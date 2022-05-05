package com.grh.grhapp.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.groups.Default;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grh.grhapp.entities.Sexe;
import com.grh.grhapp.entities.User;
import com.grh.grhapp.exceptions.UserNotFoundException;
import com.grh.grhapp.forms.UserForm;
import com.grh.grhapp.services.IUserService;
import com.grh.grhapp.validations.ValidationGroupOne;
import com.grh.grhapp.validations.ValidationGroupTwo;

@Controller
@RequestMapping(path = "/users")
public class UserController {
	
	private final IUserService userService;
	
	public UserController(IUserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public String index(
		Model model,
		@SortDefault.SortDefaults({
			@SortDefault("nom"),
			@SortDefault("prenom")
		}) Pageable pageable
	) {
		model.addAttribute("users", userService.getAll(pageable));
		return "users/index";
	}
	
	@GetMapping(path = "/create")
	public String create(Model model) {
		model.addAttribute("user", new UserForm());
		model.addAttribute("sexes", List.of(Sexe.Homme, Sexe.Femme));
		return "users/create";
	}
	
	@PostMapping(path = "/create")
	public String insert(
		@Validated({ Default.class, ValidationGroupOne.class, ValidationGroupTwo.class }) @ModelAttribute("user") UserForm userForm,
		BindingResult bindingResult,
		Model model
	) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("sexes", List.of(Sexe.Homme, Sexe.Femme));
			return "users/create";
		}
		userService.create(userForm);
		
		return "redirect:/users";
	}
	
	@GetMapping(path = "/{id}")
	public String edit(@PathVariable("id") String id, Model model) {
		User user = userService.getById(UUID.fromString(id))
						.orElseThrow(() -> new UserNotFoundException(id));
		
		UserForm userForm = new UserForm(
			user.getId(),
			user.getNom(),
			user.getPrenom(),
			user.getDateNaissance(),
			user.getLieuNaissance(),
			user.getEmail(),
			user.getSexe(),
			user.getTelephone(),
			user.getPassword(),
			user.getRoles()
		);
		model.addAttribute("user", userForm);
		model.addAttribute("sexes", List.of(Sexe.Homme, Sexe.Femme));
		return "users/edit";
	}
	
	@PostMapping(path = "/{id}")
	public String update(
		@PathVariable("id") String id,
		@Validated({ Default.class }) @ModelAttribute("user") UserForm userForm,
		BindingResult bindingResult,
		Model model
	) {
		User user = userService.getById(UUID.fromString(id))
				.orElseThrow(() -> new UserNotFoundException(id));
		if (bindingResult.hasErrors()) {
			model.addAttribute("sexes", List.of(Sexe.Homme, Sexe.Femme));
			return "users/edit";
		}
		userService.update(user.getId(), userForm);
		
		return "redirect:/users";
	}
	
	@PostMapping(path = "/{id}/delete")
	public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		User user = userService.getById(UUID.fromString(id))
						.orElseThrow(() -> new UserNotFoundException(id));
		
		userService.delete(user);
		
		redirectAttributes.addFlashAttribute("deletedUser", user.getNom() + " " + user.getPrenom());
		
		return "redirect:/users";
	}

}
