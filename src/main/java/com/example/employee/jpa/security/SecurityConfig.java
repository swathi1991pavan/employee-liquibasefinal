package com.example.employee.jpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.example.employee.jpa.filter.JwtFilter;
import com.example.employee.jpa.service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity//(prePostEnabled = true)
public class SecurityConfig {
	
	 @Autowired
	    private JwtFilter authFilter;
	
	@Bean
	public UserDetailsService userDetailsService(){
		/*UserDetails admin =User.withUsername("Swathi")
				.password( encoder.encode("swathi"))
				.roles("ADMIN")
				.build();
		UserDetails user =User.withUsername("Ron")
				.password(encoder.encode("ron"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(admin,user);*/
		return new UserInfoUserDetailsService();
		
	}
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		  http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/api/addUser","/api/authenticate/**","api/users/**").permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/api/employees/**","/api/department/**").authenticated()
				.and()
				.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class);
               
		  return http.build();
				
	}
 
		/*return http.csrf().disable()
			.authorizeRequests()
			.requestMatchers("/api/employees/**").authenticated()
			.and().formLogin()
			.and().build();*/
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
}
