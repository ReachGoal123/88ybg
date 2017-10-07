/**
 * 
 */
package com.ybg.social;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.security.SpringSocialConfigurer;
import com.baidu.oauth.service.BaiduUserService;
import com.ybg.core.properties.BaiduProperties;
import com.ybg.core.properties.SinaProperties;
import com.ybg.social.baidu.connet.BaiduConnectionFactory;
import com.ybg.social.sina.connet.SinaConnectionFactory;
import cn.sina.service.WeiboUserService;

/** @author zhailiang */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
	
	@Autowired
	private DataSource			dataSource;
	// @Autowired
	// private SecurityProperties securityProperties;
	@Autowired(required = false)
	private ConnectionSignUp	connectionSignUp;
	
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		repository.setTablePrefix("sys_");// 前缀
		if (connectionSignUp != null) {
			repository.setConnectionSignUp(connectionSignUp);
		}
		return repository;
	}
	
	// 改变默认的过滤器
	@Bean
	public SpringSocialConfigurer imoocSocialSecurityConfig() {
		String filterProcessesUrl = "/social";
		ImoocSpringSocialConfigurer configurer = new ImoocSpringSocialConfigurer(filterProcessesUrl);
		// configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		configurer.signupUrl("/common/login_do/toregister.do");// 注册地址 如果 抛出 无绑定异常，则 添加 session 到 服务器中 再去 绑定页中 请求/social/user 地址 就可以拿到 对应 的 第三方ID
		return configurer;
	}
	
	// 和社交登陆绑定的工具类
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator)) {
		};
	}
}
