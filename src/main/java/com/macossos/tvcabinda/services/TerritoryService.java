package com.macossos.tvcabinda.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macossos.tvcabinda.entities.Territory;
import com.macossos.tvcabinda.entities.dtos.TerritoryDTO;
import com.macossos.tvcabinda.repositories.TerritoryRepository;
import com.macossos.tvcabinda.services.exceptions.DataIntegrityViolationException;
import com.macossos.tvcabinda.services.exceptions.ObjectNotFoundException;

@Service
public class TerritoryService {
	@Autowired
	private TerritoryRepository territoryRepository;

	public Territory create(TerritoryDTO territoryDTO) {
		territoryDTO.setId(null);
		validarPorNome(territoryDTO);
		Territory territory = new Territory(territoryDTO);
		return territoryRepository.save(territory);
	}

	private void validarPorNome(TerritoryDTO territoryDTO) {
		Optional<Territory> territory = territoryRepository.findByName(territoryDTO.getName());
		if (territory.isPresent() && territory.get().getId() != territoryDTO.getId()) {
			throw new DataIntegrityViolationException("Nome ja cadastrado");
		}

	}

	public Territory findById(Integer id) {
		Optional<Territory> territory = territoryRepository.findById(id);
		return territory.orElseThrow(() -> new ObjectNotFoundException("Territorio nao encontrado! id: " + id));
	}

	public List<Territory> findAll() {
		return territoryRepository.findAll();
	}

	public Territory update(Integer id, @Valid TerritoryDTO territoryDTO) {
		territoryDTO.setId(id);
		Territory oldTerritory = findById(id);
		validarPorNome(territoryDTO);
		oldTerritory = new Territory(territoryDTO);
		return territoryRepository.save(oldTerritory);
	}

	public void delete(Integer id) {
		Territory territory = findById(id);
		if (territory.getNews().size() > 0) {
			throw new DataIntegrityViolationException("O territorio nao pode ser deletado porque esta associado a "
					+ territory.getNews().size() + " noticias");
		}
		territoryRepository.deleteById(id);

	}

}
