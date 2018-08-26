package com.gaoyg.monkeyzicloud.commom.core.aspect;

import com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation;
import com.gaoyg.monkeyzicloud.commom.core.util.RequestUtils;
import com.gaoyg.monkeyzicloud.dto.LoginAuthDto;
import com.gaoyg.monkeyzicloud.dto.OperationLogDto;
import com.gaoyg.monkeyzicloud.util.pubutils.PublicUtil;
import com.gaoyg.monkeyzicloud.util.response.R;
import com.gaoyg.monkeyzicloud.util.util.JacksonUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author: 高yg
 * @date: 2018/8/12 22:09
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
    private ThreadLocal<Date> threadLocal=new ThreadLocal<>();

    @Resource
    private TaskExecutor taskExecutor;
    @Resource
    private RestTemplate restTemplate;

    private static final int MAX_SIZE = 2000;

    @Pointcut("@annotation(com.gaoyg.monkeyzicloud.commom.core.annotation.LogAnnotation)")
    public void logAnnotation(){
    }

    @Before("logAnnotation()")
    public void doBefore(){
       this.threadLocal.set(new Date(System.currentTimeMillis()));
    }

    @AfterReturning(pointcut = "logAnnotation()", returning = "returnValue")
    public void doAfter(final JoinPoint joinPoint, final Object returnValue){
        if (returnValue instanceof R) {
            R result = (R) returnValue;

            if (!PublicUtil.isEmpty(result) && result.getCode().equals(R.ok().getCode())) {
               this.handlerLog(joinPoint, result);
            }
        }
    }

    private void handlerLog(final JoinPoint joinPoint,final Object result){
        final Date startTime=this.threadLocal.get();
        final Date endTime=new Date(System.currentTimeMillis());
        HttpServletRequest request=RequestUtils.getRequest();
        final UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-Agent"));
        String requestURI = request.getRequestURI();
        try {
            LogAnnotation logF=giveController(joinPoint);
            // LoginAuthDto loginUser=RequestUtils.getLoginUser();
            LoginAuthDto loginUser=new LoginAuthDto();
            if (loginUser==null){
                log.error("获取登录用户信息异常");
            }
            if (logF==null){
                return;
            }
            //获取客户端操作系统
            final String os = userAgent.getOperatingSystem().getName();
            //获取客户端浏览器
            final String browser = userAgent.getBrowser().getName();
            //获取ip
            final String ipAddress = RequestUtils.getRemoteAddr(request);

            OperationLogDto operationLogDto = new OperationLogDto();
            operationLogDto.setClassName(joinPoint.getTarget().getClass().getName());
            operationLogDto.setMethodName(joinPoint.getSignature().getName());
            operationLogDto.setExcuteTime(endTime.getTime() - startTime.getTime());
            operationLogDto.setStartTime(startTime);
            operationLogDto.setEndTime(endTime);
            operationLogDto.setIp(ipAddress);
            operationLogDto.setOs(os);
            operationLogDto.setBrowser(browser);
            operationLogDto.setRequestUrl(requestURI);
            operationLogDto.setDescription("");
            operationLogDto.setGroupId(loginUser.getGroupId());
            operationLogDto.setGroupName(loginUser.getGroupName());
            operationLogDto.setCreatedTime(new Date());
            operationLogDto.setCreator(loginUser.getUserName());
            operationLogDto.setCreatorId(loginUser.getUserId());
            operationLogDto.setLastOperator(loginUser.getUserName());
            operationLogDto.setLastOperatorId(loginUser.getUserId());
            operationLogDto.setLogType(logF.logType().getType());
            operationLogDto.setLogName(logF.logType().getName());
            getControllerMethodDescription(logF,operationLogDto,result,joinPoint);
            this.threadLocal.remove();
            taskExecutor.execute(()->this.restTemplate.
                    postForObject("http://monkeyzicloud-provider-ucloud/auth/saveLog", operationLogDto, Integer.class));
        }catch (Exception ex){
            log.error("获取注解类出现异常={}", ex.getMessage(), ex);
        }
    }

    private void getControllerMethodDescription(LogAnnotation relog, OperationLogDto operationLog, Object result, JoinPoint joinPoint) {
        if (relog.isSaveRequestData()) {
            setRequestData(operationLog, joinPoint);
        }
        if (relog.isSaveResponseData()) {
            setResponseData(operationLog, result);
        }
    }

    /**
     * 设置响应数据
     * @param requestLog
     * @param result
     */
    private void setResponseData(OperationLogDto requestLog, Object result) {
        try {
            requestLog.setResponseData(String.valueOf(JacksonUtil.toJson(result)));
        } catch (Exception e) {
            log.error("获取响应数据,出现错误={}", e.getMessage(), e);
        }
    }

    /**
     * 设置请求数据
     * @param uacLog
     * @param joinPoint
     */
    private void setRequestData(OperationLogDto uacLog, JoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();
            if (args.length == 0) {
                return;
            }
            Object[] parameter = new Object[args.length];
            int index = 0;
            for (Object object : parameter) {
                if (object instanceof HttpServletRequest) {
                    continue;
                }
                parameter[index] = object;
                index++;
            }

            String requestData = JacksonUtil.toJsonWithFormat(parameter);

            if (requestData.length() > MAX_SIZE) {
                requestData = requestData.substring(MAX_SIZE);
            }

            uacLog.setRequestData(requestData);
        } catch (Exception e) {
            log.error("获取响应数据,出现错误={}", e.getMessage(), e);
        }
    }
    /**
     * 是否存在注解, 如果存在就记录日志
     */
    private static LogAnnotation giveController(JoinPoint joinPoint) {
        Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
        String methodName = joinPoint.getSignature().getName();
        if (null != methods && 0 < methods.length) {
            for (Method met : methods) {
                LogAnnotation relog = met.getAnnotation(LogAnnotation.class);
                if (null != relog && methodName.equals(met.getName())) {
                    return relog;
                }
            }
        }
        return null;
    }
}
