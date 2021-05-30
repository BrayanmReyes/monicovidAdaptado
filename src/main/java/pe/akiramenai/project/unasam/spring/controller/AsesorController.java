package pe.akiramenai.project.unasam.spring.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.akiramenai.project.unasam.spring.model.Usuario;
import pe.akiramenai.project.unasam.spring.service.IAsesoriaService;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;

@Controller
@RequestMapping("/asesor")
public class AsesorController {

	@Autowired
	private IAsesoriaService aService;

	@Autowired
	private IUsuarioService uService;

	@RequestMapping("/perfil")
	public String perfil(Model model) 
	throws ParseException{
		Usuario usuario= uService.getUsuario();
		model.addAttribute("listaEstudiantes", aService.buscarAsesoriaCodigoAsesor(usuario.getUsername()));
		return "asesorPerfil";
	}
}
