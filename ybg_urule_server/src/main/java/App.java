import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/*** @author https://gitee.com/YYDeament/88ybg
 * 
 * @date 2017/10/1 */
@SpringBootApplication
@EnableAutoConfiguration // 这个注解可以根据你依赖的包自动生成相关配置
@ComponentScan(basePackages = { "com.bstek", "com.ybg", "cn", "org.mybatis" }) // 扫描注解
// @EnableAutoConfiguration(exclude = { org.activiti.spring.boot.SecurityAutoConfiguration.class, org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class })
// @EnableAdminServer // spring boot admin监控 ，不喜欢可以不要
public class App extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}
}
