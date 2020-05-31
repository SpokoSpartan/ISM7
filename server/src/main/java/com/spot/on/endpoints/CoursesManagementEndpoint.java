package com.spot.on.endpoints;

import com.spot.on.domain.Course;
import com.spot.on.domain.User;
import com.spot.on.repository.CourseRepository;
import com.spot.on.repository.UserRepository;
import com.spot.on.schema.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

@Endpoint
public class CoursesManagementEndpoint {

	private static final String NAMESPACE_URI = "http://www.example.com/courses";

	private final UserRepository userRepository;
	private final CourseRepository courseRepository;

	public CoursesManagementEndpoint(UserRepository userRepository, CourseRepository courseRepository) {
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "CourseRequest")
	@ResponsePayload
	public CourseId addCourse(@RequestPayload CourseRequest request) {
		CourseId courseId = new CourseId();
		Optional<User> lecturer = userRepository.findUser(request.getLecturerId());
		if(!lecturer.isPresent()) {
			courseId.setId(-1L);
			return courseId;
		}
		Course course = new Course(
				request.getTitle(),
				lecturer.get(),
				request.getDate(),
				request.getCapacity());
		course = courseRepository.addCourse(course);
		courseId.setId(course.getId());
		return courseId;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "CourseId")
	@ResponsePayload
	public Confirmation removeCourse(@RequestPayload CourseId parameters) {
		Optional<Course> course = courseRepository.findCourse(parameters.getId());
		Confirmation confirmation = new Confirmation();
		if (course.isPresent()) {
			courseRepository.removeCourse(course.get());
			confirmation.setCode(new BigInteger("200"));
			confirmation.setMessage("Course removed");
		} else {
			confirmation.setCode(new BigInteger("404"));
			confirmation.setMessage("Course with id: " + parameters.getId() + " not found");
		}
		return confirmation;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "RegisterRequest")
	@ResponsePayload
	public PersonId register(@RequestPayload RegisterRequest parameters) {
		User user = new User(
				parameters.getName(),
				parameters.getSurname(),
				parameters.getType());
		userRepository.addUser(user);
		PersonId personId = new PersonId();
		personId.setId(user.getId());
		return personId;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "WithdrawRequest")
	@ResponsePayload
	public Confirmation withdrawFromCourse(@RequestPayload WithdrawRequest parameters) {
		Optional<Course> course = courseRepository.findCourse(parameters.getCourseId());
		Confirmation confirmation = new Confirmation();
		if (course.isPresent()) {
			Optional<User> user = userRepository.findUser(parameters.getStudentId());
			if (user.isPresent()) {
				Set<User> users = course.get().getMembers();
				users.remove(user.get());
				confirmation.setCode(new BigInteger("200"));
				confirmation.setMessage("User withdraw");
			} else {
				confirmation.setCode(new BigInteger("404"));
				confirmation.setMessage("User with id " + parameters.getStudentId() + " not found");
			}
		} else {
			confirmation.setCode(new BigInteger("404"));
			confirmation.setMessage("Course with id " + parameters.getCourseId() + " not found");
		}
		return confirmation;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "EnrollmentRequest")
	@ResponsePayload
	public Confirmation enrollToCourse(@RequestPayload EnrollmentRequest parameters) {
		Optional<Course> course = courseRepository.findCourse(parameters.getCourseId());
		Confirmation confirmation = new Confirmation();
		if (course.isPresent()) {
			Optional<User> user = userRepository.findUser(parameters.getStudentId());
			if (user.isPresent()) {
				Set<User> users = course.get().getMembers();
				users.add(user.get());
				confirmation.setCode(new BigInteger("200"));
				confirmation.setMessage("User enrolled");
			} else {
				confirmation.setCode(new BigInteger("404"));
				confirmation.setMessage("User with id " + parameters.getStudentId() + " not found");
			}
		} else {
			confirmation.setCode(new BigInteger("404"));
			confirmation.setMessage("Course with id " + parameters.getCourseId() + " not found");
		}
		return confirmation;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "PersonId")
	@ResponsePayload
	public Confirmation unregister(@RequestPayload PersonId parameters) {
		Optional<User> user = userRepository.findUser(parameters.getId());
		Confirmation confirmation = new Confirmation();
		if (user.isPresent()) {
			userRepository.removeUser(user.get());
			confirmation.setCode(new BigInteger("200"));
			confirmation.setMessage("User unregistered");
		} else {
			confirmation.setCode(new BigInteger("404"));
			confirmation.setMessage("User with id: " + parameters.getId() + " not found");
		}
		return confirmation;
	}

}
