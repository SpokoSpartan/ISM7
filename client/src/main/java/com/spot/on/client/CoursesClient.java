package com.spot.on.client;

import com.spot.on.schema.Confirmation;
import com.spot.on.schema.CourseId;
import com.spot.on.schema.CourseRequest;
import com.spot.on.schema.EnrollmentRequest;
import com.spot.on.schema.PersonId;
import com.spot.on.schema.RegisterRequest;
import com.spot.on.schema.WithdrawRequest;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.Date;

public class CoursesClient extends WebServiceGatewaySupport {

	public Confirmation enrollToCourse(long studentId, long courseId) {
		EnrollmentRequest request = new EnrollmentRequest();
		request.setStudentId(studentId);
		request.setCourseId(courseId);
		return (Confirmation) getWebServiceTemplate().marshalSendAndReceive(request);
	}

	public CourseId addCourse(String title, long lecturerId, Date date, long capacity) {
		CourseRequest request = new CourseRequest();
		request.setTitle(title);
		request.setLecturerId(lecturerId);
		request.setDate(date);
		request.setCapacity(capacity);
		return (CourseId) getWebServiceTemplate().marshalSendAndReceive(request);
	}

	public Confirmation removeCourse(long courseId) {
		CourseId request = new CourseId();
		request.setId(courseId);
		return (Confirmation) getWebServiceTemplate().marshalSendAndReceive(request);
	}

	public PersonId register(String name, String surname, String type) {
		RegisterRequest request = new RegisterRequest();
		request.setName(name);
		request.setSurname(surname);
		request.setType(type);
		return (PersonId) getWebServiceTemplate().marshalSendAndReceive(request);
	}

	public Confirmation withdrawFromCourse(long courseId, long studentId) {
		WithdrawRequest request = new WithdrawRequest();
		request.setCourseId(courseId);
		request.setStudentId(studentId);
		return (Confirmation) getWebServiceTemplate().marshalSendAndReceive(request);
	}

	public Confirmation unregister(long personId) {
		PersonId request = new PersonId();
		request.setId(personId);
		return (Confirmation) getWebServiceTemplate().marshalSendAndReceive(request);
	}

}
