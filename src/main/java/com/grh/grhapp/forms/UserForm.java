package com.grh.grhapp.forms;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.grh.grhapp.entities.Role;
import com.grh.grhapp.entities.Sexe;
import com.grh.grhapp.validations.Mobile;
import com.grh.grhapp.validations.NotExistingUser;
import com.grh.grhapp.validations.ValidationGroupOne;
import com.grh.grhapp.validations.ValidationGroupTwo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotExistingUser(groups = ValidationGroupTwo.class)
public class UserForm {

	private UUID id;
	@NotBlank
	private String nom;
	@NotBlank
	private String prenom;
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateNaissance;
	@NotBlank
	private String lieuNaissance;
	@NotBlank
	@Email(groups = ValidationGroupOne.class)
	private String email;
	@NotNull
	private Sexe sexe;
	@NotBlank
	@Mobile
//	@Pattern(regexp = "^0(5|6|7)[0-9]{8}$", groups = ValidationGroupOne.class)
	private String telephone;
	@NotBlank
	private String password;
	@NotNull
	private Set<Role> role;

}
