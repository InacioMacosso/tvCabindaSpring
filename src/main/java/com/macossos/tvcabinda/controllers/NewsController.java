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

import com.macossos.tvcabinda.entities.News;
import com.macossos.tvcabinda.entities.dtos.NewsDTO;
import com.macossos.tvcabinda.services.NewsService;

@RestController
@RequestMapping(value = "/noticias")
public class NewsController {
	@Autowired
	private NewsService newsService;

	@PostMapping
	public ResponseEntity<NewsDTO> create(@Valid @RequestBody NewsDTO objDto) {
		News newObj = newsService.create(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public ResponseEntity<List<NewsDTO>> findAll() {
		List<News> news = newsService.findAll();
		List<NewsDTO> newsDTOs = news.stream().map(obj -> new NewsDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(newsDTOs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<NewsDTO> findById(@PathVariable Integer id) {
		News news = newsService.findById(id);
		return ResponseEntity.ok().body(new NewsDTO(news));
	}

	@PutMapping("/{id}")
	public ResponseEntity<NewsDTO> update(@PathVariable Integer id, @Valid @RequestBody NewsDTO newsDTO) {
		News news = newsService.update(id, newsDTO);
		return ResponseEntity.ok().body(new NewsDTO(news));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<NewsDTO> delete(@PathVariable Integer id) {
		newsService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
