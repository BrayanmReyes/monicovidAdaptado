package pe.akiramenai.project.unasam.spring.service;

import java.util.List;
import java.util.Optional;

import pe.akiramenai.project.unasam.spring.model.Proceso;

public interface IProcesoService {
	public boolean insertar(Proceso Proceso);
	public boolean modificar(Proceso Proceso);
	public void eliminar(Long idProceso);
	public Optional<Proceso> buscarId(Long idProceso);
	public Optional<Proceso> listarId(Long idProceso);
	public List<Proceso> listar();
	public List<Proceso> listarbyEstudianteDNI(String dni);
	public List<Proceso> listarProcesobyCodigoTesista(String username);
}
