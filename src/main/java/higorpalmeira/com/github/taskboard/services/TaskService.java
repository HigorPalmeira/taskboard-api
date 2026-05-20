package higorpalmeira.com.github.taskboard.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import higorpalmeira.com.github.taskboard.dtos.TaskDTO;
import higorpalmeira.com.github.taskboard.entities.Project;
import higorpalmeira.com.github.taskboard.entities.Task;
import higorpalmeira.com.github.taskboard.exceptions.ResourceNotFoundException;
import higorpalmeira.com.github.taskboard.repositories.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository repository;
	
	public TaskService(TaskRepository repository) {
	
		this.repository = repository;
	
	}
	
	public Page<TaskDTO> findAll(Pageable pageable) {
		
		Page<Task> list = this.repository.findAll(pageable);
		
		return list.map(entity -> new TaskDTO(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getStatus(), entity.getProject().getId()));
		
	}
	
	public TaskDTO findById(Long id) {
		
		Task task = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada."));
		
		return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getProject().getId());
		
	}
	
	public Page<TaskDTO> findByProjectId(Long id, Pageable pageable) {
		
		Page<Task> list = this.repository.findByProjectId(id, pageable);
		
		return list.map(entity -> new TaskDTO(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getStatus(), entity.getProject().getId()));
		
	}
	
	public TaskDTO save(TaskDTO dto) {
		
		Task entity = new Task();
		entity.setTitle(dto.title());
		entity.setDescription(dto.description());
		entity.setStatus(dto.status());
		
		Project project = new Project();
		project.setId(dto.projectId());
		entity.setProject(project);
		
		entity = this.repository.save(entity);
		
		return new TaskDTO(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getStatus(), entity.getProject().getId());
		
	}
	
	public TaskDTO update(Long id, TaskDTO updatedDto) {
		
		Task oldTask = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada."));
		
		if (updatedDto.title() != null && !updatedDto.title().isBlank()) {
			oldTask.setTitle(updatedDto.title());
		}
		
		if (updatedDto.description() != null && !updatedDto.description().isBlank()) {
			oldTask.setDescription(updatedDto.description());			
		}
		
		if (updatedDto.status() != null) {
			
			/*
			 * && Arrays.asList(TaskStatus.values()).contains(updatedTask.getStatus())
			 * 
			 * Não é necessário, porque o Spring Boot utiliza o Jackson que altera o JSON direto para um objeto
			 * se tiver algo errado na requisição, o erro é acionado antes de chegar até aqui.
			 * */
			oldTask.setStatus(updatedDto.status());			
		}
		
		if (updatedDto.projectId() != null) {
			
			/**
			 * && updatedTask.getProject().getId() != 0
			 * 
			 * O tipo que estamos utilizando é o Long, e ele não é um tipo primitivo é uma classe Wrapper,
			 * tem que verificar o null.
			 * */
			Project project = new Project();
			project.setId(updatedDto.projectId());
			
			oldTask.setProject(project);			
		}
		
		oldTask = this.repository.save(oldTask);
		
		return new TaskDTO(oldTask.getId(), oldTask.getTitle(), oldTask.getDescription(), oldTask.getStatus(), oldTask.getProject().getId());
		
	}
	
	public void deleteById(Long id) {
		
		this.repository.deleteById(id);
		
	}
	
}
