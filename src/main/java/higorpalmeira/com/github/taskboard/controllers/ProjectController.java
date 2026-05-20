package higorpalmeira.com.github.taskboard.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import higorpalmeira.com.github.taskboard.dtos.ProjectDTO;
import higorpalmeira.com.github.taskboard.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/projects")
@Tag(name = "Projetos", description = "Endpoints para gerenciamento de projetos")
public class ProjectController {

	private final ProjectService service;
	
	public ProjectController(ProjectService service) {
		
		this.service = service;
		
	}
	
	@Operation(summary = "Listar todos os projetos", description = "Lista todos os projetos do banco de dados. Os projetos são divididos em páginas.")
	@GetMapping
	public ResponseEntity<Page<ProjectDTO>> findAll(Pageable pageable) {
		
		Page<ProjectDTO> list = service.findAll(pageable);
		
		return ResponseEntity.ok(list);
		
	}
	
	@Operation(summary = "Buscar projeto pelo ID.", description = "Busca um projeto no banco de dados pelo ID.")
	@GetMapping("/{id}")
	public ResponseEntity<ProjectDTO> findById(@PathVariable Long id) {
		
		ProjectDTO dto = service.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
		
	}
	
	@Operation(summary = "Criar um novo projeto", description = "Salva um novo projeto no banco de dados. O nome não pode estar vazio.")
	@PostMapping
	public ResponseEntity<ProjectDTO> insert(@Valid @RequestBody ProjectDTO dto) {
		
		ProjectDTO savedDto = service.save(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
		
	}
	
	@Operation(summary = "Deletar um projeto pelo ID", description = "Deleta um projeto do banco de dados pelo ID. O projeto não pode possuir tarefas.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		
		service.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
}
