package br.ufes.jbocas.controllers;

import br.ufes.jbocas.domain.Usuario;
import br.ufes.jbocas.service.SecurityService;
import br.ufes.jbocas.service.UsuarioService;
import br.ufes.jbocas.validator.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AutenticadorUser {
    @Autowired
    private UsuarioService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UsuarioValidator userValidator;

    @RequestMapping(value = "/registrar", method = RequestMethod.GET)
    public String registration(Model model) {
    	model.addAttribute("title", "NDB - Registrar");
        model.addAttribute("userForm", new Usuario());

        return "registrar";
    }

    @RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") Usuario userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
        	model.addAttribute("error", bindingResult.getFieldError().getCode());
            return "registrar";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @RequestMapping(value = "/entrar", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
    	model.addAttribute("title", "NDB - Entrar");
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "entrar";
    }
}