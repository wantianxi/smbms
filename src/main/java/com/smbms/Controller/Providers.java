package com.smbms.Controller;

import com.smbms.pojo.Provider;
import com.smbms.pojo.User;
import com.smbms.service.ProvidersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

//标记控制台
@Controller
public class Providers {

    @Autowired
    private ProvidersService bean;

    @RequestMapping(value = "/select",method = RequestMethod.GET)
    public String getlist(@ModelAttribute Providers provider, Model model){

        List<Provider> getlist = bean.getlist();
        model.addAttribute("provider",getlist);
        return "jsp/d1";

    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String adds(@ModelAttribute("provider") Provider provider, Model model, HttpSession session){
        provider.setCreationDate(new Date());
        bean.add(provider);
        model.addAttribute("provider",provider);
        List<Provider> getlist = bean.getlist();
        model.addAttribute("provider",getlist);

        return "jsp/d1";
    }

    @RequestMapping(value="/add2",method=RequestMethod.GET)
    @ResponseBody
    public String adds2(String proCode) {
        System.out.println(proCode);
        Provider provider1=bean.getByProviderCode(proCode);
        if (provider1==null) {

            return "false";
        }

        return "true";


    }

    //跳转添加供应商页面  addProv
    @RequestMapping(value="/addProv")
    public String addProv(){
        return "jsp/provideradd";
    }


    //跳转供应商管理页面
    @RequestMapping(value = "/Prov_list" )
    public String Prov_list(@ModelAttribute Providers provider,HttpSession session){
        List<Provider> getlist = bean.getlist();
        session.setAttribute("providerList",getlist);
//        for (Provider provider1 : getlist) {
//            System.out.println(provider.toString());
//        }
        return "jsp/providerlist";

    }




    @RequestMapping(value="/getById",method=RequestMethod.POST)
    public String getById(@RequestParam("id") int id, Model model, HttpSession session){
        Provider byId = bean.getById(id);

        if (byId != null) {
            model.addAttribute("provider",byId);
            return "jsp/ceshi";
        }else {
            return "error/500";
        }
    }

}
