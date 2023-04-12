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

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * An example of explicitly configuring Spring Security with the defaults.
 *
 * @author Rob Winch
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//	@Bean
//	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
//		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//		return authenticationManagerBuilder.build();
//	}
//
//	@Bean
//	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
//		return new CustomUserDetailService(bCryptPasswordEncoder);
//	}
//
//	@Bean
//	public AuthorizationManager<Collection<ConfigAttribute>> authorizationManager() {
//		return new CustomAuthorizationManager<Collection<ConfigAttribute>>();
//	}

//	@Bean
//	@Role(ROLE_INFRASTRUCTURE)
//	public Advisor authorizationManagerBeforeMethodInterception(AuthorizationManager<MethodInvocation> authorizationManager) {
//		JdkRegexpMethodPointcut pattern = new JdkRegexpMethodPointcut();
//		pattern.setPattern("com.baeldung.enablemethodsecurity.services.*");
//		return new AuthorizationManagerBeforeMethodInterceptor(pattern, authorizationManager);
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println("-----securityFilterChain----");
		// @formatter:off
		
		//http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/index").access(new CustomAuthorizationManager<RequestAuthorizationContext>()));

		//OK
		//http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated()).httpBasic(withDefaults()).formLogin(withDefaults());
		
//		http.authorizeHttpRequests().requestMatchers("/index").permitAll().
//		and().authorizeHttpRequests((authorize) -> authorize.requestMatchers("/index2").access(new AuthorityAuthorizationManager("")))
//		.httpBasic(withDefaults()).formLogin(withDefaults());
		
		//Collection<? extends GrantedAuthority> authorities =SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		//http.authorizeHttpRequests().anyRequest().access(new AuthorityAuthorizationManager<>(authorities));

		
//		 http.csrf()
//         .disable()
//         .authorizeHttpRequests()
//         .anyRequest()
//         .authenticated()
//         .and()
//         .sessionManagement()
//         .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		// @formatter:on
		return http.build();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(16);
	}

	// @formatter:off
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		String result = this.encoder().encode("password");
		UserDetails user = User.withUsername("user")
				.password(result)
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	// @formatter:on
}
