package higorpalmeira.com.github.taskboard.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import higorpalmeira.com.github.taskboard.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	boolean existsByProjectId(Long projectId);
	
	Page<Task> findByProjectId(Long projectId, Pageable pageable);
	
}
