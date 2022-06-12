package com.exfi.reporthandler.config;

//import com.nimbusds.jose.shaded.json.JSONArray;
//import com.nimbusds.jose.shaded.json.JSONObject;
//import org.keycloak.adapters.KeycloakConfigResolver;
//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
//import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
//import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
//import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfigKeycloak extends KeycloakWebSecurityConfigurerAdapter {
//
//    @Autowired
//    public KeycloakClientRequestFactory keycloakClientRequestFactory;
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public KeycloakRestTemplate keycloakRestTemplate() {
//        return new KeycloakRestTemplate(keycloakClientRequestFactory);
//    }
//
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }
//
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
//                .oauth2ResourceServer(resourceServerConfigurer -> resourceServerConfigurer
//                        .jwt(jwtConfigurer -> jwtConfigurer
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                );    }
//
//    @Bean
//    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter());
//        return jwtAuthenticationConverter;
//    }
//
//    @Bean
//    public Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter() {
//        JwtGrantedAuthoritiesConverter delegate = new JwtGrantedAuthoritiesConverter();
//
//        return new Converter<>() {
//            @Override
//            public Collection<GrantedAuthority> convert(Jwt jwt) {
//                Collection<GrantedAuthority> grantedAuthorities = delegate.convert(jwt);
//
//                if (jwt.getClaim("realm_access") == null) {
//                    return grantedAuthorities;
//                }
//                Object obj  = jwt.getClaim("realm_access");
//                System.out.println(obj.getClass());
//                JSONObject realmAccess = jwt.getClaim("realm_access");
//                if (realmAccess.get("roles") == null) {
//                    return grantedAuthorities;
//                }
//                JSONArray roles = (JSONArray) realmAccess.get("roles");
//
//                System.out.println("test_" + roles);
//                final List<SimpleGrantedAuthority> keycloakAuthorities = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
//                grantedAuthorities.addAll(keycloakAuthorities);
//
//                return grantedAuthorities;
//            }
//        };
//    }
//}

