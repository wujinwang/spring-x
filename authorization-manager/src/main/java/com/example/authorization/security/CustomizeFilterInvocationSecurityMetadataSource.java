package com.example.authorization.security;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.authorization.entity.Authority;
import com.example.authorization.repository.RequestPathRepository;

@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	RequestPathRepository requestPathRepository;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) o).getRequestUrl();
		// 获取请求地址
		// String requestUrl = ((FilterInvocation) o).getFullRequestUrl();
		URI uri = UriComponentsBuilder.fromUriString(requestUrl).replaceQuery(null).build(Collections.emptyMap());
		// System.out.println("------------------uri.getPath-------------------" + uri.getPath());
		// 查询具体某个接口的权限
		String path = uri.getPath();
		if ("/".equals(path)) {
			System.out.println("-----");
		}

		if (null != path && path.startsWith("/resources")) {
			return null;
		}

		List<Authority> authorityList = requestPathRepository.findByPath(path);
		if (authorityList == null || authorityList.size() == 0) {
			// 请求路径没有配置权限，表明该请求URL可以任意访问
			return null;
		}

		String[] attributes = new String[authorityList.size()];
		for (int i = 0; i < authorityList.size(); i++) {
			attributes[i] = authorityList.get(i).getCode();
		}
		return SecurityConfig.createList(attributes);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
