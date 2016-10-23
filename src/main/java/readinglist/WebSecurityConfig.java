package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Created by jialihan on 16/10/9.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("jiali").password("123").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf()
                  .csrfTokenRepository(csrfTokenRepository());
        http
                .authorizeRequests()
                .antMatchers("/","/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/readingList")
                .permitAll()
                .and()
                .logout()
                .permitAll();


    }


    private CsrfTokenRepository csrfTokenRepository()
    {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }






    /*
    //将reader/account实体 配置为合法用户
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(buildUserDetailsService());
    }


    @Bean
    UserDetailsService buildUserDetailsService() {
        return username -> {
            Account account = accountRepository.findByUsername(username);
            User user = new User(account.getUsername(), account.getPassword(),
                    true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER", "write"));
            return user;
        };
    }
    */
    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

}


