package med.voll.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dtos.DadosCadastroPaciente;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteResource {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente){
		
		pacienteRepository.save(new Paciente(dadosCadastroPaciente));
		
	}

}
