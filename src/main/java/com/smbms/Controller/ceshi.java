package com.smbms.Controller;

import com.smbms.ceshi.use;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ceshi {

    @RequestMapping("/a1")
    @ResponseBody//返回字符串
    public String a1(){
        use use=new use("张飞",18);
        return use.toString();
    }
}
