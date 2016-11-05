package com.pchudzik.blog.repositorytesting.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("from #{#entityName} where lower(login) = lower(:login) and password = :password")
	User login(
			@Param("login") String login,
			@Param("password") String password);

	@Query("select case " +
			"  when count(u) > 0 then true " +
			"  else false " +
			"end " +
			"from #{#entityName} u " +
			"join u.roles roles " +
			"where " +
			"  u.login = :login " +
			"  and :role in(roles)")
	boolean userHasRole(
			@Param("login") String login,
			@Param("role") Role role);
}
