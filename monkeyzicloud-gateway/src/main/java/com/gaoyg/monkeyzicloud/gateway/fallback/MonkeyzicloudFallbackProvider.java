package com.gaoyg.monkeyzicloud.gateway.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author: 高yg
 * @date: 2018/7/28 22:35
 * @qq:854152531@qq.com
 * @blog http://www.monkeyzi.xin
 * @description:
 */
@Slf4j
@Component
public class MonkeyzicloudFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException){
            return response(HttpStatus.GATEWAY_TIMEOUT);
        }else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private ClientHttpResponse response(final HttpStatus status){
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode(){
                return status;
            }

            @Override
            public int getRawStatusCode()  {
                return status.value();
            }

            @Override
            public String getStatusText()  {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
               log.info("close");
            }

            @Override
            public InputStream getBody()  {
                String result= "{\n" +
                        "\"code\": 500,\n" +
                        "\"message\": \"微服务故障, 请稍后再试\"\n" +
                        "\"success\": false,\n" +
                        "}";
                return new ByteArrayInputStream(result.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers=new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }


}
