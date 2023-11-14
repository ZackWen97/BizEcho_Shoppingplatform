package com.BizEcho.shoppingplatform.repository;

import com.BizEcho.shoppingplatform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 这里可以定义一些根据用户名、邮箱等查找用户的方法，例如：
    Optional<User> findByUsername(String username);
    User findByEmail(String email);

    // 可以根据需要添加更多的查询方法
    //by phone number
    User findByPhoneNumber(String phoneNumber);
    //by id
    User findByIdNumber(String idNumber);
    //by region
    List<User> findByRegion(String region);
    //by date
    List<User> findByRegistrationDateBetween(Date startDate, Date endDate);

    //by logindate
    List<User> findByLastLoginDateAfter(Date sinceDate);
    //by isActive
    List<User> findByIsActive(Boolean isActive);

    //by the role
    List<User> findByRole(String role);

}
