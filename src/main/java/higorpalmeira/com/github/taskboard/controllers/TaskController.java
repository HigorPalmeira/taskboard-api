package higorpalmeira.com.github.taskboard.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import higorpalmeira.com.github.taskboard.dtos.TaskDTO;
import higorpalmeira.com.github.taskboard.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tarefas")
public class TaskController {
	
	private final TaskService service;
	
	public TaskController(TaskService service) {
		
		this.service = service;
		
	}
	
	@Operation(summary = "Listar todas as tarefas", description = "Lista todas as tarefas do banco de dados. As tarefas são divididas em páginas.")
	@GetMapping
	public ResponseEntity<Page<TaskDTO>> findAll(Pageable pageable) {
		
		Page<TaskDTO> page = this.service.findAll(pageable);
		
		return ResponseEntity.ok(page);
		
	}
	
	@Operation(summary = "Buscar tarefa pelo ID", description = "Busca uma tarefa do banco de dados pelo ID.")
	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
		
		TaskDTO dto = this.service.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
		
	}
	
	@Operation(summary = "Listar tarefas pelo ID do projeto", description = "Lista tarefas do banco de dados pelo ID do projeto. As tarefas são divididas em páginas.")
	@GetMapping("/project/{id}")
	public ResponseEntity<Page<TaskDTO>> findByProjectId(@PathVariable Long id, Pageable pageable) {
		
		Page<TaskDTO> page = this.service.findByProjectId(id, pageable);
		
		return ResponseEntity.ok(page);
		
	}
	
	@Operation(summary = "Criar uma nova tarefa", description = "Salva uma tarefa no banco de dados. O nome não pode estar vazio.")
	@PostMapping
	public ResponseEntity<TaskDTO> insert(@RequestBody TaskDTO task) {
		
		TaskDTO dto = this.service.save(task);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		
	}
	
	@Operation(summary = "Atualizar uma tarefa", description = "Atualiza uma tarefa do banco de dados pelo ID.")
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO updatedTask) {
		
		TaskDTO dto = this.service.update(id, updatedTask);
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
		
	}
	
	@Operation(summary = "Deletar uma tarefa", description = "Deleta uma tarefa do banco de dados pelo ID.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		
		this.service.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}

}
