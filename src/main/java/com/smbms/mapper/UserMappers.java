package com.smbms.mapper;

import com.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface UserMappers {
    List<User> getlist2(@Param("userName") String userName, @Param("userRole") Integer userRole);
    List<User> getlist();
    User getById(Integer id);
    int add(User User);
    int Update(User user);

    User getByname_pwd(@Param("userCode") String name,@Param("pwd") String pwd);

    User getByuserCode(String userCode);
    int dele(Integer id);

    int getUserCount(@Param("queryname") String queryname,@Param("queryUserRole") Integer queryUserRole);

    List<User> getuserlist(
           @Param("queryname") String queryname,
           @Param("queryUserRole") Integer queryUserRole,
           @Param("startIndex") Integer currentPageNo,
           @Param("pageSize") Integer pageSize);
}
