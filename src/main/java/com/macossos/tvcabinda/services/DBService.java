package com.macossos.tvcabinda.services;

import java.util.Arrays;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macossos.tvcabinda.entities.Category;
import com.macossos.tvcabinda.entities.News;
import com.macossos.tvcabinda.entities.Territory;
import com.macossos.tvcabinda.entities.User;
import com.macossos.tvcabinda.entities.enums.Role;
import com.macossos.tvcabinda.repositories.CategoryRepository;
import com.macossos.tvcabinda.repositories.NewsRepository;
import com.macossos.tvcabinda.repositories.TerritoryRepository;
import com.macossos.tvcabinda.repositories.UserRepository;

@Service
public class DBService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TerritoryRepository territoryRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private NewsRepository newsRepository;
	private String body;

	public void instanciaDB() {
		User user1 = new User(null, "Inacio", "Macosso", "inaciomacosso@yahoo.com", "password", "0102034545");
		user1.addRole(Role.ADMIN);
		User user2 = new User(null, "Macosso", "Inacio", "macosso@yahoo.com", "password", "0202034545");
		user2.addRole(Role.CLIENTE);
		User user3 = new User(null, "Inacio", "Macosso", "inacio@yahoo.com", "password", "0302034545");
		user3.addRole(Role.ADMIN);
		User user4 = new User(null, "Macosso", "Inacio", "macossoinacio@yahoo.com", "password", "0402034545");
		user4.addRole(Role.CLIENTE);
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));

		Territory mundo = new Territory(null, "Mundo");
		Territory angola = new Territory(null, "Angola");
		Territory cabinda = new Territory(null, "Cabinda");
		territoryRepository.saveAll(Arrays.asList(mundo, angola, cabinda));

		Category politica = new Category(null, "Política");
		Category economia = new Category(null, "Econima");
		Category educacao = new Category(null, "Educação");
		Category cultura = new Category(null, "Cultura");
		categoryRepository.saveAll(Arrays.asList(politica, economia, educacao, cultura));

		body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque finibus mi lacus, eget semper nulla interdum a. Maecenas pulvinar accumsan odio et blandit. Ut eget sem eget velit vulputate suscipit sed sit amet nunc.";
		News news1 = new News("Política falida na Ucrania", null, null, null, body, mundo, politica, user1);
		News news2 = new News("Econima falida em angola", null, null, null, body, angola, economia, user1);
		News news3 = new News("Educação falida em angola", null, null, null, body, angola, educacao, user3);
		News news4 = new News("Cultura falida em angola", null, null, null, body, cabinda, cultura, user1);

		newsRepository.saveAll(Arrays.asList(news1, news2, news3, news4));
	}
}
