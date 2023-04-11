/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.authorization.config;

import static org.springframework.beans.factory.config.BeanDefinition.ROLE_INFRASTRUCTURE;

import java.util.Collection;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.authorization.security.CustomAuthorizationManager;
import com.example.authorization.service.CustomUserDetailService;

/**
 * An example of explicitly configuring Spring Security with the defaults.
 *
 * @author Rob Winch
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		return authenticationManagerBuilder.build();
	}

	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
		return new CustomUserDetailService(bCryptPasswordEncoder);
	}

	@Bean
	public AuthorizationManager<Collection<ConfigAttribute>> authorizationManager() {
		return new CustomAuthorizationManager<Collection<ConfigAttribute>>();
	}

	@Bean
	@Role(ROLE_INFRASTRUCTURE)
	public Advisor authorizationManagerBeforeMethodInterception(AuthorizationManager<MethodInvocation> authorizationManager) {
		JdkRegexpMethodPointcut pattern = new JdkRegexpMethodPointcut();
		pattern.setPattern("com.baeldung.enablemethodsecurity.services.*");
		return new AuthorizationManagerBeforeMethodInterceptor(pattern, authorizationManager);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("-----securityFilterChain----");
		// @formatter:off
		//http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/my/authorized/endpoint").access(new CustomAuthorizationManager()));

		 http.csrf()
         .disable()
         .authorizeHttpRequests()
         .anyRequest()
         .authenticated()
         .and()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		// @formatter:on
		return http.build();
	}

}
