package com.example.authorization.security;

import java.util.function.Supplier;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

public class CustomAuthorizationManager<T> implements AuthorizationManager<RequestAuthorizationContext> {
	private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
		if (hasAuthentication(authentication.get())) {
			System.out.println("----hasAuthentication----");
			authentication.get().getAuthorities();

			
			return new AuthorizationDecision(true);
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
