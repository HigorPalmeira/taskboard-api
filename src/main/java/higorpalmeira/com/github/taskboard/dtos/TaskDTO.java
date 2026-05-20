package higorpalmeira.com.github.taskboard.dtos;

import higorpalmeira.com.github.taskboard.entities.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskDTO(
		Long id,
		
		@NotBlank(message = "O nome da tarefa é obrigatório e não pode ser vazio.")
		@Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres.")
		String title,
		
		String description,
		
		TaskStatus status,
		
		Long projectId
		) {

}
