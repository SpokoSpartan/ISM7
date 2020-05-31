package com.spot.on.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class NewCourseRequest {

	@NotBlank(message = "Title cannot be blank")
	private String title;
	@NotNull(message = "Lecturer id is required")
	private long lecturerId;
	@NotNull(message = "Date is required")
	private Date date;
	@NotNull(message = "Capacity is required")
	@Min(value = 1, message = "Capacity must be bigger than 0")
	private long capacity;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getLecturerId() {
		return lecturerId;
	}

	public void setLecturerId(long lecturerId) {
		this.lecturerId = lecturerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

}
