package med.voll.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.model.enums.Especialidade;

public record DadosCadastroMedico(
		@NotBlank(message = "Nome é obrigatório")
		String nome,
		
		@NotBlank(message = "Email é obrigatório")
	    @Email(message = "Formato do email é inválido")
		String email, 
		
		@NotBlank
		String telefone,
		
		@NotBlank(message = "{crm.obrigatorio}")
	    @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM é inválido")
		String crm, 
		
		@NotNull
        Boolean ativo,
		
		@NotNull
		Especialidade especialidade, 
		
		@NotNull
		@Valid
		DadosCadastroEndereco endereco) {

}
