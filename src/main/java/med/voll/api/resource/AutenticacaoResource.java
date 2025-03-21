package med.voll.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dtos.AuthenticationDTO;
import med.voll.api.dtos.DadosAutenticacao;
import med.voll.api.dtos.DadosTokenJWT;
import med.voll.api.dtos.RegisterDTO;
import med.voll.api.dtos.LoginResponseDTO;
import med.voll.api.infra.security.TokenService;
import med.voll.api.model.Usuario;
import med.voll.api.repository.UsuarioRepository;


@RestController
@RequestMapping("/login")
public class AutenticacaoResource {
  
	 @Autowired
	    private AuthenticationManager authenticationManager;
	
	 @Autowired
	 private TokenService tokenService;
	 
	 @Autowired
	 private UsuarioRepository usuarioRepository;
	

	    @PostMapping
	    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
	    	var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
	        var authentication = authenticationManager.authenticate(authenticationToken);

	        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

	        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	    }

	    @PostMapping("/register")
	    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
	        if(this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

	        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
	        Usuario newUser = new Usuario(data.login(), encryptedPassword, data.role());

	        this.usuarioRepository.save(newUser);

	        return ResponseEntity.ok().build();
	    }
	}
