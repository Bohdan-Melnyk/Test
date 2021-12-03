package com.example.testoauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@Lazy
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
    private final String clientid = "tutorialspoint";
    private final String clientSecret = "{noop}my-secret-key";
    private final String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEApoaQFFxCQODjkrCPW9++hyFS89QQuuOpQggJYprLBcqlQUuM\n" +
            "ONqO5knzdz824cBNwF5LUwp+pezhLmnCqRLw0uNYJTVCP0lKxL5ZVEPVXCQ/UX0L\n" +
            "Z/3X+EEzSoYrdx93WsIrImN7Pri62Sl0mSHexfEO6XOTwAZ6DIHjMXlOqKrdHSSg\n" +
            "RBwoIWxDDpf7uqXlrc9GK402Yys9FmaPY95pda/HKohubJy1R4zYKhCGSBo8Vl4v\n" +
            "+J6hQgPC9yIoOV9n6FW6UcPZp+8rpTY2L1HT7+tvqb5A5Nkg8i8Y3L1yUD8ODWkl\n" +
            "CCvX68WhGSzabaLxBkRBsmLrwDjfxTJp8uiT9QIDAQABAoIBAFkT6CSMCYDNC88f\n" +
            "FUCvGisMv2Pgz6Y5PAQval3NscaCVQ3tjK+o8gHsEwl5zjDKfozlo+Ni3MaDRR1f\n" +
            "w5pPoHUe2brll29lFFLUA9LYilxEo35CpoRVPF72+anmxX9uXJk2/n3huz6c8I5T\n" +
            "56Q2gwVdYn2srp9sl4LzRAXl/xanmXRcLP8h+K7cVHBe1p7hKrPIoP+MRzrBF/8L\n" +
            "4fV8I9QLlQWpCcOSLMyQkI2i9vdPlVUFXas0vgWNpPmniiye4Yg7HqN4StRrf/S3\n" +
            "E255B5h1yJZJTgLL167KCcNJRmhVwPc2hCC8YRtXtMX7KDcS9fO0rz4NaVUO6J6r\n" +
            "A50ttU0CgYEA5kNhKFDKRhSmYSJ72CUyMd13sLXzWFGMtVUx17+DlsB9NadFPvoP\n" +
            "L0YaztmTuOBSmZ2aEQuAfVRfNMmCZA24U11FE3q2Cl2UYalD4VOZgv+J1yUcTteg\n" +
            "wX3n39/oQd/Inl7346HQzX22RmEkcVXrwabX0kHHrVKtaw0KDzj/Y5cCgYEAuSNu\n" +
            "y3GbkMSnifPbEUOfdh3EWOY7K/D5psCZyE2q1yV0SDtAy1s+eOvxzjUVzDaM5dVo\n" +
            "hZf9oOCcRyVJzcPC18O78eWlBochS2VGrit7DHBf88/mLyhG0iBalaNVmMOfAZOA\n" +
            "F2jC/67N6rH1MG6CsfxcqZr4Wpb9yrcnD1AgRlMCgYEAo1sd7FoUhk9UEglCzv7k\n" +
            "c1fo9+a9cuS37FU6fRMTKbn5mjzG9Xy1lRYpIqJdGIWcU/rpODput3tJ58slxBGv\n" +
            "uQSsYrtltkjZYceRrqN+ft8HTSi4wn5bP6ow/DR/M2ytiR1aw5Ui6wXKcHeuQPFx\n" +
            "xfq1liawoBfNi+q+PjpzhJUCgYBThyj1oMwPBKqSEvNDVr8bC1MKyNTpfk8kbMD1\n" +
            "HhV1++/6psEU/pWw+MKfxlOPTw1CpIYDwA8uBih78NbbfvbpYeXpvTI3P8BFw+uf\n" +
            "qP5bHlgsKCAyJnnNU8uKt6Zmxdt3WpTv+tP/h15gBOoab8GHi9RCsShbtsPKMdft\n" +
            "kLj/AQKBgFONyD0XQIiGFuOZRNxrWp/Q/UjbHG9TX8+vk5cgGq212R34rzw3BNB9\n" +
            "QMi7Rjc1pVey7GXPKwv2Y+D7vNooWgFRdTmO+nLChTEcXBw2+2gH8P8kf5dk/82p\n" +
            "WOXKLURn9+H7HpScgB8UB5F/KQ9akPjDbSXjMFAthTc7zUe7pNH/\n" +
            "-----END RSA PRIVATE KEY-----";
    private final String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApoaQFFxCQODjkrCPW9++\n" +
            "hyFS89QQuuOpQggJYprLBcqlQUuMONqO5knzdz824cBNwF5LUwp+pezhLmnCqRLw\n" +
            "0uNYJTVCP0lKxL5ZVEPVXCQ/UX0LZ/3X+EEzSoYrdx93WsIrImN7Pri62Sl0mSHe\n" +
            "xfEO6XOTwAZ6DIHjMXlOqKrdHSSgRBwoIWxDDpf7uqXlrc9GK402Yys9FmaPY95p\n" +
            "da/HKohubJy1R4zYKhCGSBo8Vl4v+J6hQgPC9yIoOV9n6FW6UcPZp+8rpTY2L1HT\n" +
            "7+tvqb5A5Nkg8i8Y3L1yUD8ODWklCCvX68WhGSzabaLxBkRBsmLrwDjfxTJp8uiT\n" +
            "9QIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    private final AuthenticationManager authenticationManager;

    @Autowired
    public OAuth2Config(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)  {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }
}
