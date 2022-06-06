package com.askme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.askme.dto.SubqueryDto;
import com.askme.service.SubqueryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subquery")
public class SubqueryController {
	
	@Autowired
	private SubqueryService subqueryService;
	
	@PostMapping
	public ResponseEntity<SubqueryDto> createSubquery(@RequestBody SubqueryDto subqueryDto) {
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(subqueryService.save(subqueryDto));
	}

	@GetMapping
	public ResponseEntity<List<SubqueryDto>> getAllSubquerys() {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(subqueryService.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubqueryDto> getSubquery(@PathVariable Long id) {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(subqueryService.getSubquery(id));
	    }

}
