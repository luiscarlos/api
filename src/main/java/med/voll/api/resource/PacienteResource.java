package med.voll.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dtos.DadosAtualizacaoPaciente;
import med.voll.api.dtos.DadosCadastroPaciente;
import med.voll.api.dtos.DadosListagemPacientes;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("pacientes")
public class PacienteResource {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping
	public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dadosCadastroPaciente){
		
		pacienteRepository.save(new Paciente(dadosCadastroPaciente));
		
	}
	
	@GetMapping
	public Page<DadosListagemPacientes> listar(@PageableDefault(size = 10, sort= {"nome"}) Pageable pageable){
		return pacienteRepository.findAllByAtivoTrue(pageable).map(DadosListagemPacientes::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		var paciente = pacienteRepository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
	  @Transactional
	  public void deletar(@PathVariable Long id ) {
		  var paciente = pacienteRepository.getReferenceById(id); 
		  paciente.excluir();
	  }

}
