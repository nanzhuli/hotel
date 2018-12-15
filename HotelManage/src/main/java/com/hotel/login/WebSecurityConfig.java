package com.hotel.login;

import com.hotel.service.EmployService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService EmployService() {
        return new EmployService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //身份验证管理生成器
        //此时需要对密码进行加密
        auth.userDetailsService(EmployService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //HTTP请求安全处理
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**","/dist/**","/js/**",
                        "/layui/**","/src/js/**").permitAll()
                .anyRequest().authenticated()
                // .antMatchers("Employ/**","order/**","Event/**").hasRole("WORKER")
//                .antMatchers("/css/**", "/dist/**", "/js/**", "/layui/**", "/src/**").permitAll()
                .and().formLogin().loginPage("/login")
                //设置默认登录成功跳转页面
                .defaultSuccessUrl("/").successHandler(new LoginSuccessHandle()).permitAll()
                .failureUrl("/login?error").permitAll()
                .and()
                .logout()
                //默认注销行为为logout，可以通过下面的方式来修改
                .logoutUrl("/custom-logout")
                //设置注销成功后跳转页面，默认是跳转到登录页面
                .logoutSuccessUrl("/login")
                .permitAll();
        http.headers().frameOptions().sameOrigin();
    }
}
