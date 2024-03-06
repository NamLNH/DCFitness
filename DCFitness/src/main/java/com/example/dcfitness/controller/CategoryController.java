package com.example.dcfitness.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dcfitness.model.BodyPart;
import com.example.dcfitness.model.BodyPartRepository;
import com.example.dcfitness.model.Category;
import com.example.dcfitness.model.CategoryRepository;
import com.example.dcfitness.model.Comment;
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BodyPartRepository bodyPartRepository;
	
	//API Get all category
		@GetMapping("/categories")
		public ResponseEntity<List<Category>> getAllCategory() {

			try {
				List<Category> category = new ArrayList<Category>();
				categoryRepository.findAll().forEach(category::add);
				if (category.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(category, HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		
		//Create new category
		@PostMapping("/categories")
		public ResponseEntity<Category> createCategory(@RequestBody Category category) {

			try {
				Category _category = categoryRepository.save(new Category(category.getName()));
				return new ResponseEntity<>(_category, HttpStatus.CREATED);

			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}
		
		// Delete category by id
		@DeleteMapping("/categories/{id}")
		public ResponseEntity<List<Category>> deleteCategoryById(@PathVariable("id") Long id) {
			try {
				ArrayList<Category> categories = new ArrayList<>();
				categoryRepository.deleteById(id);
				categoryRepository.findAll().forEach(categories::add);
				if(categories.isEmpty())
				{
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				else {
					return new ResponseEntity<>(categories, HttpStatus.OK);
				}
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		// Edit category by id
		@PutMapping("/categories/{id}")
		public ResponseEntity<Category> updateCategory(@PathVariable("id") long id, @RequestBody Category newCategory) {
			try {
				Optional<Category> category = categoryRepository.findById(id);
				
				if(category.isPresent()) {
					Category _category = category.get();
					_category.setName(newCategory.getName());
					categoryRepository.save(_category);
					return new ResponseEntity<>(_category, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	

}
