package bdapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class MySecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        InMemoryUserDetailsManager manager =new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("khamit").password("khamit").roles("khamit").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("airat").password("airat").roles("airat").build());
        return manager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((user) -> user
                .requestMatchers(new AntPathRequestMatcher("/")).hasAnyRole("khamit", "airat")
                .requestMatchers(new AntPathRequestMatcher("/staff/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/department/**")).hasRole("airat")
                .anyRequest().authenticated()
        ).formLogin(form->{
            form.loginPage("/login").permitAll();
        }).httpBasic(Customizer.withDefaults());



        return http.build();
    }
}



