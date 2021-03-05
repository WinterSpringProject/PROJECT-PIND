package io.spring.pind.config;

import io.spring.pind.security.handler.LoginSuccessHandler;
import io.spring.pind.security.service.AuthService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/member/*").permitAll()
                .antMatchers("/project/*").permitAll();

        http.headers().frameOptions().sameOrigin();
        http.formLogin();
        http.csrf().disable();;
        http.logout();
        http.oauth2Login();
//        http.oauth2Login().successHandler((successHandler())); // oauth 로그인 별도 핸들링 필요할 때 사용
//        http.rememberMe().tokenValiditySeconds(60 * 60 * 24).userDetailsService(authService);
    }

    @Bean
    public LoginSuccessHandler successHandler(){
        return new LoginSuccessHandler(passwordEncoder());
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().
//    }
}
