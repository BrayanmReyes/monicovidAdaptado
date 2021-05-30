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
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.akiramenai.project.unasam.spring.model.Proceso;
import pe.akiramenai.project.unasam.spring.model.Usuario;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;
import pe.akiramenai.project.unasam.spring.service.IProcesoService;

@Controller
@RequestMapping("/proceso")
public class ProcesoController{

	@Autowired
	private IProcesoService aService;
	@Autowired
	private IUsuarioService rService;//asesor
	@Autowired
	private IUsuarioService eService;//estudiante
		
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model)
	{
		model.put("listaProcesos", aService.listar());
		model.put("listaEstudiantes", eService.listarEstudiantes());
		return "listProcesos";
	}
	
	@GetMapping("/BuscarCodigo")
	public String buscarDNIProceso(@RequestParam("username") String username,Map<String, Object> model) {
		model.put("listaEstudiantes", eService.listarEstudiantes());
		model.put("listaProcesos", aService.listarProcesobyCodigoTesista(username.toUpperCase()));
		return "listProcesos";
	}

		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Proceso Proceso)
	{
		//aService.listarId(Proceso.getIdProceso());
		return "listProcesos";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{
		model.addAttribute("proceso", new Proceso());
		model.addAttribute("listaAsesors", rService.listarAsesores());
		model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
		return "proceso";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Proceso objProceso, BindingResult binRes, Model model)
	throws ParseException
	{
		if(binRes.hasErrors()) 
		{
			model.addAttribute("listaAsesors", rService.listarAsesores());
			model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
			return "proceso";
		}
		else {

				boolean flag=aService.insertar(objProceso);
				if(flag)
					return "redirect:/proceso/listar";
				else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/proceso/irRegistrar";

				}
							
			}			
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Proceso objProceso, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException
	{
		if(binRes.hasErrors())
			return "redirect://proceso/listar";
		else {
				boolean flag=aService.modificar(objProceso);
				if(flag) {
					objRedir.addFlashAttribute("mensaje", "Se modificó correctamente");
					return "redirect:/Proceso/listar";
					}
				else {
					objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
					return "redirect:/Proceso/irRegistrar";

				}
							
			}			
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable Long id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		
		Optional<Proceso>objProceso=aService.buscarId(id);
		if(objProceso==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error al cargar");
			return "redirect://proceso/listar";
			
		}
		else
		{
			if(objProceso.isPresent())
				objProceso.ifPresent(o-> model.addAttribute("proceso",o));
			model.addAttribute("listaProcesos", aService.listar());
			model.addAttribute("listaEstudiantes", eService.listar());
			return "proceso";
		}
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String,Object>model, @RequestParam(value="id")Long id){
		try {
			if(id!=null&&id>0) {
				aService.eliminar(id);
				model.put("listaProcesos", aService.listar());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Sucedio un error");
			model.put("listaProcesos", aService.listar());
		}
		return "listProcesos";
	}
	
	@GetMapping("/form/{id}")
	public String newInvoice(@PathVariable(value = "id") Long id, Model model) {
		try {
			Optional<Usuario> estudiante = eService.buscarId(id);
			if (!estudiante.isPresent()) {
				model.addAttribute("info", "Estudiante no existe");
				return "redirect:/Proceso/listar";
			} else {
				Proceso Proceso = new Proceso();
				Proceso.setEstudiante(estudiante.get());
				Date date=new Date();
				DateFormat hourdateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
				String cod= String.valueOf(hourdateFormat.format(date));
				Proceso.setCodigoProceso(cod+"-PR");
				Date dateActual=new Date();
				//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Proceso.setFechaRegistro(dateActual);
				model.addAttribute("proceso", Proceso);
				model.addAttribute("listaAsesors", rService.listarAsesores());
				model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "proceso";
	}
	
}

