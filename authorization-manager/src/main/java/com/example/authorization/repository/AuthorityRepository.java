package com.example.authorization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.authorization.entity.Authority;

/**
 * 权限表
 * 
 * 
 * @author : Tom
 * @date : Tue Sep 20 15:31:17 PDT 2022
 * @since : 1.0.0
 */

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>, JpaSpecificationExecutor<Authority> {

	/**
	 * Select
	 * id,name,content,enabled,createdDate,createdBy,lastModifiedDate,lastModifiedBy
	 * from authority
	 */

	public List<Authority> findByEnabled(boolean b);

}
