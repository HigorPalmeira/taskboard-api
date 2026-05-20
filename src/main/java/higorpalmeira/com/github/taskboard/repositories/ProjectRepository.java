package higorpalmeira.com.github.taskboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import higorpalmeira.com.github.taskboard.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
