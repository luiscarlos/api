package med.voll.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dtos.DadosAtualizacaoPaciente;
import med.voll.api.dtos.DadosCadastroPaciente;
import med.voll.api.dtos.DadosDetalhamentoPaciente;
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
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
	    var paciente = new Paciente(dados);
	    pacienteRepository.save(paciente);

	    var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
	    return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPacientes>>  listar(@PageableDefault(size = 10, sort= {"nome"}) Pageable pageable){
		var page =  pacienteRepository.findAllByAtivoTrue(pageable).map(DadosListagemPacientes::new);
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
	    var paciente = pacienteRepository.getReferenceById(dados.id());
	    paciente.atualizarInformacoes(dados);

	    return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}
	
	@DeleteMapping("/{id}")
	  @Transactional
	  public ResponseEntity deletar(@PathVariable Long id ) {
		  var paciente = pacienteRepository.getReferenceById(id); 
		  paciente.excluir();
		  
		  return ResponseEntity.noContent().build();
	  }

	
	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable Long id) {
		var paciente = pacienteRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}
}
