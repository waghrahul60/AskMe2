package com.askme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.askme.security.JwtAuthenticationFilter;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		//.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		 .authorizeRequests().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests().antMatchers("/api/auth/**").permitAll()
		.antMatchers("/api/test/**").permitAll()
		.antMatchers("/api/admin/**").hasRole("ADMIN")
		//.antMatchers("/api/user/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/api/user/**")
		.permitAll()
		.antMatchers(HttpMethod.POST, "/api/user/**")
		.permitAll()
		.antMatchers(HttpMethod.GET, "/api/subquery/**")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/api/posts/by-subquery/**")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/api/subquery/**")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/api/posts/")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/api/posts/")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/api/posts/**")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/api/posts/**")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/api/votes").permitAll()
//        .permitAll()
//        .antMatchers(HttpMethod.POST, "/api/votes")
//        .permitAll()
        .antMatchers(HttpMethod.GET, "/api/comments/**")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/api/comments/")
        .permitAll();
		//.anyRequest().authenticated();
		
//		http.addFilterBefore(jwtAuthenticationFilter,
//                UsernamePasswordAuthenticationFilter.class);
	}
	
	
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	 }
	 
	 @Override
	 protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			// TODO Auto-generated method stub
			authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
