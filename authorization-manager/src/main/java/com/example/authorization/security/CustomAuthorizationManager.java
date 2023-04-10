package com.example.authorization.security;

import java.util.function.Supplier;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;

public class CustomAuthorizationManager<T> implements AuthorizationManager<MethodInvocation> {
	private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, MethodInvocation methodInvocation) {

		
		if (hasAuthentication(authentication.get())) {
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
