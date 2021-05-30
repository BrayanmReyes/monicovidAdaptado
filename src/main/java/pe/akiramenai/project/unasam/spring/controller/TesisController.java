package pe.akiramenai.project.unasam.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.akiramenai.project.unasam.spring.model.Usuario;
import pe.akiramenai.project.unasam.spring.model.Tesis;
import pe.akiramenai.project.unasam.spring.model.TesisFile;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;
import pe.akiramenai.project.unasam.spring.service.ITesisLinkService;
import pe.akiramenai.project.unasam.spring.service.ITesisService;

@Controller
@RequestMapping("/tesis")
public class TesisController {

	@Autowired
	private ITesisService aService;
	@Autowired
	private ITesisLinkService bService;
	@Autowired
	private IUsuarioService eService;//estudiante
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model)
	{
		model.put("listaTesiss", aService.FilesByUserId());
		model.put("listaEstudiantes", eService.listar());
		return "listTesiss";
		//return "listEstudiantesAdmin";
	}
	
	@RequestMapping("/listar2")
	public String listar2(Map<String, Object>model)
	{
		model.put("listaTesissl", bService.listar());
		model.put("listaEstudiantes", eService.listar());
		return "listTesissLink";
		//return "listEstudiantesAdmin";
	}
	
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("tesis", new Tesis());
		model.addAttribute("listaEstudiantes", eService.listar());
		return "tesis";
	}
	
	@RequestMapping("/irRegistrar2")
	public String irRegistrar2(Model model)
	throws ParseException{
		Tesis tesis=new Tesis();
		Usuario usuario= eService.getUsuario();
		tesis.setEstudiante(usuario);
		model.addAttribute("tesis", tesis);
		model.addAttribute("listaEstudiantes", eService.listar());
		
		return "tesislink";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Tesis tesis, BindingResult binRes, Model model,RedirectAttributes redirectAttributes)
	throws ParseException
	{
		if(binRes.hasErrors()) 
		{
			model.addAttribute("listaTesiss", aService.listar());
			model.addAttribute("listaEstudiantes", eService.listar());
			return "tesis";
		}
		else {
				Tesis dbTesis = aService.save(tesis);
				if(dbTesis!=null)
				{
					redirectAttributes.addFlashAttribute("successmessage", "SAVE EXITOSO");
					return "redirect:/tesis/listar2";
				}
				else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/tesis/irRegistrar2";

				}
							
			}			
	}

	@RequestMapping("/actualizar")
	public String update(@ModelAttribute Tesis tesis,RedirectAttributes redirectAttributes,Model model ) {
		Tesis dbTesis = aService.update(tesis);
		if(dbTesis!=null) {
			redirectAttributes.addFlashAttribute("successmessage", "UPDATE EXITOSO");
			return "redirect:/";
		}else {
			model.addAttribute("errormessage", "RIP");
			model.addAttribute("tesis", tesis);
			return "tesis";
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String tesissedit(@PathVariable Long tesisId, Model model) {
		Tesis tesis = aService.findById(tesisId);
		List<TesisFile> tesisFiles = aService.findFilesByUserId(tesisId);
		List<Tesis> tesiss=aService.listar();		
		model.addAttribute("tesiss", tesiss);
		model.addAttribute("tesis", tesis);
		model.addAttribute("tesisFile", tesisFiles);
		model.addAttribute("listaEstudiantes", eService.listar());
		model.addAttribute("isAdd", false);
		return "tesis";
	}
	
	@GetMapping("/form/{idEstudiante}")
	public String newInvoice(@PathVariable(value = "id") Long id, Model model) {
		try {
			Optional<Usuario> estudiante = eService.buscarId(id);
			if (!estudiante.isPresent()) {
				model.addAttribute("info", "Estudiante no existe");
				return "redirect:/usuario/perfil/"+id;
			} else {
				Tesis tesis = new Tesis();
				tesis.setEstudiante(estudiante.get());
				model.addAttribute("isAdd", true);
				model.addAttribute("tesis", tesis);
				model.addAttribute("listaEstudiantes", eService.listar());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "tesis";
	}
	
	@GetMapping("/BuscarPorNombreTesis")
	public String buscarNombre(@RequestParam("name") String name,Map<String, Object> model) {
		model.put("listaTesissl",bService.buscarByNombreTesis(name.toUpperCase()));
		model.put("listaEstudiantes", eService.listar());
		return "listTesissLink";
	}
	
	@GetMapping("/BuscarPorTesista")
	public String buscarTesista(@RequestParam("apellido") String apellido,Map<String, Object> model) {
		model.put("listaTesissl", bService.buscarByTesistaApellido(apellido.toUpperCase()));
		model.put("listaEstudiantes", eService.listar());
		return "listTesissLink";
	}
}
