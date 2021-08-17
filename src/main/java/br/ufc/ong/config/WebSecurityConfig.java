package br.ufc.ong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
//@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }

    private static final String[] PUBLIC_MATCHERS = {
            "public/**/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/swagger-resources/**",
            //teste
            "/usuarios/**",
    };

    private static final String[] PUBLIC_MATCHERS_POST = {
            "/login",
            "/usuarios",
            "/pessoas",
            "/ongs"
    };

    private static final String[] ADMIN_MATCHERS_PUT = {
            "/usuarios/habilitar/**",

    };

    private static final String[] ADMIN_MATCHERS_GET = {
            "/usuarios/**",
            "/usuarios/listar",
            "/pessoas/listar",
            "/ongs/listar"
    };

    private static final String[] ADMIN_MATCHERS_POST = {

    };

    private static final  String[] ADMIN_MATCHERS_DELETE = {
            "/usuarios/**"
    };

    private static final String[] USUARIO_NORMAL_MATCHERS_GET = {
            "/ocorrencias/pessoa"
    };

    private static final String[] USUARIO_NORMAL_MATCHERS_POST = {
            "/ocorrencias",
            "/pessoas"
    };

    private static final String[] USUARIO_NORMAL_MATCHERS_DELETE = {
            "/ocorrencias/**"
    };

    private static final String[] USUARIO_NORMAL_MATCHERS_PUT = {

    };

    private static final String[] USUARIO_ONG_MATCHERS_DELETE = {

    };

    private static final String[] USUARIO_ONG_MATCHERS_POST = {
            "/solicitacoes_coletivas"
    };

    private static final String[] USUARIO_ONG_MATCHERS_GET = {
            "/ocorrencias/ong"
    };

    private static final String[] USUARIO_ONG_MATCHERS_PUT = {

    };


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()

                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

}
