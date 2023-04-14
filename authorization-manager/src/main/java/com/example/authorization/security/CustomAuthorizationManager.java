package com.example.authorization.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import com.example.authorization.entity.Authority;
import com.example.authorization.repository.RequestPathRepository;

@Component
public class CustomAuthorizationManager<T> implements AuthorizationManager<RequestAuthorizationContext> {
	private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	@Autowired
	public RequestPathRepository requestPathRepository;

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {

		if (hasAuthentication(authentication.get())) {
			System.out.println("----hasAuthentication----");

			// 查询具体某个接口的权限
			String path = context.getRequest().getRequestURI();

			String method = context.getRequest().getMethod();
			System.out.println(path + "--|---" + method);

			if ("/".equals(path)) {
				System.out.println("-----");
			}

			if (null != path && path.startsWith("/resources")) {
				return new AuthorizationDecision(true);
			}

			List<Authority> authorityList = requestPathRepository.findByPath(path);
			if (authorityList == null || authorityList.size() == 0) {
				// 请求路径没有配置权限，表明该请求URL可以任意访问
				return new AuthorizationDecision(true);
			}

			String[] attributes = new String[authorityList.size()];
			for (int i = 0; i < authorityList.size(); i++) {
				attributes[i] = authorityList.get(i).getCode();
			}

			List<ConfigAttribute> list = SecurityConfig.createList(attributes);

			Iterator<ConfigAttribute> iterator = list.iterator();
			while (iterator.hasNext()) {
				ConfigAttribute ca = iterator.next();
				// 当前请求需要的权限
				String needRole = ca.getAttribute();
				// 当前用户所具有的权限
				Collection<? extends GrantedAuthority> grantedAuthorities = authentication.get().getAuthorities();
				for (GrantedAuthority authority : grantedAuthorities) {
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
