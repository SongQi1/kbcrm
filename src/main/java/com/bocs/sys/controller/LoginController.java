package com.bocs.sys.controller;

import com.bocs.core.Constants;
import com.bocs.core.base.AbstractController;
import com.bocs.core.exception.BusinessException;
import com.bocs.core.support.Assert;
import com.bocs.core.support.HttpCode;
import com.bocs.core.util.SecurityUtil;
import com.bocs.sys.model.SysUser;
import com.bocs.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.bocs.core.support.Resources.getMessage;

/**
 * Description:<p> </p>
 * Created by songqi on 2017/7/25.
 */
@RestController
@Api(value = "登录接口", description = "登录接口")
public class LoginController extends AbstractController<SysUser> {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录")
    public Object login(@ApiParam(hidden = true) ModelMap modelMap,
                        @ApiParam(hidden = true) HttpServletRequest request,
                        @ApiParam(required = true, value = "手机号或用户名", name = "account") String account,
                        @ApiParam(required = true, value = "登录密码", name = "password") String password){
        Assert.isNotBlank(account, "ACCOUNT");
        Assert.isNotBlank(password, "PASSWORD");

        String clientIp = (String) request.getSession().getAttribute(Constants.USER_IP);
        SysUser sysUser = sysUserService.login(account, SecurityUtil.encryptPassword(password), clientIp);
        return setSuccessModelMap(modelMap, sysUser);
    }

    @PostMapping(value = "/forgetPassword")
    @ApiOperation(value = "忘记密码")
    public Object forgetPassword(@ApiParam(hidden = true) ModelMap modelMap,
                        @ApiParam(required = true, value = "手机号或用户名", name = "account") String account,
                        @ApiParam(required = true, value = "身份证号", name = "idCard") String idCard){
        Assert.isNotBlank(account, "ACCOUNT");
        Assert.isNotBlank(idCard, "idCard");
        SysUser sysUser = sysUserService.getByAccountByIdCard(account, idCard);
        if(sysUser != null){
            return setSuccessModelMap(modelMap, sysUser.getPhone());
        }else{
            throw new BusinessException(getMessage("ACCOUNT_IDCARD_DISMATCH"));
        }
    }

    // 登出
    @ApiOperation(value = "用户登出")
    @RequestMapping("/logout")
    public Object logout(HttpServletRequest request, ModelMap modelMap) {
        SecurityUtils.getSubject().logout();
        return setSuccessModelMap(modelMap);
    }

    // 注册
    @ApiOperation(value = "用户注册")
    @PostMapping("/regin")
    public Object regin(@ApiParam(hidden = true) ModelMap modelMap,
                        @ApiParam(hidden = true) HttpServletRequest request,
                        @ApiParam(required = true, value = "手机号", name = "phone") String phone,
                        @ApiParam(required = true, value = "登录密码", name = "password") String password) {
        Assert.notNull(phone, "ACCOUNT");
        Assert.notNull(password, "PASSWORD");
        String clientIp = (String) request.getSession().getAttribute(Constants.USER_IP);
        SysUser sysUser = sysUserService.regin(phone, password, clientIp);
        return setSuccessModelMap(modelMap, sysUser);
    }

    // 没有登录
    @RequestMapping(value = "/unauthorized", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
    public Object unauthorized(ModelMap modelMap, HttpServletRequest request) throws Exception {
        if(isAjax(request)){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }else{
            return new ModelAndView("/account/login","msg","您没有登录，请重新登录！");
        }
    }

    // 没有权限
    @RequestMapping(value = "/forbidden", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
    public Object forbidden(ModelMap modelMap, HttpServletRequest request) {
        if(isAjax(request)){
            return setModelMap(modelMap, HttpCode.FORBIDDEN);
        }else{
            return new ModelAndView("/403","msg","您没有权限！");
        }
    }


    @RequestMapping("/forgetPasswordPage")
    public ModelAndView forgetPasswordPage(){
        return new ModelAndView("/account/forget_password");
    }


    @RequestMapping("/resetPasswordPage")
    public ModelAndView forgetPasswordPage(String phone){
        return new ModelAndView("/account/reset_password", "phone", phone);
    }

    @PostMapping("/resetPassword")
    public Object resetPassword(ModelMap modelMap, String phone, String password, String repassword){
        Assert.notNull(phone, "ACCOUNT");
        Assert.notNull(password, "PASSWORD");

        SysUser param = new SysUser();
        param.setPhone(phone);
        SysUser currentUser = sysUserService.selectOne(param);
        if(currentUser != null){
            currentUser.setPassword(SecurityUtil.encryptPassword(password));
            sysUserService.update(currentUser);
        }
        return setSuccessModelMap(modelMap, true);
    }


    @RequestMapping("/resetPasswordSuccessPage")
    public ModelAndView resetPasswordSuccessPage(){
        return new ModelAndView("/account/reset_password_success");
    }

    @RequestMapping("/resetPasswordErrorPage")
    public ModelAndView resetPasswordErrorPage(){
        return new ModelAndView("/account/reset_password_error");
    }

    @RequestMapping("/registerPage")
    public ModelAndView registerPage(){
        return new ModelAndView("/account/register");
    }


    @RequestMapping("/userHomePage")
    public ModelAndView userHomePage(){
        return new ModelAndView("/home/user_home");
    }



}


