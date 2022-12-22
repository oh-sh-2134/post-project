package spring.postproject.config.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import spring.postproject.config.Security.Filter.CustomAuthenticationFilter;
import spring.postproject.config.Security.Hadler.CustomLoginFailureHandler;
import spring.postproject.config.Security.Hadler.CustomLoginSuccessHandler;
import spring.postproject.config.Security.Service.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity //기본필터체인등록
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                    // /post 요청에 대해서는 로그인을 요구함
                    .antMatchers("/post/**").authenticated()
                    // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
                    .antMatchers("/post/new").hasRole("NORMAL")
                    // 나머지 요청에 대해서는 로그인을 요구하지 않음
                    .anyRequest().permitAll()
                    .and()
                // 로그인하는 경우에 대해 설정함
                .formLogin()
                    // 로그인 페이지를 제공하는 URL을 설정함
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout()
                    .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/auth/login");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
        customAuthenticationFilter.setAuthenticationFailureHandler(customLoginFailureHandler());
//        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    //로그인 성공
    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler(){
        return new CustomLoginSuccessHandler();
    }

    //로그인 실패
    @Bean
    public CustomLoginFailureHandler customLoginFailureHandler() { return new CustomLoginFailureHandler();}

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(userDetailService,bCryptPasswordEncoder());
    }

}
