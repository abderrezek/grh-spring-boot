package com.grh.grhapp;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.grh.grhapp.entities.Role;
import com.grh.grhapp.entities.Sexe;
import com.grh.grhapp.forms.UserForm;
import com.grh.grhapp.services.IUserService;

@Component
@Profile("init-db")
public class DatabaseInitializer implements CommandLineRunner {

	private final Faker faker = new Faker(new Locale("fr"));
	private final IUserService userService;
	
	public DatabaseInitializer(IUserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		UserForm userForm;
		// create users
		for (int i = 0; i < 20; i++) {
			userForm = newRandomUser();
			userService.create(userForm);
		}
		
		// create admin
		userForm = new UserForm(
			null,
			"admin",
			"admin",
			new Date(),
			"chlef",
			"admin@admin.com",
			Sexe.Homme,
			"0697970724",
			"admin",
			Set.of(Role.USER,Role.ADMIN)
		);
		userService.create(userForm);
		System.out.println("/** end init db **/");
	}
	
	private UserForm newRandomUser() {
		String nom = faker.name().firstName();
		String prenom = faker.name().lastName();
		Date dateNaissance = faker.date().birthday(10, 40);
		String lieuNaissance = faker.address().city() + ", " + faker.address().country();
		String email = faker.internet().emailAddress(nom + "_" + prenom);
		Sexe sexe = faker.bool().bool() ? Sexe.Homme : Sexe.Femme;
		String telephone = faker.phoneNumber().phoneNumber();
		
		return new UserForm(null, nom, prenom, dateNaissance, lieuNaissance, email, sexe, telephone, "user", Set.of(Role.USER));
	}
	
}
