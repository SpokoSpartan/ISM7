package com.spot.on.domain;

import java.util.*;

public class Course {

	private Long id;
	private String title;
	private User lecturer;
	private Date date;
	private long capacity;
	private Set<User> members = new HashSet<>();

	public Course(String title, User lecturer, Date date, long capacity) {
		this.title = title;
		this.lecturer = lecturer;
		this.date = date;
		this.capacity = capacity;
	}

	public Course() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLecturer(User lecturer) {
		this.lecturer = lecturer;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getLecturer() {
		return lecturer;
	}

	public void setLecturerId(User lecturer) {
		this.lecturer = lecturer;
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
