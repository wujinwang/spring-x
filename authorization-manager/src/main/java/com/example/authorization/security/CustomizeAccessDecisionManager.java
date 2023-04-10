package com.example.authorization.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @Description 访问决策管理器
 * @Author jin_z
 * @Date 2020/5/19 23:38
 * @since:
 * @copyright:
 */
@Component
public class CustomizeAccessDecisionManager implements AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)
			throws AccessDeniedException, InsufficientAuthenticationException {
		Iterator<ConfigAttribute> iterator = collection.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute ca = iterator.next();
			// 当前请求需要的权限
			String needRole = ca.getAttribute();
			// 当前用户所具有的权限
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(needRole)) {
					return;
				}
			}
		}
		throw new AccessDeniedException("Access Denied!");
	}

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
