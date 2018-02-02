package com.bocs.sys.controller;

/**
 * Description:<p> </p>
 * Created by songqi on 2017/8/27.
 */

import com.baomidou.mybatisplus.plugins.Page;
import com.bocs.core.base.AbstractController;
import com.bocs.core.exception.BusinessException;
import com.bocs.core.support.Assert;
import com.bocs.core.support.DatatablesViewPage;
import com.bocs.core.util.DateUtil;
import com.bocs.core.util.InstanceUtil;
import com.bocs.core.util.SecurityUtil;
import com.bocs.core.util.WebUtil;
import com.bocs.core.util.excel.ExportExcel;
import com.bocs.sys.model.SysUser;
import com.bocs.sys.service.SysUserService;
import com.bocs.sys.vo.Select;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理控制器
 *
 */
@RestController
@Api(value = "用户管理", description = "用户管理")
@RequestMapping(value = "/user")
public class UserController extends AbstractController<SysUser> {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping
    @ApiOperation(value = "修改用户信息")
    @RequiresPermissions("sys.base.user.update")
    public Object update(ModelMap modelMap, @RequestBody SysUser param) {
        Assert.isNotBlank(param.getAccount(), "ACCOUNT");
        Assert.length(param.getAccount(), 3, 15, "ACCOUNT");
        if (param.getId() != null) {
            if (param.getEnable() == null) {
                param.setEnable(1);
            }
            SysUser user = sysUserService.queryById(param.getId());
            Assert.notNull(user, "USER", param.getId());
            if (StringUtils.isNotBlank(param.getPassword())) {
                if (!param.getPassword().equals(user.getPassword())) {
                    param.setPassword(SecurityUtil.encryptPassword(param.getPassword()));
                }
            }
        } else if (StringUtils.isNotBlank(param.getPassword())) {
            param.setPassword(SecurityUtil.encryptPassword(param.getPassword()));
        }
        return super.update(modelMap, param);
    }

    // 查询用户
    @ApiOperation(value = "查询用户")
    @RequiresPermissions("sys.base.user.read")
    @PutMapping(value = "/read/list")
    public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
        return super.query(modelMap, param);
    }

    // 用户详细信息
    @ApiOperation(value = "用户详细信息")
    @RequiresPermissions("sys.base.user.read")
    @PutMapping(value = "/read/detail")
    public Object get(ModelMap modelMap, @RequestBody SysUser param) {
        return super.get(modelMap, param);
    }



    // 当前用户
   /* @ApiOperation(value = "当前用户权限信息")
    @GetMapping(value = "/read/promission")
    public Object promission(ModelMap modelMap) {
        Long id = getCurrUser();
        SysUser sysUser = sysUserService.queryById(id);
        modelMap.put("user", sysUser);
        parameter = new Parameter("sysAuthorizeService", "queryAuthorizeByUserId").setId(id);
        logger.info("{} execute queryAuthorizeByUserId start...", parameter.getNo());
        List<?> menus = provider.execute(parameter).getList();
        logger.info("{} execute queryAuthorizeByUserId end.", parameter.getNo());
        modelMap.put("menus", menus);
        return setSuccessModelMap(modelMap);
    }*/

    // 当前用户
    @ApiOperation(value = "当前用户信息")
    @GetMapping(value = "/read/current")
    public Object current(ModelMap modelMap) {
        SysUser param = new SysUser();
        param.setId(getCurrUser());
        return super.get(modelMap, param);
    }

    @ApiOperation(value = "修改个人信息")
    @PostMapping(value = "/update/person")
    public Object updatePerson(ModelMap modelMap, @RequestBody SysUser param) {
        param.setId(getCurrUser());
        param.setPassword(null);
        Assert.isNotBlank(param.getAccount(), "ACCOUNT");
        Assert.length(param.getAccount(), 3, 15, "ACCOUNT");
        return super.update(modelMap, param);
    }


    // 修改密码
    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/update/password")
    public Object updatePassword(ModelMap modelMap, @RequestBody SysUser param) {
        Assert.isNotBlank(param.getOldPassword(), "OLDPASSWORD");
        Assert.isNotBlank(param.getPassword(), "PASSWORD");
        Long userId = getCurrUser();
        String encryptPassword = SecurityUtil.encryptPassword(param.getOldPassword());
        SysUser sysUser = sysUserService.queryById(userId);
        Assert.notNull(sysUser, "USER", param.getId());
        if (!sysUser.getPassword().equals(encryptPassword)) {
            throw new UnauthorizedException("原密码错误.");
        }
        param.setPassword(encryptPassword);
        param.setUpdateBy(getCurrUser());
        return super.update(modelMap, param);
    }

    @RequestMapping("/my")
    public ModelAndView my(){
        Long userId = WebUtil.getCurrentUser();
        SysUser user = sysUserService.queryById(userId);
        return new ModelAndView("/account/my","user", user);
    }

    @RequestMapping("/account")
    public ModelAndView account(){
        Long userId = WebUtil.getCurrentUser();
        SysUser sysUser = sysUserService.queryById(userId);
        return new ModelAndView("/account/account", "user", sysUser);
    }

    @PostMapping("/setUserName")
    public Object setUserName(ModelMap modelMap, String userName){
        Long userId = WebUtil.getCurrentUser();
        SysUser sysUser = sysUserService.queryById(userId);
        sysUser.setUserName(userName);
        sysUserService.update(sysUser);
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping("/toList")
    public ModelAndView toList(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.setViewName("/management/user_list");
        return  modelAndView;
    }

    @RequestMapping(value = "/addUserPage")
    public ModelAndView addUserPage() {
        return new ModelAndView("/management/user_form");
    }

    @GetMapping("/queryManager")
    public Object queryManager(Long titleId, Long id, ModelMap modelMap){
        List<SysUser> managerList = sysUserService.queryManager(titleId);
        List<Select> results = InstanceUtil.newArrayList();
        if(managerList != null && managerList.size() > 0){
            for(SysUser manager : managerList) {
                Select select = new Select();
                select.setId(manager.getId());
                select.setText(manager.getNamePinyin());
                if(id == null){
                    results.add(select);
                }else if(id > 0 && id.longValue() != manager.getId().longValue()){
                    results.add(select);
                }
            }
        }
        return setSuccessModelMap(modelMap, results);

    }

    @PostMapping(value = "/addUser")
    public Object addUser(SysUser user, ModelMap modelMap) {
        if (user.getId() == null) {
            user.setUserType("1");
            user.setEnable(1);
        }

        sysUserService.update(user);
        return setSuccessModelMap(modelMap);
    }

    @RequestMapping(value = "/editUserPage/{id}", method = RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable("id") Long id) {
        Assert.notNull(id, "ID");
        SysUser user = sysUserService.queryById(id);
        if(user.getManagerId() != null && user.getManagerId() > 0){
            SysUser manager = sysUserService.queryById(user.getManagerId());
            user.setManagerNamePinyin(manager.getNamePinyin());
        }
        ModelAndView modelAndView = new ModelAndView("/management/user_form");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "viewUser/{id}", method = RequestMethod.GET)
    public ModelAndView viewUser(@PathVariable("id") Long id) {
        Assert.notNull(id, "ID");
        SysUser user = sysUserService.queryById(id);
        ModelAndView modelAndView = new ModelAndView("/user/user_detail");
        if(user != null){
            modelAndView.addObject("user", user);
        }
        return modelAndView;
    }

    @PostMapping(value = "del/{id}")
    public Object delUser(@PathVariable("id") Long id, ModelMap modelMap) {
        SysUser param = new SysUser();
        param.setId(id);
        return super.delete(modelMap, param);
    }
    @RequestMapping(value = "list")
    public DatatablesViewPage<SysUser> list(ModelMap modelMap, HttpServletRequest request){
        Map<String, Object> params = new HashMap<>();
        Integer start = Integer.parseInt(request.getParameter("start"));
        Integer length = Integer.parseInt(request.getParameter("length"));
        Integer pageNum = start/length + 1;
        params.put("pageNum", pageNum);
        params.put("pageSize", request.getParameter("length"));
        params.put("titleId", request.getParameter("titleId"));
        params.put("userInfo", request.getParameter("userInfo"));
        params.put("startTime", request.getParameter("startTime"));
        params.put("endTime", request.getParameter("endTime"));

        params.put("userType", "1");//只查询普通用户
        Page<SysUser> page = sysUserService.query(params);
        DatatablesViewPage<SysUser> result = new DatatablesViewPage<>();
        result.setData(page.getRecords());
        result.setiTotalDisplayRecords(page.getTotal());
        result.setiTotalRecords(page.getTotal());
        result.setSuccess("success");
        return result;
    }

    @RequestMapping(value = "/toEditCurrUser", method = RequestMethod.GET)
    public ModelAndView toEditCurrUser() {
        SysUser user = sysUserService.queryById(getCurrUser());
        ModelAndView modelAndView = new ModelAndView("/management/user_message");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping(value = "/editCurrUser")
    public Object editCurrUser(SysUser user, ModelMap modelMap) {
        sysUserService.update(user);
        return setSuccessModelMap(modelMap);
    }
    @RequestMapping(value = "/toResetPwd", method = RequestMethod.GET)
    public ModelAndView toResetPwd() {
        SysUser user = sysUserService.queryById(getCurrUser());
        ModelAndView modelAndView = new ModelAndView("/management/resetPassword");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping(value = "/resetPwd")
    public Object resetPwd(SysUser user, ModelMap modelMap) {
        if (StringUtils.isNotEmpty(user.getNewPassword())) {
            user.setPassword(SecurityUtil.encryptPassword(user.getNewPassword()));
        }
        sysUserService.update(user);
        return setSuccessModelMap(modelMap);
    }

    /**
     * 校验密码是否正确
     * @param password
     * @return
     */
    @PostMapping(value = "/validPwd")
    public Boolean validPwd(String password){
        SysUser currUser = sysUserService.queryById(getCurrUser());
        if (SecurityUtil.encryptPassword(password).equals(currUser.getPassword())) {
            return true;
        } else{
            return false;
        }
    }

    /**
     * 校验当前用户的用户名是否存在
     * @param userName
     * @userName
     */
    @PostMapping(value = "/validUserName")
    public Boolean validUserName(String userName){
        SysUser currUser = sysUserService.queryById(getCurrUser());
        Map<String, Object> params = new HashedMap();
        params.put("userName", userName);
        List<SysUser> userList = sysUserService.queryList(params);
        if (userList != null) {
            if (userList.size() > 0){
                if (currUser.getId().equals(userList.get(0).getId())) {
                    return true;
                } else{
                    return false;
                }
            } else{
                return true;
            }
        } else{
            return false;
        }
    }

    /**
     * 校验当前用户的手机号是否存在
     * @param phone
     * @return
     */
    @PostMapping(value = "/validPhone")
    public Boolean validPhone(String phone){
        SysUser currUser = sysUserService.queryById(getCurrUser());
        Map<String, Object> params = new HashedMap();
        params.put("phone", phone);
        List<SysUser> userList = sysUserService.queryList(params);
        if (userList != null) {
            if (userList.size() > 0){
                if (currUser.getId().equals(userList.get(0).getId())) {
                    return true;
                } else{
                    return false;
                }
            } else{
                return true;
            }
        } else{
            return false;
        }
    }

    @RequestMapping(value = "list1")
    public ModelAndView searchByKeyword(SysUser user,HttpServletRequest request) {
        Map<String, Object> params = new HashMap();
        params.put("userType", 1);
        params.put("keyword",user.getKeyword());
        List<Long> userIds = sysUserService.searchByKeyword(params);

        List<SysUser> users = sysUserService.getList(userIds);
        List<List<Object>> infos = Lists.newArrayList();
        for (SysUser stu : users) {
            List<Object> dataList = Lists.newArrayList();
            dataList.add(stu.getNamePinyin());
            dataList.add(stu.getPhone());
            dataList.add(stu.getIdCard());
            dataList.add(stu.getRemark());
            infos.add(dataList);
        }
        request.getSession().setAttribute("EXPORT_FILE", infos);

        ModelAndView modelAndView = new ModelAndView("/management/userManagement");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @RequestMapping(value = "export")
    public Object export(ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) {
        String fileName = "用户数据" + DateUtil.getDateTime("yyyyMMddHHmmss") + ".xlsx";

        List<String> headerList = Lists.newArrayList();
        headerList.add("业务员");
        headerList.add("手机");
        headerList.add("身份证");
        headerList.add("备注");

        List<List<Object>> dataList = (List<List<Object>>) request.getSession().getAttribute("EXPORT_FILE");
        ExportExcel exportExcel = new ExportExcel("业务员信息", headerList);

        if(dataList!=null && dataList.size()>0){
            for (int i = 0; i < dataList.size(); i++) {
                Row row = exportExcel.addRow();
                for (int j = 0; j < dataList.get(i).size(); j++) {
                    exportExcel.addCell(row, j, dataList.get(i).get(j));
                }
            }
            try {
                exportExcel.write(response, fileName).dispose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            throw new BusinessException("暂无数据");
        }
        return null;
    }
}
