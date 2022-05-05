package com.grh.grhapp.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.grh.grhapp.entities.User;
import com.grh.grhapp.forms.UserForm;

public interface IUserService {

	boolean userWithEmailExists(String email);
	
	User create(UserForm user);
	
	User update(UUID id, UserForm userForm);
	
	void delete(User user);
	
	Page<User> getAll(Pageable pageable);
	Optional<User> getById(UUID id);
	Optional<User> getByEmail(String email);
	
	
	
}
