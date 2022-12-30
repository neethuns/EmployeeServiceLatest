//package com.maveric.employee.config;
//
//
//
//import com.maveric.employee.filter.JWTFilter;
//import com.maveric.employee.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//@CrossOrigin(value = "*")
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigref extends WebSecurityConfigurerAdapter {
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    JWTFilter jwtFilter;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService);
//    }
//
////    @Bean
////    PasswordEncoder passwordEncoder(){
////        return NoOpPasswordEncoder.getInstance();
////    }
//
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder(){
////
////        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        return bCryptPasswordEncoder;
////    }
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                httpBasic().disable().
//                csrf().disable(). //post,put
//                authorizeRequests().
//                antMatchers("/api/v1/auth/login").permitAll().
//                antMatchers("/api/v1/auth/signup").permitAll().
//                anyRequest().
//                authenticated().
//                and().
//                sessionManagement().
//                sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//    }
//
//}
