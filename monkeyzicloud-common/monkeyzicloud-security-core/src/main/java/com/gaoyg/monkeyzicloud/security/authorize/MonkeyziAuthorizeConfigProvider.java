package com.gaoyg.monkeyzicloud.security.authorize;

import com.gaoyg.monkeyzicloud.security.properties.SecurityConstants;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;


/**
 * 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 * @author  guoguo
 */
@Component
@Order(Integer.MIN_VALUE)
public class MonkeyziAuthorizeConfigProvider implements AuthorizeConfigProvider {

	/**
	 * Config boolean.
	 *
	 * @param config the config
	 *
	 * @return the boolean
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
				"/druid/**", "/auth/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs").permitAll();
		return false;
	}

}
