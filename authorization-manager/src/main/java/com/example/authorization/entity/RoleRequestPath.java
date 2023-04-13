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
 * @date : Thu Sep 08 17:08:26 PDT 2022
 * @since : 1.0.0
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spring_role_request_path")
public class RoleRequestPath extends BaseEntity {

	/**
	 * can be used in sequence
	 */
	public static final String TABLE = "spring_role_request_path";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1472459937752395888L;

	/**
	 * 角色ID
	 */
	private Long roleId = 0l;

	/**
	 * 请求URL ID
	 */
	private Long requestPathId = 0l;

}
