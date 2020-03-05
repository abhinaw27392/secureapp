package com.ahinfotech.secureapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	// for your own login page authentication
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().and().logout().invalidateHttpSession(true)
				.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/logout-success").permitAll();
	}

	// login with google
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.authorizeRequests().antMatchers("/login").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.logout().invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/logout-success").permitAll();
//		
//	}

	// register custom filter using FilterRegistrationBean for specific URI only (it
	// will apply filter for these URL pattern only)
//	@Bean
//	public FilterRegistrationBean<CORSServletFilter> filterRegistrationBean() {
//		FilterRegistrationBean<CORSServletFilter> registrationBean = new FilterRegistrationBean<CORSServletFilter>();
//		CORSServletFilter filter = new CORSServletFilter();
//		registrationBean.setFilter(filter);
//		registrationBean.addUrlPatterns("/user/*");
//		registrationBean.setOrder(2);
//		return registrationBean;
//	}

}
