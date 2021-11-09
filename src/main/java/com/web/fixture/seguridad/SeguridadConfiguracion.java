/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class SeguridadConfiguracion extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private UsuarioServicio usuarioServicio;

    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {

//        auth
//                .userDetailsService(usuarioServicio) //Le decimos a Spring Security cual es el servicio que tiene que utilizar para identificar al usuario
//                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("=============================================================");
        http
                .authorizeRequests()
                .antMatchers("/css/", "/js/", "/img/", "/*").permitAll()
                .and().formLogin()
                .loginPage("/login") // Que formulario esta mi login
                .loginProcessingUrl("/logincheck")
                .usernameParameter("username") // Como viajan los datos del logueo
                .passwordParameter("password")// Como viajan los datos del logueo
                .defaultSuccessUrl("/inicio") // A que URL viaja 
                .permitAll()
                .and().logout() // Aca configuro la salida
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll().and().csrf().disable();
    }
}