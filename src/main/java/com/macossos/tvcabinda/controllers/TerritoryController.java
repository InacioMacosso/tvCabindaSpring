package com.macossos.tvcabinda.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.macossos.tvcabinda.entities.Territory;
import com.macossos.tvcabinda.entities.dtos.TerritoryDTO;
import com.macossos.tvcabinda.services.TerritoryService;

@RestController
@RequestMapping(value = "/territorios")
public class TerritoryController {
	@Autowired
	private TerritoryService territoryService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TerritoryDTO> create(@Valid @RequestBody TerritoryDTO territoryDTO) {
		Territory newObj = territoryService.create(territoryDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TerritoryDTO> findById(@PathVariable Integer id) {
		Territory territory = territoryService.findById(id);
		return ResponseEntity.ok().body(new TerritoryDTO(territory));
	}

	@GetMapping
	public ResponseEntity<List<TerritoryDTO>> findAll() {
		List<Territory> territories = territoryService.findAll();
		List<TerritoryDTO> territoryDTOs = territories.stream().map(obj -> new TerritoryDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(territoryDTOs);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<TerritoryDTO> update(@PathVariable Integer id,
			@Valid @RequestBody TerritoryDTO territoryDTO) {
		Territory territory = territoryService.update(id, territoryDTO);
		return ResponseEntity.ok().body(new TerritoryDTO(territory));
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<TerritoryDTO> delete(@PathVariable Integer id) {
		territoryService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
