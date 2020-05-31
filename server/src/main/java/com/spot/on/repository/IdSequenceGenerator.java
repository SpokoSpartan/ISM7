package com.spot.on.repository;

public class IdSequenceGenerator {

	private static long id = 1L;

	public static final Long next() {
		return id++;
	}

}
