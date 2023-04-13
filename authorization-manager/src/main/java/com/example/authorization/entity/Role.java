package com.example.authorization.entity;

import com.example.authorization.entity.audit.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 角色表
 *
 * @author : Tom
 * @date : Mon Aug 08 15:28:52 PDT 2022
 * @since : 1.0.0
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spring_role")
public class Role extends BaseEntity {

	/**
	 * can be used in sequence
	 */
	public static final String TABLE = "role";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1472459935068022498L;

	/**
	 * role name
	 */
	private String name;

	/**
	 * about the role
	 */
	private String descr;

	/**
	 * 0 disable 1 enable
	 */
	private Boolean enabled;

}
