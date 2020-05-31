package com.spot.on.repository;

import com.spot.on.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

	private Map<Long, User> USERS = new HashMap<>();

	public User addUser(User user) {
		user.setId(IdSequenceGenerator.next());
		USERS.put(user.getId(), user);
		return user;
	}

	public Optional<User> findUser(Long id) {
		return Optional.ofNullable(USERS.get(id));
	}

	public void removeUser(User user) {
		User u = Optional.ofNullable(USERS.get(user.getId())).orElseThrow(
				() -> new IllegalArgumentException("User with id: " + user.getId() + " not found"));
		USERS.remove(u.getId());
	}

}
