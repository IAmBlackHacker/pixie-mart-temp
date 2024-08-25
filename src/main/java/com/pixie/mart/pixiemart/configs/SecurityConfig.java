package com.pixie.mart.pixiemart.configs;

import com.pixie.mart.pixiemart.constants.url.InspectorURLConstant;
import com.pixie.mart.pixiemart.constants.url.URLConstant;
import com.pixie.mart.pixiemart.filters.JWTTokenFilter;
import com.pixie.mart.pixiemart.models.UserKind;
import com.pixie.mart.pixiemart.models.responses.PixieResponse;
import com.pixie.mart.pixiemart.repositories.user.UserRepositoryManager;
import com.pixie.mart.pixiemart.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ALL_SUB_URL = "/**";

    @Autowired
    private JWTTokenFilter jwtTokenFilter;

    @Autowired
    private UserRepositoryManager userRepositoryManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> userRepositoryManager.getUserByUsername(username))
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        // Set unauthorized requests exception handler
        http = http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream()
                    .println(GsonUtil.toJsonString(PixieResponse.getDefaultFailureResponse(ex.getMessage())));
        }).and();

        http.authorizeRequests().antMatchers(URLConstant.AUTH_URL + ALL_SUB_URL).permitAll()
                .antMatchers(URLConstant.VERSION_URL).permitAll().antMatchers(URLConstant.BRANDS_BASE_URL + ALL_SUB_URL)
                .permitAll().antMatchers(URLConstant.SELL_ITEMS_BASE_URL + ALL_SUB_URL).permitAll()
                .antMatchers(URLConstant.TONGUE_TWISTER_URL + ALL_SUB_URL).permitAll().antMatchers(URLConstant.ITEM_URL)
                .hasRole(UserKind.SITE_ADMIN).antMatchers(InspectorURLConstant.INSPECTOR_BASE_URL + ALL_SUB_URL)
                .hasAnyAuthority(UserKind.SITE_ADMIN, UserKind.INSPECTOR).anyRequest().authenticated().and().logout()
                .permitAll();

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Used by spring security if CORS is enabled.
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:8000");
        config.addAllowedOrigin("http://localhost");
        config.addAllowedOrigin("capacitor://localhost");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
