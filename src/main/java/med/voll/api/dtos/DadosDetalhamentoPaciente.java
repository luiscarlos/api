package med.voll.api.dtos;

import med.voll.api.model.Endereco;
import med.voll.api.model.Paciente;

public record DadosDetalhamentoPaciente(String nome, String email, String telefone, String cpf, Endereco endereco) { 
    public DadosDetalhamentoPaciente(Paciente paciente) { 
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco()); 
    }
} 
