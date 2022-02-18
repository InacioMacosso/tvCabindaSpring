package com.macossos.tvcabinda.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macossos.tvcabinda.entities.Category;
import com.macossos.tvcabinda.entities.dtos.CategoryDTO;
import com.macossos.tvcabinda.repositories.CategoryRepository;
import com.macossos.tvcabinda.services.exceptions.DataIntegrityViolationException;
import com.macossos.tvcabinda.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public Category create(CategoryDTO categoryDTO) {
		categoryDTO.setId(null);
		validarPorNome(categoryDTO);
		Category category = new Category(categoryDTO);
		return categoryRepository.save(category);
	}

	private void validarPorNome(CategoryDTO categoryDTO) {
		Optional<Category> category = categoryRepository.findByName(categoryDTO.getName());
		if (category.isPresent() && category.get().getId() != categoryDTO.getId()) {
			throw new DataIntegrityViolationException("Nome ja cadastrado");
		}

	}

	public Category findById(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException("Categoria nao encontrada! id: " + id));
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category update(Integer id, @Valid CategoryDTO categoryDTO) {
		categoryDTO.setId(id);
		Category oldCategory = findById(id);
		validarPorNome(categoryDTO);
		oldCategory = new Category(categoryDTO);
		return categoryRepository.save(oldCategory);
	}

	public void delete(Integer id) {
		Category category = findById(id);
		if (category.getNews().size() > 0) {
			throw new DataIntegrityViolationException("O territorio nao pode ser deletada porque esta associado a "
					+ category.getNews().size() + " noticias");
		}
		categoryRepository.deleteById(id);

	}

}