package higorpalmeira.com.github.taskboard.services;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import higorpalmeira.com.github.taskboard.dtos.ProjectDTO;
import higorpalmeira.com.github.taskboard.entities.Project;
import higorpalmeira.com.github.taskboard.exceptions.BusinessRuleException;
import higorpalmeira.com.github.taskboard.exceptions.ResourceNotFoundException;
import higorpalmeira.com.github.taskboard.repositories.ProjectRepository;
import higorpalmeira.com.github.taskboard.repositories.TaskRepository;

@Service
public class ProjectService {
	
	private final ProjectRepository repository;
	private final TaskRepository taskRepository;
	
	public ProjectService(ProjectRepository repository, TaskRepository taskRepository) {
		
		this.repository = repository;
		this.taskRepository = taskRepository;
		
	}
	
	public Page<ProjectDTO> findAll(Pageable pageable) {
		
		Page<Project> page = this.repository.findAll(pageable);
		
		return page.map(entity -> new ProjectDTO(entity.getId(), entity.getName(), entity.getDescription()));
		
	}
	
	public ProjectDTO findById(Long id) {	
	
		Project project = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado."));
		
		return new ProjectDTO(project.getId(), project.getName(), project.getDescription());
		
	}
	
	public ProjectDTO save(ProjectDTO dto) {
		
		Project entity = new Project();
		entity.setName(dto.name());
		entity.setDescription(dto.description());
		
		entity = this.repository.save(entity);
		
		return new ProjectDTO(entity.getId(), entity.getName(), entity.getDescription());
		
	}

	public void deleteById(Long id) {
		
		if (this.taskRepository.existsByProjectId(id)) {
			
			throw new BusinessRuleException("Não é possível deletar um projeto que possui tarefas.");
			
		}
		
		this.repository.deleteById(id);
		
	}
}
