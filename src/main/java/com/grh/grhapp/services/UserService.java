package com.grh.grhapp.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grh.grhapp.entities.User;
import com.grh.grhapp.exceptions.UserNotFoundException;
import com.grh.grhapp.forms.UserForm;
import com.grh.grhapp.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder; 
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Page<User> getAll(Pageable pageable) {
		return this.userRepository.findAll(pageable);
	}

	@Override
	public User create(UserForm userForm) {
		User user = new User();
		user.setNom(userForm.getNom());
		user.setPrenom(userForm.getPrenom());
		user.setDateNaissance(userForm.getDateNaissance());
		user.setLieuNaissance(userForm.getLieuNaissance());
		user.setEmail(userForm.getEmail());
		user.setSexe(userForm.getSexe());
		user.setTelephone(userForm.getTelephone());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.setRoles(userForm.getRole());
		
		return userRepository.save(user);
	}

	@Override
	public boolean userWithEmailExists(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public Optional<User> getByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User update(UUID id, UserForm userForm) {
		User user = new User();
		user.setId(id);
		user.setNom(userForm.getNom());
		user.setPrenom(userForm.getPrenom());
		user.setDateNaissance(userForm.getDateNaissance());
		user.setLieuNaissance(userForm.getLieuNaissance());
		user.setEmail(userForm.getEmail());
		user.setSexe(userForm.getSexe());
		user.setTelephone(userForm.getTelephone());
		return userRepository.save(user);
	}

	@Override
	public Optional<User> getById(UUID id) {
		return userRepository.findById(id);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}
	
}
