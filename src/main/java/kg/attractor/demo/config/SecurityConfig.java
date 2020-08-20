package kg.attractor.demo.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

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
                .invalidateHttpSession(true);

        http.authorizeRequests()

                .antMatchers("/orders", "/orders/add-order","/orders/{id}/edit",
                        "/orders/{id}/delete", "/users", "/users/add-user", "/orders/assign-courier")
                .hasAnyRole("ADMIN")
                .antMatchers("users/{id}/orders", "/orders/{id}", "/orders/{id}/state")
                .hasAnyRole("ADMIN", "USER")
                .antMatchers("/orders/{id}/state")
                .hasRole("USER");

        http.authorizeRequests()
                .antMatchers("/login")
                .anonymous()
                .anyRequest()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        String fetchAdminsQuery = "select email, password, enabled"
                + " from users"
                + " where email = ?";

        String fetchRolesQuery = "select email, role" +
                " from users" +
                " where email = ?";

        auth.jdbcAuthentication()
                .usersByUsernameQuery(fetchAdminsQuery)
                .authoritiesByUsernameQuery(fetchRolesQuery)
                .dataSource(dataSource);
    }
}
