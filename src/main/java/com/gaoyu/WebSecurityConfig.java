package com.gaoyu;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**","/css/**","/js/**","/logo/**","/img/","/tinymce/**","/jquery/**","static/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {//"/**"
        http.authorizeRequests().antMatchers("/register","/**","/modifyUserInfo/**","/h2/**").permitAll()
                .antMatchers("/waigua").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .antMatcher("/**")
                .csrf().disable();

        //http.logout().logoutSuccessUrl("/login");
        //http.formLogin().defaultSuccessUrl("/main");
        http.csrf().ignoringAntMatchers("/h2/**");
        //http.csrf().ignoringAntMatchers("/addArticle/**");
        http.csrf().ignoringAntMatchers("/uploadImage/**");

        http.headers().frameOptions().sameOrigin();


                //.and().authorizeRequests().anyRequest().authenticated(); // 所有请求都要经过登录认知
    }
}
