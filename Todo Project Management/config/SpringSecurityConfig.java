package com.risrchanish.todo.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity  // This is used to use method level security.
public class SpringSecurityConfig {
	
	
	private UserDetailsService userDetailsService;
	
	
	public SpringSecurityConfig(UserDetailsService userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf((csrf) -> csrf.disable())
					.authorizeHttpRequests((authorize) -> {
	
						authorize.anyRequest().authenticated();
						
					}).httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	
	/*
	 * Without using method level authentication.
	 
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf((csrf) -> csrf.disable())
					.authorizeHttpRequests((authorize) -> {
						
						authorize.requestMatchers(HttpMethod.POST, "/todo/**").hasRole("ADMIN");
						authorize.requestMatchers(HttpMethod.PUT, "/todo/**").hasRole("ADMIN");
						authorize.requestMatchers(HttpMethod.DELETE, "/todo/**").hasRole("ADMIN");
						authorize.requestMatchers(HttpMethod.GET, "/todo/**").hasAnyRole("ADMIN","USER");
						authorize.requestMatchers(HttpMethod.PATCH, "/todo/**").hasAnyRole("ADMIN","USER");
						
						authorize.anyRequest().authenticated();
						
					}).httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	*/

	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	

	/*
	 //Above we are using database authentication so commenting the below along with we have to comment the application.properties file 
	  // user based authentication. 
	@Bean
	public UserDetailsService userDetailsService()
	{
		UserDetails anish = User.builder()
				
							.username("anish")
							.password(passwordEncoder().encode("anish123"))
							.roles("USER")
							.build();
		
		UserDetails admin = User.builder()
							
							.username("admin")
							.password(passwordEncoder().encode("admin123"))
							.roles("ADMIN")
							.build();
		
		return new InMemoryUserDetailsManager(anish,admin);
	}
	*/
}
