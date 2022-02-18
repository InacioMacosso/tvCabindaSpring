package com.macossos.tvcabinda.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.macossos.tvcabinda.entities.Category;
import com.macossos.tvcabinda.entities.dtos.CategoryDTO;
import com.macossos.tvcabinda.services.CategoryService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO) {
		Category newObj = categoryService.create(categoryDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Integer id) {
		Category category = categoryService.findById(id);
		return ResponseEntity.ok().body(new CategoryDTO(category));
	}

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> categories = categoryService.findAll();
		List<CategoryDTO> categoryDTOs = categories.stream().map(obj -> new CategoryDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(categoryDTOs);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
		Category category = categoryService.update(id, categoryDTO);
		return ResponseEntity.ok().body(new CategoryDTO(category));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CategoryDTO> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
