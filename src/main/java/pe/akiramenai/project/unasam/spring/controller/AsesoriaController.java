package pe.akiramenai.project.unasam.spring.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;


import javax.validation.Valid;

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
import pe.akiramenai.project.unasam.spring.model.Asesoria;
import pe.akiramenai.project.unasam.spring.model.Usuario;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;
import pe.akiramenai.project.unasam.spring.service.IAsesoriaService;


@Controller
@RequestMapping("/asesoria")
public class AsesoriaController{

	@Autowired
	private IAsesoriaService aService;
	@Autowired
	private IUsuarioService rService;//asesor
	@Autowired
	private IUsuarioService eService;// estudiante
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model)
	{
		model.put("listaAsesorias", aService.listar());
		model.put("listaAsesors", rService.listarAsesores());
		model.put("listaEstudiantes", eService.listarEstudiantes());
		return "listAsesorias";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Asesoria Asesoria)
	{
		aService.listarId(Asesoria.getIdAsesoria());
		return "listAsesorias";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		Asesoria asesoria=new Asesoria();
		model.addAttribute("asesoria", asesoria);
		model.addAttribute("listaAsesors", rService.listarAsesores());
		model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
		return "asesoria";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Asesoria objAsesoria, BindingResult binRes, Model model)
	throws ParseException
	{
		if(binRes.hasErrors()) 
		{
			model.addAttribute("listaAsesors", rService.listarAsesores());
			model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
			return "asesoria";
		}
		else {
				boolean flag=aService.insertar(objAsesoria);
				if(flag)
					return "redirect:/asesoria/listar";
				else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/asesoria/irRegistrar";

				}
							
			}			
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Asesoria objAsesoria, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException
	{
		if(binRes.hasErrors())
			return "redirect://asesoria/listar";
		else {
				boolean flag=aService.modificar(objAsesoria);
				if(flag) {
					objRedir.addFlashAttribute("mensaje", "Se modificó correctamente");
					return "redirect:/asesoria/listar";
					}
				else {
					objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
					return "redirect:/asesoria/listar";

				}
							
			}			
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		
		Optional<Asesoria>objAsesoria=aService.buscarId(id);
		if(objAsesoria==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error al cargar");
			return "redirect://asesoria/listar";
			
		}
		else
		{
			if(objAsesoria.isPresent())
				objAsesoria.ifPresent(o-> model.addAttribute("asesoria",o));
			model.addAttribute("listaAsesors", rService.listarAsesores());
			model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
			return "DesignarAsesor";
		}
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String,Object>model, @RequestParam(value="id")Integer id){
		try {
			if(id!=null&&id>0) {
				aService.eliminar(id);
				model.put("listaAsesorias", aService.listar());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Sucedio un error");
			model.put("listaAsesorias", aService.listar());
		}
		return "listAsesorias";
	}
	
	@GetMapping("/form/{id}")
	public String newInvoice(@PathVariable(value = "id") Long id, Model model) {
		try {
			Optional<Usuario> estudiante = eService.buscarId(id);
			if (!estudiante.isPresent()) {
				model.addAttribute("info", "Estudiante no existe");
				return "redirect:/usuario/listaProcesos";
			} else {
				Asesoria asesoria = new Asesoria();
				asesoria.setEstudiante(estudiante.get());
				Date date=new Date();
				DateFormat hourdateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
				String cod= String.valueOf(hourdateFormat.format(date));
				asesoria.setCodigoAsesoria(cod+"-AS");
				model.addAttribute("asesoria", asesoria);
				model.addAttribute("listaAsesors", rService.listarAsesores());
				model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "DesignarAsesor";
	}
	
}

