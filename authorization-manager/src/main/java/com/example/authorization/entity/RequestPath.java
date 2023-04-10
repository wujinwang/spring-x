package com.example.authorization.entity;

import com.example.authorization.entity.audit.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 角色表
 *
 * @author : Tom
 * @date : Wed Aug 31 09:46:26 PDT 2022
 * @since : 1.0.0
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "spring_request_path")
public class RequestPath extends BaseEntity {

	/**
	 * can be used in sequence
	 */
	public static final String TABLE = "spring_request_path";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1472459937034675862L;

	/**
	 * parent menu id
	 */
	private Long parentId = 0l;

	/**
	 * Menu name
	 */
	private String name;

	@Column(name = "name_zh_cn")
	private String nameZhCn;

	@Column(name = "name_zh_tw")
	private String nameZhTw;

	/**
	 * menu url
	 */
	private String url;

	/**
	 * css icon class
	 */
	private String clazz;

	/**
	 * menu priority
	 */
	private Integer priority = 0;

	/**
	 * is enabled
	 */
	private Boolean enabled;
}
