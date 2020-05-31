package com.spot.on.repository;

import com.spot.on.domain.Course;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CourseRepository {

	private Map<Long, Course> COURSES = new HashMap<>();

	public Course addCourse(Course course) {
		course.setId(IdSequenceGenerator.next());
		COURSES.put(course.getId(), course);
		return course;
	}

	public Optional<Course> findCourse(Long id) {
		return Optional.ofNullable(COURSES.get(id));
	}

	public void removeCourse(Course course) {
		Course c = findCourse(course.getId()).orElseThrow(
				() -> new IllegalArgumentException("Course with id: " + course.getId() + " not found"));
		COURSES.remove(c.getId());
	}

}
