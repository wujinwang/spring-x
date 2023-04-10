package com.example.authorization.entity;

import com.example.authorization.entity.audit.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 路径权限关联表
 *
 * @author : Tom
 * @date : Mon Sep 05 13:44:31 PDT 2022
 * @since : 1.0.0
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spring_request_path_authority")
public class RequestPathAuthority extends BaseEntity {

	/**
	 * can be used in sequence
	 */
	public static final String TABLE = "spring_request_path_authority";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1472459937480961559L;

	/**
	 * 请求路径id
	 */
	private Long requestPathId = 0l;

	private Long authorityId;
}
