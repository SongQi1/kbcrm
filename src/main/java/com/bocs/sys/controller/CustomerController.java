package com.bocs.sys.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.bocs.core.base.AbstractController;
import com.bocs.core.support.Assert;
import com.bocs.core.support.DatatablesViewPage;
import com.bocs.core.util.InstanceUtil;
import com.bocs.core.util.WebUtil;
import com.bocs.sys.model.CustomerDecorationForm;
import com.bocs.sys.model.Pictures;
import com.bocs.sys.model.SysUser;
import com.bocs.sys.service.CustomerDecorationFormService;
import com.bocs.sys.service.PicturesService;
import com.bocs.sys.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户管理控制器
 */
@RestController
@Api(value = "客户管理", description = "客户管理")
@RequestMapping(value = "/customer")
public class CustomerController extends AbstractController<CustomerDecorationForm> {

    @Autowired
    private CustomerDecorationFormService customerDecorationFormService;
    @Autowired
    private PicturesService picturesService;
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/toList")
    public ModelAndView toList(){
        return  new ModelAndView("/management/customer_list");
    }

    @RequestMapping(value = "list")
    public DatatablesViewPage<CustomerDecorationForm> list(ModelMap modelMap, HttpServletRequest request){
        Map<String, Object> params = new HashMap<>();
        Integer start = Integer.parseInt(request.getParameter("start"));
        Integer length = Integer.parseInt(request.getParameter("length"));
        Integer pageNum = start/length + 1;
        params.put("pageNum", pageNum);
        params.put("pageSize", request.getParameter("length"));
        SysUser currUser = sysUserService.queryById(WebUtil.getCurrentUser());
        if (!"2".equals(currUser.getUserType())) {
            params.put("authority", currUser.getId());
        } else{
            params.put("userType", "2");
        }
        params.put("keyword", request.getParameter("keyword"));
        params.put("phone", request.getParameter("phone"));
        params.put("status", request.getParameter("status"));
        params.put("businessMan", request.getParameter("businessMan"));
        params.put("startTime", request.getParameter("startTime"));
        params.put("endTime", request.getParameter("endTime"));
        Page<CustomerDecorationForm> page = customerDecorationFormService.query(params);
        DatatablesViewPage<CustomerDecorationForm> result = new DatatablesViewPage<>();
        result.setData(page.getRecords());
        result.setiTotalDisplayRecords(page.getTotal());
        result.setiTotalRecords(page.getTotal());
        result.setSuccess("success");
        return result;
    }

    @PostMapping(value = "del/{id}")
    public Object delUser(@PathVariable("id") Long id, ModelMap modelMap) {
        CustomerDecorationForm param = new CustomerDecorationForm();
        param.setId(id);
        return super.delete(modelMap, param);
    }

    @RequestMapping(value = "/toDetail/{id}", method = RequestMethod.GET)
    public ModelAndView toDetail(@PathVariable("id") Long id) {
        Assert.notNull(id, "ID");
        ModelAndView modelAndView = new ModelAndView("/management/customer_details");
        CustomerDecorationForm customer = customerDecorationFormService.selectById(id);
        if (customer != null) {
            SysUser createUser = sysUserService.queryById(customer.getCreateBy());
            if(createUser != null){
                customer.setBusinessMan(createUser.getNamePinyin());
            }

            Map<String, List<Long>> map = getPics(customer.getId());
            modelAndView.addObject("entrancePicIdList", map.get("entrancePicIdList"));
            modelAndView.addObject("unitPicIdList", map.get("unitPicIdList"));
            modelAndView.addObject("doorPicIdList", map.get("doorPicIdList"));
        }


        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    /**
     * 获取图片
     * @param customerId
     * @return
     */
    private Map<String, List<Long>> getPics(Long customerId){
        Map<String,List<Long>> picMap = InstanceUtil.newHashMap();
        Map<String, Object> param = InstanceUtil.newHashMap();
        param.put("customerId", customerId);
        List<Pictures> pictureList = picturesService.queryList(param);
        if(pictureList != null && pictureList.size() > 0){

            //分捡图片
            List<Long> entrancePicIdList = InstanceUtil.newArrayList();
            List<Long> unitPicIdList = InstanceUtil.newArrayList();
            List<Long> doorPicIdList = InstanceUtil.newArrayList();
            for(Pictures picture : pictureList){
                //entrance(小区正门),unit(单元),door(门牌)
                if("entrance".equals(picture.getPictureType())){
                    entrancePicIdList.add(picture.getId());
                }else if("unit".equals(picture.getPictureType())){
                    unitPicIdList.add(picture.getId());
                }else if("door".equals(picture.getPictureType())){
                    doorPicIdList.add(picture.getId());
                }
            }
            picMap.put("entrancePicIdList", entrancePicIdList);
            picMap.put("unitPicIdList", unitPicIdList);
            picMap.put("doorPicIdList", doorPicIdList);
        }
        return picMap;

    }
}
