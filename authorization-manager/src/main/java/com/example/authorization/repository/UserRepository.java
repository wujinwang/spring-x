package com.example.authorization.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.authorization.entity.Authority;
import com.example.authorization.entity.User;
import com.example.authorization.entity.UserRole;

/**
 * 
 * 
 * 
 * @author : Tom
 * @date : Thu Aug 18 15:21:15 PDT 2022
 * @since : 1.0.0
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	/**
	 * Select
	 * id,supplierId,username,password,alias,name,title,gender,mobile,email,telephone,descr,birthdate,street,city,province,photoUuid,photoPath,photoFilename,token,loginIp,loginDate,lastLoginDate,lastLoginIp,lastFailedLoginDate,failedLoginAttempts,resetKey,keyExpiredDate,verifyCode,verifyCreatedDate,enabled,isAccountNonExpired,accountExpiredDate,isAccountNonLocked,accountLockoutDate,isCredentialsNonExpired,createdDate,createdBy,lastModifiedDate,lastModifiedBy
	 * from user
	 */

	public Optional<User> findByEmail(String email);

	public Optional<User> findByUsernameOrEmail(String username, String email);

	public List<User> findByIdIn(List<Long> userIds);

	public Optional<User> findByUsername(String username);

	public Boolean existsByUsername(String username);

	public Boolean existsByEmail(String email);

	// @Query("SELECT sa FROM User su,UserRole sur,Role sr,RoleAuthority sra,RequestPathAuthority sa WHERE sa.id = sra.authorityId AND sra.roleId = sr.id AND sr.id = sur.roleId AND sur.userId = su.id AND su.enabled = 1 AND su.username =:username")
	// public List<RequestPathAuthority> findAuthorityByUserame(@Param("username") String username);

	@Query(" SELECT sa FROM User su,UserRole sur,Role sr,RoleAuthority sra,Authority sa WHERE sa.id = sra.authorityId AND sa.enabled = 1 AND sra.roleId = sr.id AND sr.id = sur.roleId AND sr.enabled = 1 AND sur.userId = su.id AND su.enabled = 1 AND su.username =:username")
	List<Authority> findAuthorityByUserame(@Param("username") String username);

	@Modifying
	@Query("update User set name=:name,email=:email,mobile=:mobile,language=:language,descr=:descr,postcode=:postcode,address1=:address1,address2=:address2,city=:city,province=:province,country=:country where id=:id")
	public int updateBasicInfo(@Param("id") Long id, @Param("name") String name, @Param("email") String email, @Param("mobile") String mobile, @Param("language") String language, @Param("descr") String descr, @Param("postcode") String postcode, @Param("address1") String address1, @Param("address2") String address2,
			@Param("city") String city, @Param("province") String province, @Param("country") String country);

	@Query("SELECT sur FROM UserRole sur WHERE sur.userId =:id")
	public List<UserRole> findRoleById(@Param("id") Long userId);

}
