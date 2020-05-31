package com.spot.on.controllers;

import com.spot.on.dto.NewCourseRequest;
import com.spot.on.schema.RegisterRequest;
import com.spot.on.services.CoursesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class CoursesController {

	private final CoursesService service;

	public CoursesController(CoursesService service) {
		this.service = service;
	}

	@PostMapping("/courses/add")
	public ResponseEntity<Long> addCourse(@Valid @RequestBody NewCourseRequest request) {
		return service.addCourse(request);
	}

	@DeleteMapping("/courses/remove/{id}")
	public ResponseEntity<String> removeCourse(@PathVariable Long id) {
		return service.removeCourse(id);
	}

	@PostMapping("/courses/{courseId}/enroll/{studentId}")
	public ResponseEntity<String> enrollToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
		return service.enrollToCourse(courseId, studentId);
	}

	@PostMapping("/courses/{courseId}/withdraw/{studentId}")
	public ResponseEntity<String> withdrawFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
		return service.withdrawFromCourse(courseId, studentId);
	}

	@PostMapping("/user/register")
	public ResponseEntity<Long> registerUser(@Valid @RequestBody RegisterRequest request) {
		return service.createUser(request);
	}

	@DeleteMapping("/user/{id}/unregister")
	public ResponseEntity<String> unregisterUser(@PathVariable Long id) {
		return service.unregisterUser(id);
	}

}
