package kg.attractor.demo.config;

import kg.attractor.demo.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/", true);

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .and().exceptionHandling().accessDeniedPage("/accessdenied");;

        http.authorizeRequests()

                .antMatchers("/orders", "/orders/add-order","/orders/{id}/edit",
                        "/orders/{id}/delete", "/users", "/users/add-user", "/orders/assign-courier",
                        "users/{id}/edit", "/orders/search")
                .hasAnyRole("ADMIN")
                .antMatchers("users/{id}/orders", "/orders/{id}", "/orders/{id}/state")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers("/orders/{id}/state", "/{id}/orders/search")
                .hasRole("USER");

        http.authorizeRequests()
                .antMatchers("/login")
                .anonymous()
                .anyRequest()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }
}
