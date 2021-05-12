package com.smbms.service;

import com.smbms.mapper.UserMappers;
import com.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    @Qualifier("userMappers")
    private UserMappers userMappers;


    public  List<User> getlist2(String userName, Integer userRole){
        return userMappers.getlist2(userName,userRole);
    }
    public  List<User> getlist(){
        return userMappers.getlist();
    }
    public   int Update(User user){

        return userMappers.Update(user);
    }

    public User getByname_pwd(String userCode, String pwd) {
        return userMappers.getByname_pwd(userCode,pwd);
    }
    public User getById(Integer id) {
        return userMappers.getById(id);
    }
    public int update(User user){
        return userMappers.Update(user);
    }

    public User getByuserCode(String userCode) {
        return userMappers.getByuserCode(userCode);
    }

    public int add(User user) {
        return userMappers.add(user);
    }
    public int dele(Integer id){
        return userMappers.dele(id);
    }

    public int getUserCount(String queryname, Integer queryUserRole) {
        return userMappers.getUserCount(queryname,queryUserRole);
    }

    public List<User> getuserlist(String queryname, Integer queryUserRole, Integer currentPageNo, Integer pageSize) {
        return userMappers.getuserlist(queryname,queryUserRole,currentPageNo,pageSize);
    }
}
