package com.smbms.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Component
public class QJExceptionHanDler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("error/500");
        if (e instanceof RuntimeException) {
            mv.addObject("username","服务器异常，请稍后重试!!!!!!!!");
        }else if (e instanceof SQLException){
            mv.addObject("username","该功能正在升级维护，请稍后或者重试!!!!!!!!");
        }else{
            mv.addObject("username","服务器异常，请联系客服!!!!!!!!");
        }



        return mv;
    }
}
