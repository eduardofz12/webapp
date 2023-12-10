package application.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Configuration
//	@EnableWebMvc
//	public class CorsConfiguration implements WebMvcConfigurer {
//	    @Override
//	    public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/**")
//	                .allowedMethods("GET", "POST", "OPTIONS").exposedHeaders("Authorization")
//	                .allowedOrigins("*")
//	                .allowedHeaders("*");
//	    }
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.headers().frameOptions().disable()
			.and()
			.csrf().disable()
			.authorizeRequests()
			.anyRequest()
			.permitAll()
			.and()
			.httpBasic();
		http.cors().configurationSource(request -> {
            CorsConfiguration cors = new CorsConfiguration();
            cors.setAllowedOrigins(List.of("http://localhost:4200/"));
            cors.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            cors.setAllowedHeaders(List.of("*"));
            return cors;
        });
	}
	
}
