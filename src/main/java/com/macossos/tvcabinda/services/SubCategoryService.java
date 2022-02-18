package com.macossos.tvcabinda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macossos.tvcabinda.repositories.SubCategoryRepository;

@Service
public class SubCategoryService {
	@Autowired
	private SubCategoryRepository subCategoryRepository;

}
