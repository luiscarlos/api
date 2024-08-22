package med.voll.api.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dtos.DadosCadastroMedico;
import med.voll.api.dtos.DadosListagemMedico;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoResource {

	
	@Autowired
	private MedicoRepository  medicoRepository;
	
	
	  @PostMapping
	  @Transactional
      public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		  medicoRepository.save(new Medico(dados));
  }
	  
	 /* @GetMapping
	  public List<DadosListagemMedico> listar(){
		  
		  return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();
	  }*/
	  
	  @GetMapping
	    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10 , sort = {"nome"}) Pageable paginacao) {
	        return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
	  }
}
