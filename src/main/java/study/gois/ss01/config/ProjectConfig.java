package study.gois.ss01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import study.gois.ss01.security.CustomAuthenticationProvider;

@Configuration
public class ProjectConfig {

    private final CustomAuthenticationProvider authenticationProvider;

    public ProjectConfig(CustomAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // App uses HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        http.authenticationProvider(authenticationProvider);

        // All the requests require authentication.
        http.authorizeHttpRequests(
                c -> c.anyRequest().authenticated()
                // With a slight change, you can make all the endpoints accessible without the need for credentials
                // c -> c.anyRequest().permitAll()
        );

        /*var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        var userDetailsService = new InMemoryUserDetailsManager(user);

        http.userDetailsService(userDetailsService);*/

        return http.build();
    }

    /*@Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

}