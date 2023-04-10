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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.authorization.security.AccessDecisionManagerAuthorizationManagerAdapter;

/**
 * An example of explicitly configuring Spring Security with the defaults.
 *
 * @author Rob Winch
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("-----securityFilterChain----");
		// @formatter:off
		//http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated()).httpBasic(withDefaults()).formLogin(withDefaults()).rememberMe((remember) -> remember.rememberMeServices(this.rememberMeServices(this.userDetailsService())));
		http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/my/authorized/endpoint").access(new AccessDecisionManagerAuthorizationManagerAdapter()));
        
        
		// @formatter:on
		return http.build();
	}

}
