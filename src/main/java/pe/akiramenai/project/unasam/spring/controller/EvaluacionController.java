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
import pe.akiramenai.project.unasam.spring.model.Evaluacion;
import pe.akiramenai.project.unasam.spring.model.Asesoria;
import pe.akiramenai.project.unasam.spring.model.Email;
import pe.akiramenai.project.unasam.spring.model.Usuario;
import pe.akiramenai.project.unasam.spring.service.IUsuarioService;
import pe.akiramenai.project.unasam.spring.service.IAsesoriaService;
import pe.akiramenai.project.unasam.spring.service.IEvaluacionService;
import pe.akiramenai.project.unasam.spring.service.ISesionService;

@Controller
@RequestMapping("/evaluacion")
public class EvaluacionController{

	@Autowired
	private IEvaluacionService evService;
	@Autowired
	private IUsuarioService aService;//asesor
	@Autowired
	private IUsuarioService eService;//estudiante
	@Autowired
	private IUsuarioService jService;//juez
	@Autowired
	private IAsesoriaService asService;
	@Autowired
	private ISesionService sService;
	
	private String back;
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object>model)
	{
		model.put("listaEvaluacions", evService.listar());
		model.put("listaAsesors", aService.listarAsesores());
		model.put("listaEstudiantes", eService.listarEstudiantes());
		model.put("listaAsesorias", asService.listar());
		model.put("listaJuezs", jService.listarJueces());
		return "listEvaluaciones";
	}
		
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object>model, @ModelAttribute Evaluacion Evaluacion)
	{
		evService.listarId(Evaluacion.getIdEvaluacion());
		return "listEvaluaciones";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model)
	{	
		model.addAttribute("evaluacion", new Evaluacion());
		model.addAttribute("listaAsesors", aService.listarAsesores());
		model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
		model.addAttribute("listaAsesorias", asService.listar());
		model.addAttribute("listaJuezs", jService.listarJueces());
		return "evaluacion";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Evaluacion objEvaluacion, BindingResult binRes, Model model)
	throws ParseException
	{
		if(binRes.hasErrors()) 
		{
			model.addAttribute("listaAsesors", aService.listarAsesores());
			model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
			model.addAttribute("listaAsesorias", asService.listar());
			model.addAttribute("listaJuezs", jService.listarJueces());
			return "evaluacion";
		}
		else {
				boolean flag=evService.insertar(objEvaluacion);
				if(flag)
					return "redirect:/evaluacion/listar";
				else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return "redirect:/evaluacion/irRegistrar";

				}
							
			}			
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Evaluacion objEvaluacion, BindingResult binRes, Model model, RedirectAttributes objRedir)
	throws ParseException
	{
		if(binRes.hasErrors())
			return "redirect://evaluacion/listar";
		else {
				boolean flag=evService.modificar(objEvaluacion);
				if(flag) {
					objRedir.addFlashAttribute("mensaje", "Se modificó correctamente");
					return "redirect:/evaluacion/listar";
					}
				else {
					objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
					return "redirect:/evaluacion/listar";

				}
							
			}			
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
	throws ParseException{
		
		Optional<Evaluacion>objEvaluacion=evService.buscarId(id);
		if(objEvaluacion==null)
		{
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error al cargar");
			return "redirect://evaluacion/listar";
			
		}
		else
		{
			if(objEvaluacion.isPresent())
				objEvaluacion.ifPresent(o-> model.addAttribute("evaluacion",o));
			model.addAttribute("listaAsesors", aService.listarAsesores());
			model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
			model.addAttribute("listaAsesorias", asService.listar());
			model.addAttribute("listaJuezs", jService.listarJueces());
			return "evaluacion";
		}
	}	
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String,Object>model, @RequestParam(value="id")Integer id){
		try {
			if(id!=null&&id>0) {
				evService.eliminar(id);
				model.put("listaEvaluacions", evService.listar());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Sucedio un error");
			model.put("listaEvaluacions", evService.listar());
		}
		return "listEvaluacions";
	}
	
	@GetMapping("/form/{id}")
	public String newInvoice(@PathVariable(value = "id") Long id, Model model) {
		try {
			//Optional<Estudiante> estudiante = eService.buscarId(idEstudiante);
			Optional<Asesoria> asesoria = asService.buscarByEstudiante(id);
			if (!asesoria.isPresent()) {
				model.addAttribute("info", "Asesoria no existe");
				return "redirect:/usuario/listaProcesos";
			} else {
				Evaluacion Evaluacion = new Evaluacion();
				Evaluacion.setAsesoria(asesoria.get());
				Date date=new Date();
				DateFormat hourdateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
				String cod= String.valueOf(hourdateFormat.format(date));
				Evaluacion.setCodigoEvaluacion(cod+"-EV");
				model.addAttribute("Evaluacion", Evaluacion);
				model.addAttribute("listaAsesors", aService.listarAsesores());
				model.addAttribute("listaAsesorias", asService.listar());
				model.addAttribute("listaEstudiantes", eService.listarEstudiantes());
				model.addAttribute("listaJuezs", jService.listarJueces());
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "DesignarEvaluadores";
	}
	@GetMapping("/documentos/{idEstudiante}")
	public String newDocumentos(@PathVariable(value = "idEstudiante") Long idEstudiante, Model model) {
		try {
			Optional<Usuario> estudiante = eService.buscarId(idEstudiante);
			if (!estudiante.isPresent()) {
				model.addAttribute("info", "Estudiante no existe");
				return "redirect:/reserva/listar";
			} else {
				return "SeleccionarDocumento";
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "SeleccionarDocumento";
	}
	
	@GetMapping("/correo/{idEstudiante}")
	public String newCorreo(@PathVariable(value = "idEstudiante") Long idEstudiante, Model model) {
		try {
			Optional<Usuario> objEstudiante = eService.buscarId(idEstudiante);
			if (!objEstudiante.isPresent()) {
				model.addAttribute("info", "Estudiante no existe");
				return "redirect:/usuario/lista";
			} else {
				if(objEstudiante.isPresent())
					objEstudiante.ifPresent(o-> model.addAttribute("listaEstudiantes",eService.buscarUsuario(o.getDni())));
				model.addAttribute("email", new Email());
				back="redirect:/usuario/lista";
				return "EnviarCorreo";
				}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "redirect:/usuario/lista";
	}
	
	
	@RequestMapping("/enviarCorreo")
	public String enviarCorreo(@ModelAttribute @Valid Email objEmail,  Model model)
	throws ParseException
	{
		boolean flag=false;
		int usuario=sService.elegirUsuarioRecuperar(objEmail.getToEmail());			
		if(usuario>0){
		if(sService.sendMail(objEmail.getToEmail(),objEmail.getAsuntoEmail(),objEmail.getMensajeEmail()))
					flag=true;
				if(flag) {
					model.addAttribute("mensaje", "Mensaje enviado con Éxito");
					return "redirect:/usuario/lista";
					}
				else {
					model.addAttribute("mensaje", "Ocurrió un error");
					return back;
					}			
		}
		else{
			model.addAttribute("mensaje", "Ocurrió un error");
			return back;
			}
	}

	@RequestMapping("/back")
	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}
	
}