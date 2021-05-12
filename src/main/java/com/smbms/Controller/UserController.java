package com.smbms.Controller;

import com.smbms.mapper.RoleMapper;
import com.smbms.pojo.Role;
import com.smbms.pojo.User;
import com.smbms.service.UserService;
import com.smbms.utilt.PageSupport;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService bean;
    @Autowired
    private RoleMapper roleMapper;

    @RequestMapping(value = "/log" )
    public String login(){
        return "jsp/frame";
    }


    //跳转密码修改页面
    @RequestMapping(value = "/updatePwd" )
    public String updatePwd(){

        return "jsp/pwdmodify";

    }


    //跳转密码修改页面
    @RequestMapping(value = "/adduser1" )
    public String adduser1(){

        return "jsp/useradd";

    }


    //跳转  用户信息查看页面
    @RequestMapping(value = "/user_view" )
    public String user_view(@RequestParam("id") Integer id,HttpSession session){
        User byId1 = bean.getById(id);
        session.setAttribute("user",byId1);
        Role byId = roleMapper.getById(byId1.getUserRole());
        session.setAttribute("RoleName",byId.getRoleName());

        return "jsp/userview";

    }




    //跳转用户管理页面  userList
    @RequestMapping(value = "/user_list" )
    public String user_list(HttpSession session,Model model){
//        List<Role> Rolegetlist = roleMapper.list();
//        session.setAttribute("roleList",Rolegetlist);
        List<User> getlist = bean.getlist();
        session.setAttribute("userList",getlist);

        //1.查询总记录数，计算总页数
        int count=bean.getUserCount(null,null);
        //对页码和每页大小做兼容性处理
        PageSupport pageSupport = new PageSupport(0, 5, count);
        //2.查询用户列表
        List<User> userlist=bean.getuserlist(null,null,1,5);

        //获取角色列表
        List<Role> Rolegetlist = roleMapper.list();

        //回显输入信息
        model.addAttribute("queryUserName",null);
        model.addAttribute("queryUserRole",null);


        session.setAttribute("userList",userlist);
        session.setAttribute("roleList",Rolegetlist);
        session.setAttribute("pageSupport",pageSupport);


        return "jsp/userlist";

    }


    //分页  userList
    @RequestMapping(value = "/user_list_2",method = RequestMethod.POST)
    public String user_list_2(@RequestParam("queryname") String queryname,
                              @RequestParam(defaultValue = "0") Integer queryUserRole,
                              @RequestParam(defaultValue = "0") Integer currentPageNo,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              Model model){
        System.out.println(queryname);


        int currentPageNo2 = 0;
        if (currentPageNo > 1) {
            currentPageNo2 = (currentPageNo-1) * pageSize;
        }



        //1.查询总记录数，计算总页数
        int count=bean.getUserCount(queryname,queryUserRole);
        //对页码和每页大小做兼容性处理
        PageSupport pageSupport = new PageSupport(currentPageNo, pageSize, count);



        //2.查询用户列表
        List<User> userlist=bean.getuserlist(queryname,queryUserRole,currentPageNo2,pageSize);

        //获取角色列表
        List<Role> Rolegetlist = roleMapper.list();


        //回显输入信息
        model.addAttribute("queryUserName",queryname);
        model.addAttribute("queryUserRole",queryUserRole);


        model.addAttribute("userList",userlist);
        model.addAttribute("roleList",Rolegetlist);
        model.addAttribute("pageSupport",pageSupport);

        return "jsp/userlist";

    }






    //登录
    @RequestMapping(value = "/log2",method = RequestMethod.POST)
    public String login2(@RequestParam("userCode") String userCode, @RequestParam("pwd") String pwd, HttpSession session){

        User user=bean.getByname_pwd(userCode,pwd);
        if (user != null) {
            session.setAttribute("user",user);
            return "redirect:log";
        }
        return "../login";
    }

    //修改密码
    @RequestMapping( value = "/userpwdModify",method = RequestMethod.POST)
    public String pwdModify(@RequestParam String newpassword, HttpSession session){
        User user = (User) session.getAttribute("user");
        user.setUserPassword(newpassword);
        int count=bean.update(user);
        if (count>0) {
            session.setAttribute("user",user);
            return "../login";
        }
        return "jsp/pwdmodify";
    }

    //验证原密码是否正确
    @RequestMapping( "/checkPwd")
    @ResponseBody
    public String checkPwd(@RequestParam String userpassword, HttpSession session,
                           HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (session.getAttribute("user") == null) {//登录已过期

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath+"/login.jsp");//重定向
        }else {
            User user = (User) session.getAttribute("user");
            String Password = user.getUserPassword();
            System.out.println(Password);
            System.out.println(userpassword);
            if (userpassword.equals(Password)) {
                System.out.println(Password+"\t"+userpassword);

                return "true";
            }else {
                return "false";
            }
        }
        return null;
    }
    //查询用户编码是否存在
    @RequestMapping(value="/userCode_add",method=RequestMethod.GET)
    @ResponseBody
    public String adds2(String userCode,HttpSession session) {
        System.out.println(userCode);


        User user=bean.getByuserCode(userCode);

        if (user==null) {

            return "false";
        }

        return "true";


    }





    //查找用户角色
    @RequestMapping( "/Rolegetlist")
    public String Rolegetlist2(HttpSession session){
        List<Role> Rolegetlist = roleMapper.list();
        session.setAttribute("Rolegetlist",Rolegetlist);
        return "user/usermodify";
    }


//    //添加用户
//    @RequestMapping( value="/user_add",method=RequestMethod.POST)
//    public String user_add(@ModelAttribute("user") User user   ,HttpSession session){
//        int add = bean.add(user);
//        System.out.println(user.toString()+"*");
//        if (add>0) {
//            return "redirect:user_list";
//
//        }
//
//
//        return "user/usermodify";
//    }




    //添加用户  2.0 版
    @RequestMapping("/user_add_2")
    public Object user_add_2(User user, HttpSession session,
                             @RequestParam(value = "idPicPath_1",required = false)MultipartFile file,
                             HttpServletRequest request
                             ) throws IOException {
        //获取项目实际路径
        String realPath = request.getServletContext().getRealPath("images/");
        // D:\Hunter\Dev-------

        //保存图片
        file.transferTo(new File(realPath+File.separator +file.getOriginalFilename()));   //a.jpg

        //给user的idPicPath
        user.setIdPicPath("images/"+file.getOriginalFilename());

        User user1 = (User) session.getAttribute("user");
        user.setCreationDate(new Date());
        user.setCreatedBy(user1.getId());



        bean.add(user);
        return "redirect:user_list";
    }







    //删除用户
    @RequestMapping( "/user_dele")
    @ResponseBody
    public String  user_dele(Integer id, HttpSession session){
        int dele = bean.dele(id);
        if (dele>0){
            return "true";
        }else if(dele<=0){
            return "false";
        }
        return null;
    }



    //查询用户
    @RequestMapping( "/user_select")
    public String user_select( HttpSession session){
        List<User> getlist = bean.getlist();
        session.setAttribute("user_select",getlist);
        return "user/user_select";
    }

    //查询用户  通过用户名和角色
    @RequestMapping( "/user_select2")
    public String user_select2(@RequestParam("userName") String userName,@RequestParam("userRole") Integer userRole, HttpSession session){
        List<User> getlist = bean.getlist2(userName,userRole);
        session.setAttribute("user_select",getlist);
        return "user/user_select";
    }




    @RequestMapping(value="/userRole_add",method=RequestMethod.GET)
    @ResponseBody
    public String userRole_add(HttpSession session) {
        List<User> getlist =bean.getlist();
        session.setAttribute("usergetlist",getlist);
        return "true";


    }











}
