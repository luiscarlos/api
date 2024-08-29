package med.voll.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dtos.DadosAtualizacaoMedico;
import med.voll.api.dtos.DadosCadastroMedico;
import med.voll.api.dtos.DadosDetalhamentoMedico;
import med.voll.api.dtos.DadosListagemMedico;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/medicos")
public class MedicoResource {

	@Autowired
	private MedicoRepository medicoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(dados);
		medicoRepository.save(medico);

		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}

	/*
	 * @GetMapping public List<DadosListagemMedico> listar(){
	 * 
	 * return
	 * medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList(); }
	 */

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = medicoRepository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var medico = medicoRepository.getReferenceById(id);
		medico.excluir();

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity buscarPorId(@PathVariable Long id) {
		var medico = medicoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
}
