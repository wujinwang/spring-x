package com.example.authorization.entity;

import com.example.authorization.entity.audit.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 角色权限中间表
 *
 * @author : Tom
 * @date : Thu Aug 18 15:42:57 PDT 2022
 * @since : 1.0.0
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spring_role_authority")
public class RoleAuthority extends BaseEntity {

	/**
	 * can be used in sequence
	 */
	public static final String TABLE = "spring_role_authority";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1472459935932866692L;

	/**
	 * 角色ID
	 */
	private Long roleId = 0l;

	/**
	 * 权限ID
	 */
	private Long authorityId = 0l;

}
