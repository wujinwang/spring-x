package com.example.authorization.entity;

import com.example.authorization.entity.audit.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 权限表
 *
 * @author : Tom
 * @date : Tue Sep 20 15:31:17 PDT 2022
 * @since : 1.0.0
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spring_authority")
public class Authority extends BaseEntity {

	/**
	 * can be used in sequence
	 */
	public static final String TABLE = "spring_authority";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1472459938783367141L;

	/**
	 * 权限编码
	 */
	private String code;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 权限目录名称
	 */
	private String content;

	/**
	 * 是否被删除
	 */
	private Boolean enabled;

}
