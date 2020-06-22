package com.Fontys.s44.BramHouben.Fun4Backend.security;


import com.Fontys.s44.BramHouben.Fun4Backend.authenticate.ApplicationUserService;
import com.Fontys.s44.BramHouben.Fun4Backend.jwt.JwtUsernamePasswordAuthFilter;
import com.Fontys.s44.BramHouben.Fun4Backend.jwt.JwtVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private MyUserDetailsService myUserDetailsService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService);
//    }

    private final PasswordEncoder passwordEncoder;

    private final ApplicationUserService applicationUserService;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Moet t veilig maken
//        http.requiresChannel()
//                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto")!= null)
//                .requiresSecure();
       http.headers().defaultsDisabled().cacheControl();
//        http.headers().frameOptions().sameOrigin();
       http.headers().xssProtection().block(true);
//        http.requiresChannel().anyRequest().requiresSecure();



        http.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                addFilter(new JwtUsernamePasswordAuthFilter(authenticationManager())).
                addFilterAfter(new JwtVerifier(), JwtUsernamePasswordAuthFilter.class).

//        csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
//                and().
        authorizeRequests().
                antMatchers("/", "index", "/css/*", "/js/*").permitAll().
                // antMatchers("/user/**").hasRole(ADMIN.name()).

                  //      antMatchers(HttpMethod.GET, "/api/v1/order/**").permitAll().
                antMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll().
                antMatchers(HttpMethod.GET, "/api/v1/product/getProduct/**").permitAll().
                //todo dit moet allemaal user worden
             //   antMatchers(HttpMethod.GET, "/AllOrders").permitAll().
              //  antMatchers(HttpMethod.POST, "/order/sendOrder").permitAll().
              //  antMatchers(HttpMethod.POST, "/uploadPicture").permitAll().
                antMatchers(HttpMethod.GET, "/api/v1/picture/downloadFile/**").permitAll().
         //       antMatchers(HttpMethod.GET,"/order/getDetails").permitAll().
                antMatchers(HttpMethod.GET,"/logout").permitAll().
                //todo kijken wat we hier mee doen soms werkt t wel en soms niet goed na kijken ws gwn de preautherize gebruiken

//
        antMatchers(HttpMethod.POST, "/api/v1/user/register").permitAll().
                // todo Hier moet nog iets komen dat basic users producten kunnen zien en de rest moet afgeschermd zijn
                        anyRequest().
                authenticated().
                and().cors().configurationSource(ConfigCors())
                .and().
                logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).
               invalidateHttpSession(true).
                deleteCookies("Authorization")
        ;

    }

    @Bean
    CorsConfigurationSource ConfigCors() {
        CorsConfiguration configuration = new CorsConfiguration();
        // configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowCredentials(true);
        //the below three lines will add the relevant CORS response headers
        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedOrigin("https://funproject.azurewebsites.net");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(applicationUserService);
        return daoAuthenticationProvider;
    }


}
