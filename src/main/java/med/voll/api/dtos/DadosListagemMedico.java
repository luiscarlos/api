package med.voll.api.dtos;

import med.voll.api.model.Medico;
import med.voll.api.model.enums.Especialidade;

public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {

	
	public DadosListagemMedico(Medico medico) {
		this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}
}
