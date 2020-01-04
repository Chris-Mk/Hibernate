package com.mkolongo.residentEvil.config;

import com.mkolongo.residentEvil.web.filters.JwtAuthenticationFilter;
import com.mkolongo.residentEvil.web.filters.JwtTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtPropertiesConfig jwtPropertiesConfig;

//    @Qualifier("userServiceImpl")
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /*      for form based authentication
                   .csrf()
                   .csrfTokenRepository(csrfTokenRepository())
                   .and()
               */

                .csrf().disable()

                // jwt based authentication
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtPropertiesConfig))
                .addFilterAfter(new JwtTokenVerifier(jwtPropertiesConfig), JwtAuthenticationFilter.class)

                // access rules
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/login").permitAll()
                .antMatchers("/", "/users/login", "/users/register").anonymous()
                .antMatchers("/viruses", "/users/home", "/json").authenticated()
                .antMatchers("/viruses/edit/*", "/viruses/delete/*", "/viruses/add").hasAnyRole("MODERATOR", "ADMIN")
                .antMatchers("/users").hasRole("ADMIN");

            /*      form based authentication
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/users/home")
                .and()
                .logout();
            */
    }

    private CsrfTokenRepository csrfTokenRepository() {
        var httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        httpSessionCsrfTokenRepository.setSessionAttributeName("_csrf");
        return httpSessionCsrfTokenRepository;
    }
}
