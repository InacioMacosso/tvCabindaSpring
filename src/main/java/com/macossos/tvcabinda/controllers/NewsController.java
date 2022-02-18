package com.macossos.tvcabinda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macossos.tvcabinda.services.NewsService;

@RestController
@RequestMapping(value = "/news")
public class NewsController {
	@Autowired
	private NewsService newsService;

}
