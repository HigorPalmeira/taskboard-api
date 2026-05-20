package higorpalmeira.com.github.taskboard.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectDTO(
		Long id,
		
		@NotBlank(message = "O nome do projeto é obrigatório e não pode ser vazio.")
		@Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
		String name,
		
		String description
		) {

}
