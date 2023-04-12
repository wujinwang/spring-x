package com.example.authorization.entity;

import com.example.authorization.entity.audit.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户角色中间表
 *
 * @author : Tom
 * @date : Thu Aug 18 15:38:12 PDT 2022
 * @since : 1.0.0
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spring_user_role")
public class UserRole extends BaseEntity {

	/**
	 * can be used in sequence
	 */
	public static final String TABLE = "spring_user_role";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1472459935932582130L;

	/**
	 * 用户ID
	 */
	private Long userId = 0l;

	/**
	 * 角色ID
	 */
	private Long roleId = 0l;

}
