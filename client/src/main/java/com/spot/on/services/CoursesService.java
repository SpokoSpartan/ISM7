package com.spot.on.services;

import com.spot.on.client.CoursesClient;
import com.spot.on.dto.NewCourseRequest;
import com.spot.on.schema.Confirmation;
import com.spot.on.schema.CourseId;
import com.spot.on.schema.PersonId;
import com.spot.on.schema.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CoursesService {

	private final CoursesClient client;

	public CoursesService(CoursesClient client) {
		this.client = client;
	}

	public ResponseEntity<Long> addCourse(NewCourseRequest request) {
		CourseId courseId = client.addCourse(
				request.getTitle(),
				request.getLecturerId(),
				request.getDate(),
				request.getCapacity());
		return ResponseEntity.ok(courseId.getId());
	}

	public ResponseEntity<String> removeCourse(Long id) {
		Confirmation confirmation = client.removeCourse(id);
		return translateConfirmation(confirmation);
	}

	public ResponseEntity<String> enrollToCourse(Long courseId, Long studentId) {
		Confirmation confirmation = client.enrollToCourse(studentId, courseId);
		return translateConfirmation(confirmation);
	}

	public ResponseEntity<String> withdrawFromCourse(Long courseId, Long studentId) {
		Confirmation confirmation = client.withdrawFromCourse(courseId, studentId);
		return translateConfirmation(confirmation);
	}

	public ResponseEntity<Long> createUser(RegisterRequest request) {
		PersonId personId = client.register(
				request.getName(),
				request.getSurname(),
				request.getType());
		return ResponseEntity.ok(personId.getId());
	}

	public ResponseEntity<String> unregisterUser(Long id) {
		Confirmation confirmation = client.unregister(id);
		return translateConfirmation(confirmation);
	}

	private ResponseEntity<String> translateConfirmation(Confirmation confirmation) {
		return new ResponseEntity(
				confirmation.getMessage(),
				HttpStatus.valueOf(confirmation.getCode().intValue()));
	}

}
