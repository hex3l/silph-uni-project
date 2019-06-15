package net.hex3l.silph.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	}
	
	/* @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/", "/catalog/photos");
		
	} */
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/", "/catalog/photos").permitAll()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/anonymous*").anonymous()
        .antMatchers("/login*").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        //.loginPage("/login")
        //.loginProcessingUrl("/perform_login")
        .defaultSuccessUrl("/admin", true)
        .failureUrl("/login?error=true")
       // .failureHandler(authenticationFailureHandler())
        .and()
        .logout()
        .logoutUrl("/perform_logout")
        .deleteCookies("JSESSIONID");
        //.logoutSuccessHandler(logoutSuccessHandler());
		
		
		
			
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
