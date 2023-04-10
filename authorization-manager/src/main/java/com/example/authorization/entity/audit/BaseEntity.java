package com.example.authorization.entity.audit;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Tom
 */

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@MappedSuperclass
//@JsonIgnoreProperties(value = { "createdBy", "updatedBy" }, allowGetters = true)
public abstract class BaseEntity extends BaseAudit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6987582165177290567L;

	/**
	 * primary key
	 */
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@CreatedBy
	public String createdBy;

	@LastModifiedBy
	public String lastModifiedBy;
}
