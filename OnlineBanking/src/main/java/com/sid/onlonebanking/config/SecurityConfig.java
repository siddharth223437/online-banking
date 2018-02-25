package com.sid.onlonebanking.config;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sid.onlonebanking.serviceImpl.AppUserDetailsService;
import com.sid.onlonebanking.serviceImpl.MyBasicAuthenticationEntryPoint;



@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(appUserDetailsService);
//	}
	
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
    }
	
	private static final String SALT = "salt"; // Salt should be protected carefully
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }
	
	
	public static final String[] PATH_MATCHER = {
			"/account/register",
			"/account/login",
			"/logout",
			"/world/country",
			"/account/test"
			
	};
	
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
		// starts authorizing configurations
		.authorizeRequests()
		// ignoring the guest's urls "
		.antMatchers(PATH_MATCHER).permitAll()
		// authenticate all remaining URLS
		.anyRequest().fullyAuthenticated().and()
      /* "/logout" will log the user out by invalidating the HTTP Session,
       * cleaning up any {link rememberMe()} authentication that was configured, */
		.logout()
        .permitAll()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
        .and()
		// enabling the basic authentication
		.httpBasic()
		.and()
		
		// configuring the session on the server
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
		// disabling the CSRF - Cross Site Request Forgery
		.csrf().disable();
	}

}
