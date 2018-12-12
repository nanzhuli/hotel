package com.hotel.login;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

class LoginSuccessHandle implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException,ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String path = request.getContextPath() ;
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        if (roles.contains("ROLE_ADMIN")){
            response.sendRedirect(basePath+"adminHotel");
            return;
        }
        else if (roles.contains("ROLE_WORKER")){
            response.sendRedirect(basePath+"workerHotel");
            return;
        }
        else if (roles.contains("ROLE_SERVER")){
            response.sendRedirect(basePath+"serverHotel");
            return;
        }
        else {
            response.sendRedirect(basePath+"/403");
        }
    }

    }