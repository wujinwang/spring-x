package com.example.authorization.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.authorization.entity.Authority;
import com.example.authorization.entity.SecurityUser;
import com.example.authorization.entity.User;
import com.example.authorization.repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService {
	private final Map<String, SecurityUser> userMap = new HashMap<>();

	@Autowired
	UserRepository userRepository;

	public CustomUserDetailService(BCryptPasswordEncoder bCryptPasswordEncoder) {
		userMap.put("user", createUser("user", bCryptPasswordEncoder.encode("userPass"), false, "USER"));
		userMap.put("admin", createUser("admin", bCryptPasswordEncoder.encode("adminPass"), true, "ADMIN", "USER"));
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		// return Optional.ofNullable(userMap.get(username)).orElseThrow(() -> new UsernameNotFoundException("User " + username + " does not exists"));
		System.out.println("----usernameOrEmail0---" + username);
		// Let people login with either username or email
		User userPrincipal = userRepository.findByUsernameOrEmail(username, username).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));
		List<Authority> authorityList = userRepository.findAuthorityByUserame(username);
		// UserPrincipal userPrincipal=new UserPrincipal(user.getId(), user.getName(),
		// user.getUsername(), user.getEmail(), user.getPassword(), authorities);

		List<GrantedAuthority> authorities = authorityList.stream().map(authority -> new SimpleGrantedAuthority(authority.getCode())).collect(Collectors.toList());
		userPrincipal.setAuthorities(authorities);
		// return UserPrincipal.create(user, authorities);
		return userPrincipal;
	}

	private SecurityUser createUser(String userName, String password, boolean withRestrictedPolicy, String... role) {
		return SecurityUser.builder().withUserName(userName).withPassword(password).withGrantedAuthorityList(Arrays.stream(role).map(SimpleGrantedAuthority::new).collect(Collectors.toList())).withAccessToRestrictedPolicy(withRestrictedPolicy);
	}
}