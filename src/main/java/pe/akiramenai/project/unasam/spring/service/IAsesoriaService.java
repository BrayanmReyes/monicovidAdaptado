package pe.akiramenai.project.unasam.spring.service;

import java.util.List;
import java.util.Optional;

import pe.akiramenai.project.unasam.spring.model.Asesoria;

public interface IAsesoriaService {

	public boolean insertar(Asesoria Asesoria);
	public boolean modificar(Asesoria Asesoria);
	public void eliminar(int idAsesoria);
	public Optional<Asesoria> buscarId(int idAsesoria);
	public Optional<Asesoria> listarId(int idAsesoria);
	public List<Asesoria> listar();
	public List<Asesoria> buscarAsesoria(int idAsesoria);
	public Optional<Asesoria> buscarByEstudiante(Long idEstudiante);
	public List<Asesoria>buscarAsesoriaDniEstudiante(String dni);
	public List<Asesoria>buscarAsesoriaCodigoEstudiante(String codigo);
	public List<Asesoria>buscarAsesoriaCodigoAsesor(String codigo);
	public List<Asesoria>buscarAsesoriaDniAsesor(String dni);
}
