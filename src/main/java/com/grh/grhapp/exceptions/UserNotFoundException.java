package com.grh.grhapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String id) {
		super(String.format("Utilisateur avec l'identifiant %s introuvable", id));
	}
}
