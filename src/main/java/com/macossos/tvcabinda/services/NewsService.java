package com.macossos.tvcabinda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macossos.tvcabinda.entities.Category;
import com.macossos.tvcabinda.entities.News;
import com.macossos.tvcabinda.entities.Territory;
import com.macossos.tvcabinda.entities.User;
import com.macossos.tvcabinda.entities.dtos.NewsDTO;
import com.macossos.tvcabinda.repositories.NewsRepository;
import com.macossos.tvcabinda.services.exceptions.ObjectNotFoundException;

@Service
public class NewsService {
	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;
	@Autowired
	private TerritoryService territoryService;

	public News create(NewsDTO objDto) {
		return newsRepository.save(novoNews(objDto));
	}

	private News novoNews(NewsDTO objDto) {
		Category category = categoryService.findById(objDto.getCategory());
		User autor = userService.findById(objDto.getAutor());
		Territory territory = territoryService.findById(objDto.getTerritory());

		News news = new News();
		if (objDto.getId() != null) {
			news.setId(objDto.getId());
		}
		news.setAutor(autor);
		news.setBody(objDto.getBody());
		news.setCategory(category);
		news.setDataCriacao(objDto.getDataCriacao());
		news.setIsBest(objDto.getIsBest());
		news.setPhoto(objDto.getPhoto());
		news.setSlug(objDto.getSlug());
		news.setTerritory(territory);
		news.setTitle(objDto.getTitle());
		news.setVideo(objDto.getVideo());
		return news;
	}

	public List<News> findAll() {
		return newsRepository.findAll();
	}

	public News findById(Integer id) {
		Optional<News> news = newsRepository.findById(id);
		return news.orElseThrow(() -> new ObjectNotFoundException("Noticia não encontrada! ID: " + id));
	}

	public News update(Integer id, NewsDTO newsDTO) {
		newsDTO.setId(id);
		News oldNews = findById(id);
		oldNews = novoNews(newsDTO);
		return newsRepository.save(oldNews);
	}

	public void delete(Integer id) {
		newsRepository.deleteById(id);
	}
}
