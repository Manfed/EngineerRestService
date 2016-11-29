package pg.eti.inz.engineer.rest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Konfiguracja polityk bezpieczeństwa
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static String REALM = "ENGINEER_REALM";

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService restUserDetailsService;


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(restUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/**", "/routes/**").hasAuthority("USER")
                .antMatchers(HttpMethod.POST, "/routes/**").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/user/**", "/routes/**").hasAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/user/**").hasAuthority("USER")
            .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint());
    }

    @Bean
    public EngineerBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new EngineerBasicAuthenticationEntryPoint();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers(HttpMethod.POST, "/user");
    }
}
