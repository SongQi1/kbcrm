package com.bocs.core.dynamicdatasource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 切换数据源(不同方法调用不同数据源)
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:17:52
 */
@Component
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DataSourceAspect {
	private final Logger logger = LogManager.getLogger();



	//切点的位置应该包含baseService的位置
	@Pointcut(value = "execution(* com.bocs..*Service.*(..))")
	public void aspect() {}

	/**
	 * 配置前置通知,使用在方法aspect()上注册的切入点
	 */
	@Before(value = "aspect()", argNames = "point")
	public void before(JoinPoint point) {
		String className = point.getTarget().getClass().getName();
		String method = point.getSignature().getName();
		logger.info(className + "." + method + "(" + StringUtils.join(point.getArgs(), ",") + ")");
		try {
			L: for (String key : ChooseDataSource.METHODTYPE.keySet()) {
				for (String type : ChooseDataSource.METHODTYPE.get(key)) {
					if (method.startsWith(type)) {
						logger.info(key);
						HandleDataSource.putDataSource(key);
						break L;
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
			HandleDataSource.putDataSource("write");
		}
	}

	@After(value = "aspect()", argNames = "point")
	public void after(JoinPoint point) {
		HandleDataSource.clear();
	}
}
