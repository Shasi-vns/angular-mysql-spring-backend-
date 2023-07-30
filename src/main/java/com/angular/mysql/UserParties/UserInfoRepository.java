package com.angular.mysql.UserParties;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;





public interface UserInfoRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
    public User findByName(@Param("username") String username);
	
	
	@Query("SELECT u FROM User u WHERE u.mobile = :username")
	 public User getUserByMobile(@Param("username") String username);
}
