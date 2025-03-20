package med.voll.api.model;

public class teste {
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * Na classe de tratamento de erros, precisa adicionar mais métodos:

@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity tratarErroBadCredentials() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
}

@ExceptionHandler(AuthenticationException.class)
public ResponseEntity tratarErroAuthentication() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
}

@ExceptionHandler(AccessDeniedException.class)
public ResponseEntity tratarErroAcessoNegado() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
}

-----------------------------------------------------------------


E na classe de configurações de segurança, precisa fazer um tratamento específico também:

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeHttpRequests()
            .requestMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
            .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}

@Bean
public AuthenticationEntryPoint authenticationEntryPoint() {
    return new UnauthorizedEntryPoint();
}

------------------------------------------------------------------------------

E criar a classe de tratamento de erro de autenticação:

public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }

}
	 */

}
