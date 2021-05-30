package pe.akiramenai.project.unasam.spring.service;

import java.util.List;
import java.util.Optional;

import pe.akiramenai.project.unasam.spring.model.Evaluacion;
import pe.akiramenai.project.unasam.spring.model.Usuario;;

public interface IEvaluacionService {

	public boolean insertar(Evaluacion Evaluacion);
	public boolean modificar(Evaluacion Evaluacion);
	public void eliminar(int idEvaluacion);
	public Optional<Evaluacion> buscarId(int idEvaluacion);
	public Optional<Evaluacion> listarId(int idEvaluacion);
	public List<Evaluacion> listar();
	public List<Evaluacion> buscarEvaluacion(int idEvaluacion);
	public List<Evaluacion>buscarEvaluacionDniEstudiante(String dni);
	public List<Usuario>buscarJuezDniEstudiante(String dni);
	public List<Evaluacion>buscarEvaluacionDniJuez(String dni);
	public List<Usuario>buscarJuezCodigoEstudiante(String codigo);
	public List<Evaluacion>buscarEvaluacionCodigoEstudiante(String codigo);
	public List<Evaluacion>buscarEvaluacionCodigoJuez(String codigo);
	public List<Usuario>buscarEstudianteCodigoJuez(String codigo);
}
