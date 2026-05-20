package higorpalmeira.com.github.taskboard.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import higorpalmeira.com.github.taskboard.dtos.ProjectDTO;
import higorpalmeira.com.github.taskboard.entities.Project;
import higorpalmeira.com.github.taskboard.exceptions.BusinessRuleException;
import higorpalmeira.com.github.taskboard.repositories.ProjectRepository;
import higorpalmeira.com.github.taskboard.repositories.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

	@InjectMocks
	private ProjectService service;
	
	@Mock
	private ProjectRepository repository;
	
	@Mock
	private TaskRepository taskRepository;
	
	@Test
	public void findById_ShouldReturnProjectDTO_WhenIdExists() {
		
		// Arrange
		Long existingId = 1L;
		Project mockProject = new Project();
		mockProject.setId(existingId);
		mockProject.setName("Projeto Teste");
		
		// Treinando dublê
		when(repository.findById(existingId)).thenReturn(Optional.of(mockProject));
		
		// Act
		ProjectDTO result = service.findById(existingId);
		
		// Assert
		assertNotNull(result);
		assertEquals(existingId, result.id());
		assertEquals("Projeto Teste", result.name());
		
		// verifica se o dublê foi realmente chamado 1 vez
		verify(repository, times(1)).findById(existingId);
		
	}
	
	@Test
	public void deleteById_ShouldThrowException_WhenProjectHasTasks() {
		
		// Arrange
		Long existingId = 1L;
		
		// Treinando dublê
		when(taskRepository.existsByProjectId(existingId)).thenReturn(true);
		
		// Act & Assert
		assertThrows(BusinessRuleException.class, () -> {
			service.deleteById(existingId);
		});
		
		// verifica se o dublê foi realmente chamado 1 vez
		verify(taskRepository, never()).deleteById(existingId);
		
	}
	
}
