package com.example.authorization.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Supplier;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

public class CustomAuthorizationManager<T> implements AuthorizationManager<RequestAuthorizationContext> {
	private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	private final FilterInvocationSecurityMetadataSource customizeFilterInvocationSecurityMetadataSource = new CustomizeFilterInvocationSecurityMetadataSource();

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
		if (hasAuthentication(authentication.get())) {
			System.out.println("----hasAuthentication----");

			Collection<ConfigAttribute> collections = customizeFilterInvocationSecurityMetadataSource.getAttributes(context);			
			//context.getRequest().getRequestURI();

			Iterator<ConfigAttribute> iterator = collections.iterator();
			while (iterator.hasNext()) {
				ConfigAttribute ca = iterator.next();
				// 当前请求需要的权限
				String needRole = ca.getAttribute();
				// 当前用户所具有的权限
				Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
				for (GrantedAuthority authority : authorities) {
					if (authority.getAuthority().equals(needRole)) {
						return new AuthorizationDecision(true);
					}
				}
			}
			
		}
		return new AuthorizationDecision(false);
	}

	private boolean hasAuthentication(Authentication authentication) {
		return authentication != null && isNotAnonymous(authentication) && authentication.isAuthenticated();
	}

	private boolean isNotAnonymous(Authentication authentication) {
		return !this.trustResolver.isAnonymous(authentication);
	}
}
