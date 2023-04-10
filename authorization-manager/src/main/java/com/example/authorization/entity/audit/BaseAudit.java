package com.example.authorization.entity.audit;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = { "createdDate", "updatedDate" }, allowGetters = true)
public abstract class BaseAudit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5221214098711748378L;

	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Los_Angeles")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false, insertable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdDate;

	@LastModifiedDate
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Los_Angeles")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true, updatable = true, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date lastModifiedDate;

}
