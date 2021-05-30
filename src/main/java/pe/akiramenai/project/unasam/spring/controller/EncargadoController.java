package pe.akiramenai.project.unasam.spring.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.akiramenai.project.unasam.spring.model.Usuario;
import pe.akiramenai.project.unasam.spring.service.IAsesoriaService;
import pe.akiramenai.project.unasam.spring.service.IEvaluacionService;
import pe.akiramenai.project.unasam.spring.service.IProcesoService;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;

@Controller
@RequestMapping("/encargado")
public class EncargadoController {

	@Autowired
	private IAsesoriaService aService;

	@Autowired
	private IEvaluacionService eService;

	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IProcesoService pService;

	@RequestMapping("/perfil")
	public String perfil(Model model) 
			throws ParseException{
		Usuario usuario= uService.getUsuario();
		model.addAttribute("listaAsesorias", aService.buscarAsesoriaCodigoAsesor(usuario.getUsername()));
		model.addAttribute("listaEvaluaciones", eService.buscarEstudianteCodigoJuez(usuario.getUsername()));
		model.addAttribute("listaEstudiantes", eService.buscarEstudianteCodigoJuez(usuario.getUsername()));
		return "encargadoPerfil";
	}
	@RequestMapping("/procesos")
	public String listarEst(Map<String, Object>model)
			throws ParseException{
		Usuario usuario= uService.getUsuario();
		model.put("listaProcesos", pService.listarbyEstudianteDNI(usuario.getDni()));
		model.put("listaEstudiantes", uService.buscarEstudianteDNI(usuario.getDni()));
		return "listProcesosEstudiante";
	}
}
