package ma.projet.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /**
         * Custom configurations as per our requirement
         */
    	http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/home", "/myBalance", "/myLoans", "/myCards").authenticated()
                .requestMatchers("/notices", "/contact").permitAll()
                .requestMatchers("/login").permitAll() // Allow access to the login page
        ).httpBasic(Customizer.withDefaults())
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/home")
        .permitAll()
        .and()
    .logout()
        .logoutSuccessUrl("/login")
        .permitAll();

return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
}
