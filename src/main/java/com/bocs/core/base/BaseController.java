package com.bocs.core.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.plugins.Page;
import com.bocs.core.Constants;
import com.bocs.core.exception.BaseException;
import com.bocs.core.exception.IllegalParameterException;
import com.bocs.core.support.HttpCode;
import com.bocs.core.util.InstanceUtil;
import com.bocs.core.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 控制器基类：处理返回结果及异常
 */
public abstract class BaseController {
	protected final Logger logger = LogManager.getLogger(this.getClass());

	/** 获取当前用户Id */
	protected Long getCurrUser() {
		return WebUtil.getCurrentUser();
	}

	/** 设置成功响应代码 */
	protected ResponseEntity<ModelMap> setSuccessModelMap() {
		return setSuccessModelMap(null, null);
	}

	/** 设置成功响应代码 */
	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
		return setSuccessModelMap(modelMap, null);
	}

	/** 设置成功响应代码 */
	protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
		return setModelMap(modelMap, HttpCode.OK, data);
	}

	/** 设置响应代码 */
	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code) {
		return setModelMap(modelMap, code, null);
	}

	/** 设置响应代码 */
	protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
		if(modelMap == null){
			modelMap = new ModelMap();
		}
		Map<String, Object> map = InstanceUtil.newLinkedHashMap();
		map.putAll(modelMap);
		modelMap.clear();
		for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			if (!key.startsWith("org.springframework.validation.BindingResult") && !key.equals("void")) {
				modelMap.put(key, map.get(key));
			}
		}
		if (data != null) {
			if (data instanceof Page) {
				Page<?> page = (Page<?>) data;
				modelMap.put("data", page.getRecords());
				modelMap.put("current", page.getCurrent());
				modelMap.put("size", page.getSize());
				modelMap.put("pages", page.getPages());
				modelMap.put("iTotalRecords", page.getTotal());
				modelMap.put("iTotalDisplayRecords", page.getTotal());
			} else if (data instanceof List<?>) {
				modelMap.put("data", data);
				modelMap.put("iTotalRecords", ((List<?>) data).size());
				modelMap.put("iTotalDisplayRecords", ((List<?>) data).size());
			} else {
				modelMap.put("data", data);
			}
		}
		if(HttpCode.OK.value() == code.value()){
			modelMap.put("success", true);
		}else{
			modelMap.put("success", false);
		}
		modelMap.put("httpCode", code.value());
		modelMap.put("msg", code.msg());
		modelMap.put("timestamp", System.currentTimeMillis());

		logger.info("RESPONSE : " + JSON.toJSONString(modelMap));
		return ResponseEntity.ok(modelMap);
	}

	/** 异常处理 */
	@ExceptionHandler(Exception.class)
	public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
			throws Exception {
		logger.error(Constants.Exception_Head, ex);

		ModelMap modelMap = new ModelMap();
		if (ex instanceof BaseException) {
			((BaseException) ex).handler(modelMap);
		} else if (ex instanceof IllegalArgumentException) {
			new IllegalParameterException(ex.getMessage()).handler(modelMap);
		} else if (ex instanceof UnauthorizedException) {
			modelMap.put("httpCode", HttpCode.FORBIDDEN.value());
			//modelMap.put("data", ResultUtil.error(StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.FORBIDDEN.msg())));

			modelMap.put("msg", StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.FORBIDDEN.msg()));
		} else {
			modelMap.put("httpCode", HttpCode.INTERNAL_SERVER_ERROR.value());
			String msg = StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.INTERNAL_SERVER_ERROR.msg());
			//modelMap.put("data", ResultUtil.error(msg.length() > 100 ? "系统发生错误,请稍候再试." : msg));
			modelMap.put("msg", msg.length() > 100 ? "系统发生错误,请稍候再试." : msg);
		}
		response.setContentType("application/json;charset=UTF-8");
		modelMap.put("timestamp", System.currentTimeMillis());
		if(isAjax(request)){
			logger.info("RESPONSE : " + JSON.toJSON(modelMap));
			logger.info(JSON.toJSON(modelMap));
			byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
			response.getOutputStream().write(bytes);
			return null;
		}else{
			ModelAndView modelAndView = new ModelAndView("/syserror","msg",modelMap.get("msg"));
			return modelAndView;
		}

	}

	/**
	 * 判断是否是异步请求
	 * @param request
	 * @return
	 */
	protected boolean isAjax(HttpServletRequest request){
		String requestType = request.getHeader("X-Requested-With");
		if(requestType != null && "XMLHttpRequest".equalsIgnoreCase(requestType)){
			return true;
		}
		return false;
	}
}
