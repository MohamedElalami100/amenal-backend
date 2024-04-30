package com.amenal.amenalbackend.budget.infrastructure.adapter.in.web;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amenal.amenalbackend.budget.core.domain.Project;
import com.amenal.amenalbackend.budget.core.port.in.ProjectUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
	@Autowired
	private ProjectUseCase projectUseCase;

	@GetMapping
	public ResponseEntity<List<Project>> getAllProjects() {
		return ResponseEntity.ok(projectUseCase.findAllProjects());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Project> getProjectById(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.ok(projectUseCase.findProjectById(id));
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		} 
	}

	@PostMapping
	public ResponseEntity<Project> saveProject(@RequestBody Project project) {
		return ResponseEntity.status(HttpStatus.CREATED).body(projectUseCase.saveProject(project));
	}

	@PutMapping
	public ResponseEntity<Project> updateProject(@RequestBody Project project) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(projectUseCase.updateProject(project));
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Project> deleteProject(@PathVariable("id") Integer id) {
		try {
			projectUseCase.deleteProject(id);
			return ResponseEntity.noContent().build();
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		} catch (DataIntegrityViolationException  e) {
			// Return a response with status 400 Bad Request for data integrity violation
		    return ResponseEntity.badRequest().build();
		}
	}

	@PatchMapping("/figer/{id}")
	public ResponseEntity<String> figerProject(@PathVariable("id") Integer id) {
		try {
			String response = projectUseCase.figer(id);
			if(!response.isEmpty())
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			return ResponseEntity.status(HttpStatus.OK).body("Project figer avec succes");
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/valider/{id}")
	public ResponseEntity<String> validerProject(@PathVariable("id") Integer id) {
		try {
			Boolean response = projectUseCase.valider(id);
			if(!response)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Project non Valider");
			return ResponseEntity.status(HttpStatus.OK).body("Project valider avec succes");
		} catch (NoSuchElementException e) {
			// return a response with status 404 if an object with id is not found
			return ResponseEntity.notFound().build();
		}
	}

}
