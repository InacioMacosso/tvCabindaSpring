package com.macossos.tvcabinda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macossos.tvcabinda.repositories.NewsRepository;

@Service
public class NewsService {
	@Autowired
	private NewsRepository newsRepository;
}
