package com.bocs.core.exception;

import com.bocs.core.support.HttpCode;

/**
 * Description:<p>业务异常类。 </p>
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@SuppressWarnings("serial")
public class BusinessException extends BaseException {
	private String code;

	public BusinessException() {
	}

	public BusinessException(Throwable ex) {
		super(ex);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable ex) {
		super(message, ex);
	}

	public BusinessException(String code, String message){
		super(message);
		this.code = code;
	}

	protected HttpCode getHttpCode() {
		return HttpCode.CONFLICT;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}