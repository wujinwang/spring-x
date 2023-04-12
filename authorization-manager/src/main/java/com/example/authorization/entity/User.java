package com.example.authorization.entity;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.authorization.entity.audit.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Tom
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spring_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }), @UniqueConstraint(columnNames = { "email" }) })
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = -2908371066969846094L;

	/**
	 * can be used in sequence
	 */
	public static final String TABLE = "user";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 
	 */
	private Long supplierId = 0l;

	/**
	 * same as springsecurity
	 */
	private String username;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private String alias;

	/**
	 * 
	 */
	private String name;

	/**
	 * job title
	 */
	private String title;

	/**
	 * 
	 */
	private Boolean gender;

	/**
	 * 
	 */
	private String mobile;

	/**
	 * 
	 */
	private String email;

	/**
	 * 
	 */
	private String telephone;

	/**
	 * 
	 */
	private String descr;

	/**
	 * 
	 */
	private Date birthdate;

	/**
	 * 
	 */
	private String postcode;
	
	/**
	 * 
	 */
	private String address1;

	/**
	 * 
	 */
	private String address2;

	/**
	 * 
	 */
	private String city;

	/**
	 * 
	 */
	private String province;

	/**
	 * 
	 */
	private String country;
	
	/**
	 * 
	 */
	private String photoId;

	/**
	 * 
	 */
	private String language;

	/**
	 * 
	 */
	private String token;

	/**
	 * 
	 */
	private String loginIp;

	/**
	 * 
	 */
	private Date loginDate;

	/**
	 * 
	 */
	private Date lastLoginDate;

	/**
	 * 
	 */
	private String lastLoginIp;

	/**
	 * 
	 */
	private Date lastFailedLoginDate;

	/**
	 * 
	 */
	private Integer failedLoginAttempts = 0;

	/**
	 * 
	 */
	private String resetKey;

	/**
	 * 
	 */
	private Date keyExpiredDate;

	/**
	 * 
	 */
	private String verifyCode;

	/**
	 * 
	 */
	private Date verifyCreatedDate;

	/**
	 * 
	 */
	private boolean enabled;

	/**
	 * 
	 */
	private boolean isAccountNonExpired;

	/**
	 * 
	 */
	private Date accountExpiredDate;

	/**
	 * 
	 */
	private boolean isAccountNonLocked;

	/**
	 * 
	 */
	private Date accountLockoutDate;

	/**
	 * 
	 */
	private boolean isCredentialsNonExpired;

	@Transient
	private List<GrantedAuthority> authorities;

}