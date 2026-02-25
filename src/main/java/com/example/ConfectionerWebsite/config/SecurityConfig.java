package com.example.ConfectionerWebsite.config;

import com.example.ConfectionerWebsite.services.DbUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DbUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // если делаешь много fetch JSON — проще отключить, но лучше потом включить
                .authorizeHttpRequests(auth -> auth
                        // публичное
                        .requestMatchers("/users/create", "/styles.css", "/js/**", "/images/**").permitAll()

                        // бюджет (для сайдбара) — пусть видят все авторизованные
                        .requestMatchers("/budget").authenticated()

                        // отчёты
                        .requestMatchers("/reports", "/reports/export")
                        .hasAnyAuthority("админ", "директор", "бухгалтер")

                        // кредиты
                        .requestMatchers("/loans", "/loan/create", "/loan/pay")
                        .hasAnyAuthority("админ", "директор", "бухгалтер")

                        // сотрудники + зарплаты
                        .requestMatchers("/employees", "/employees/update", "/employees/create",
                                "/salary/pay-all", "/salaryPayments", "/salaryPayment/create")
                        .hasAnyAuthority("админ", "директор", "бухгалтер")

                        // закупка сырья — бухгалтер
                        .requestMatchers("/purchaseMaterials", "/purchaseMaterial/create")
                        .hasAnyAuthority("админ", "бухгалтер")

                        // сырьё (CRUD) — бухгалтер/менеджер (как хочешь)
                        .requestMatchers("/rawMaterials", "/rawMaterial/create", "/rawMaterial/edit/**", "/rawMaterial/delete/**")
                        .hasAnyAuthority("админ", "бухгалтер", "менеджер")

                        // продукты (CRUD)
                        .requestMatchers("/products", "/create", "/edit/**", "/delete/**")
                        .hasAnyAuthority("админ", "директор", "менеджер")

                        // продажи
                        .requestMatchers("/productSales", "/product/sale/**")
                        .hasAnyAuthority("админ", "директор", "менеджер")

                        // производство
                        .requestMatchers("/productions", "/production/create")
                        .hasAnyAuthority("админ", "директор", "менеджер", "кондитер")

                        // всё остальное — только авторизованные
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/products", true)
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"))
                .exceptionHandling(ex -> ex.accessDeniedPage("/access-denied"))
                .build();
    }
}