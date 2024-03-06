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
import com.example.dcfitness.model.Video;
import com.example.dcfitness.model.VideoRepository;

@RestController
@RequestMapping("/api")
public class BodyPartController {
	
	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BodyPartRepository bodyPartRepository;
	
	//API Get all body parts
	@GetMapping("/bodyparts")
	public ResponseEntity<List<BodyPart>> getAllBodyPart() {

		try {
			List<BodyPart> parts = new ArrayList<BodyPart>();
			bodyPartRepository.findAll().forEach(parts::add);
			if (parts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(parts, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	//Create new body part
	@PostMapping("/bodyparts")
	public ResponseEntity<BodyPart> createCourse(@RequestBody BodyPart part) {

		try {
			BodyPart _part = bodyPartRepository.save(new BodyPart(part.getName()));
			return new ResponseEntity<>(_part, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	// Delete bodypart by id
	@DeleteMapping("/bodyparts/{id}")
	public ResponseEntity<List<BodyPart>> deleteBodyPartById(@PathVariable("id") Long id) {
		try {
			ArrayList<BodyPart> bodyParts = new ArrayList<>();
			bodyPartRepository.deleteById(id);
			bodyPartRepository.findAll().forEach(bodyParts::add);
			if(bodyParts.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>(bodyParts, HttpStatus.OK);
			}
			} catch (Exception e) {
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
	// Edit body part by id
	@PutMapping("/bodyparts/{id}")
	public ResponseEntity<BodyPart> updateBodyPart(@PathVariable("id") long id, @RequestBody BodyPart newBodyPart) {
		try {
			Optional<BodyPart> bodyPart = bodyPartRepository.findById(id);
				
			if(bodyPart.isPresent()) {
				BodyPart _bodyPart = bodyPart.get();
				_bodyPart.setName(newBodyPart.getName());
				bodyPartRepository.save(_bodyPart);
				return new ResponseEntity<>(_bodyPart, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	

}
