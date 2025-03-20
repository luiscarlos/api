package med.voll.api.dtos;

import med.voll.api.model.enums.UserRole;

public record RegisterDTO(String login, String senha, UserRole role ) {

}
