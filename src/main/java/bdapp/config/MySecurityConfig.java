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
                .requestMatchers(new AntPathRequestMatcher("/staff/**")).hasRole("airat")
                .requestMatchers(new AntPathRequestMatcher("/department/**")).hasRole("airat")
                .requestMatchers(new AntPathRequestMatcher("/maintenance/**")).hasRole("airat")
                .requestMatchers(new AntPathRequestMatcher("/machine/**")).hasRole("airat")
                .requestMatchers(new AntPathRequestMatcher("/machineType/**")).hasRole("airat")
                .requestMatchers(new AntPathRequestMatcher("/machineStatus/**")).hasRole("airat")
                .requestMatchers(new AntPathRequestMatcher("/machineStorage/**")).hasRole("airat")
                .requestMatchers(new AntPathRequestMatcher("/factory/**")).hasRole("airat")
                .requestMatchers(new AntPathRequestMatcher("/manufacture/**")).hasAnyRole("airat","khamit")
                .requestMatchers(new AntPathRequestMatcher("/material/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/materialStorage/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/product/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/compositionOfProduct/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/productStorage/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/customer/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/order/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/deliveryCompany/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/materialDelivery/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/process/**")).hasRole("khamit")
                .requestMatchers(new AntPathRequestMatcher("/processStage/**")).hasRole("khamit")
                .anyRequest().authenticated()
        ).formLogin(form->{
            form.loginPage("/login").permitAll();
        }).httpBasic(Customizer.withDefaults());



        return http.build();
    }
}



