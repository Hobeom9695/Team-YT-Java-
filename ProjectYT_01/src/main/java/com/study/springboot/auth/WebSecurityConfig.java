package com.study.springboot.auth;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;
//	private HttpSession session;
//	String id = (String)session.getAttribute("id");
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
				.antMatchers("/guest/**").permitAll()
				.antMatchers("/member/**").hasAnyRole("NORMAL", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/security/**").permitAll()
				.anyRequest().authenticated();
		
		http.formLogin()
			.loginPage("/loginForm")			// default : /login
			.loginProcessingUrl("/j_spring_security_check")
//			.failureUrl("/loginForm?error")			// default : /login?error
			.defaultSuccessUrl("/member/main2")
			.failureHandler(authenticationFailureHandler)
			.usernameParameter("j_username")	// default : j_username
			.passwordParameter("j_password")	// default : j_password
//			.usernameParameter(id)
			.permitAll();
		
		http.logout()
			.logoutUrl("/logout")	// default
			.logoutSuccessUrl("/")
			.permitAll();
		
		// ssl을 사용하지 않으면 true로 사용
		http.csrf().disable();
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		// 테스트를 위한 inMemory방식
//		auth.inMemoryAuthentication()
//			.withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
//			.and()
//			.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
//		// ROLE_ADMIN 에서 ROLE_는 자동으로 붙는다.
//	}
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select id as userName, password, enabled"
								 + " from user_yt where id = ?")
			.authoritiesByUsernameQuery("select id as userName, authority"
									   + " from user_yt where id = ?")
			.passwordEncoder(new BCryptPasswordEncoder());
		
		// 쿼리문에 사용되는 userName, password, enabled, authority는 Spring에서 지정된 컬럼명입니다.
		// 만일 테이블 컬럼과 다를 경우 별칭(Alias)을 줘서 변경하도록 합니다.
		// enabled 의 값이 0이면 비활성, 1이면 활성

	}
	
	// passwordEncoder() 추가
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
