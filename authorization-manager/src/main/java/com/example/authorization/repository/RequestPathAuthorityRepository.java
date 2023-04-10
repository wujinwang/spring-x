package com.example.authorization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.authorization.entity.RequestPathAuthority;

/**
 * 路径权限关联表
 * 
 * 
 * @author : Tom
 * @date : Mon Sep 05 13:44:31 PDT 2022
 * @since : 1.0.0
 */

@Repository
public interface RequestPathAuthorityRepository
		extends JpaRepository<RequestPathAuthority, Long>, JpaSpecificationExecutor<RequestPathAuthority> {

	/**
	 * @param requestPathId
	 * @return
	 */
	public List<RequestPathAuthority> findByRequestPathId(Long requestPathId);

	/**
	 * @param requestPathId
	 * @return
	 */
	public int deleteByRequestPathId(Long requestPathId);
}
