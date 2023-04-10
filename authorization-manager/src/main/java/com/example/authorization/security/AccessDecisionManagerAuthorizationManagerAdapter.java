package com.example.authorization.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AccessDecisionManagerAuthorizationManagerAdapter implements AuthorizationManager {

	@Autowired
	private CustomizeAccessDecisionManager accessDecisionManager;
	@Autowired
	private CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;
	// private final AccessDecisionManager accessDecisionManager;
	// private final SecurityMetadataSource securityMetadataSource;

	@Override
	public AuthorizationDecision check(Authentication authentication, Object object) {
		try {
			Collection<ConfigAttribute> attributes = this.securityMetadataSource.getAttributes(object);
			this.accessDecisionManager.decide(authentication, object, attributes);
			return new AuthorizationDecision(true);
		} catch (AccessDeniedException ex) {
			return new AuthorizationDecision(false);
		}
	}

}
