package com.docsconsole.tutorials.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
       /* auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("userpass")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("adminpass")).roles("ADMIN");*/

        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT U.USER_NAME, U.USER_PASSWORD, U.USER_ENABLED FROM APP_USERS U WHERE U.USER_NAME= ?")
                .authoritiesByUsernameQuery("SELECT U.USER_NAME, R.ROLE_NAME FROM APP_USERS U JOIN APP_USER_ROLES UR ON U.USER_ID = UR.USER_ID JOIN APP_ROLES R ON UR.ROLE_ID = R.ROLE_ID WHERE U.USER_NAME = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin").access("hasRole('ROLE_SUPER_ADMIN')")
                .antMatchers("/home").access("hasAnyRole('ROLE_USER', 'ROLE_SUPER_ADMIN')")
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}