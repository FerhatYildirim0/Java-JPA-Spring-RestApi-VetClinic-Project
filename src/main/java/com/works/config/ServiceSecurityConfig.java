package com.works.config;

import com.works.utils.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServiceSecurityConfig extends WebSecurityConfigurerAdapter {

    final UserService userService;
    public ServiceSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // sql -> jpa query -> user control
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(userService.encoder());
    }

    // hangi yöntemle giriş yapılarak, rollere göre hangi servis kullanılcak?
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/agenda/**").permitAll()
        .antMatchers("/pet/**").hasRole("SUPERADMIN")
        .antMatchers("/customerR/**").hasRole("SUPERADMIN")
        .antMatchers("/buying/**").hasAnyRole("ADMIN","SUPERADMIN")
        .antMatchers("/sale/**").hasAnyRole("ADMIN","SUPERADMIN")
        .antMatchers("/swagger-ui/#/**").hasAnyRole("ADMIN","SUPERADMIN")
        .and()
        .csrf().disable()
        .formLogin().disable()
        .logout().logoutUrl("/admin/logout").invalidateHttpSession(true) ;
        http.headers().frameOptions().disable(); // h2-console for using

    }



    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

}
