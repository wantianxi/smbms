package com.smbms.Controller;

import com.smbms.pojo.Bill;
import com.smbms.service.billServlice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class billController {

    @Autowired
    private billServlice billServlice;
    //跳转用户管理页面
    @RequestMapping(value = "/bill_list" )
    public String bill_list(HttpSession session){
        List<Bill> lis2t = billServlice.getLis2t();
        session.setAttribute("billList",lis2t);
        return "jsp/billlist";

    }

}
