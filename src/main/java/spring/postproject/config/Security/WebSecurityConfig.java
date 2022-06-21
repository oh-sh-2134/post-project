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

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                    // /about 요청에 대해서는 로그인을 요구함
                    .antMatchers("/post").authenticated()
                    // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
//                    .antMatchers("/admin").hasRole("ADMIN")
                    // 나머지 요청에 대해서는 로그인을 요구하지 않음
                    .anyRequest().permitAll()
                    .and()
                // 로그인하는 경우에 대해 설정함
                .formLogin()
                    // 로그인 페이지를 제공하는 URL을 설정함
                    .loginPage("/member/loginForm")
                    // 로그인 성공 URL을 설정함
                    .successForwardUrl("/post/home")
                    // 로그인 실패 URL을 설정함
                    .failureForwardUrl("/member/loginForm")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }

}
