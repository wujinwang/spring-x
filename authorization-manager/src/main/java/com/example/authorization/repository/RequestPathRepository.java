package com.example.authorization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.authorization.entity.Authority;
import com.example.authorization.entity.RequestPath;

/**
 * 角色表
 * 
 * 
 * @author : Tom
 * @date : Wed Aug 31 09:46:26 PDT 2022
 * @since : 1.0.0
 */

@Repository
public interface RequestPathRepository extends JpaRepository<RequestPath, Long>, JpaSpecificationExecutor<RequestPath> {
	/**
	 * Select
	 * id,parentId,name,nameZhCn,nameZhTw,url,clazz,priority,enabled,createdDate,createdBy,lastModifiedDate,lastModifiedBy
	 * from request_path
	 */

	public List<RequestPath> findByEnabledOrderByPriorityAsc(boolean enabled);

	public List<RequestPath> findByParentIdAndEnabled(Long requestPathId, boolean enabled);

	// @Query("SELECT ra FROM RequestPathAuthority ra LEFT JOIN RequestPath rp ON
	// rp.id = ra.requestPathId WHERE rp.enabled=1 and rp.url = :path")
	// List<RequestPathAuthority> findByPath(@Param("path") String path);
	@Query("SELECT au FROM Authority au LEFT JOIN RequestPathAuthority ra ON ra.authorityId = au.id LEFT JOIN RequestPath rp ON rp.id = ra.requestPathId WHERE rp.enabled=1 and rp.url = :path")
	List<Authority> findByPath(@Param("path") String path);
}
