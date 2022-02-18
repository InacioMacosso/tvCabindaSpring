package com.macossos.tvcabinda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macossos.tvcabinda.services.SubCategoryService;

@RestController
@RequestMapping(value = "/subcategories")
public class SubcategoryController {
	@Autowired
	private SubCategoryService subcategoryService;

}
