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

public class CustomAuthorizationManager<T> implements AuthorizationManager<Collection<ConfigAttribute>> {
	private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, Collection<ConfigAttribute> collection) {

		if (hasAuthentication(authentication.get())) {
			Iterator<ConfigAttribute> iterator = collection.iterator();
			while (iterator.hasNext()) {
				ConfigAttribute ca = iterator.next();
				// required permission of this request
				String needRole = ca.getAttribute();
				// all permissions of current user
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
