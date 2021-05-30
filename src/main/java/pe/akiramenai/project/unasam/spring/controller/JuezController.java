package pe.akiramenai.project.unasam.spring.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.akiramenai.project.unasam.spring.model.Usuario;
import pe.akiramenai.project.unasam.spring.service.IEvaluacionService;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;

@Controller
@RequestMapping("/juez")
public class JuezController {

	@Autowired
	private IEvaluacionService eService;

	@Autowired
	private IUsuarioService uService;

	@RequestMapping("/perfil")
	public String perfil(Model model) 
	throws ParseException{
		Usuario usuario= uService.getUsuario();
		model.addAttribute("listaEstudiantes", eService.buscarEstudianteCodigoJuez(usuario.getUsername()));
		return "juezPerfil";
	}
}
