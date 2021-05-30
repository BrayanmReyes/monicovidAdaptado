package pe.akiramenai.project.unasam.spring.service;

import java.util.List;
import java.util.Optional;

import pe.akiramenai.project.unasam.spring.model.Tesis;
import pe.akiramenai.project.unasam.spring.model.TesisFile;

public interface ITesisService {

	public boolean insertar(Tesis Tesis);
	public boolean modificar(Tesis Tesis);
	public void eliminar(int idTesis);
	public Optional<Tesis> buscarId(int idTesis);
	public Optional<Tesis> listarId(int idTesis);
	public List<Tesis> listar();
	public List<Tesis> buscarTesis(String dniTesis);
	public Tesis save(Tesis tesis);
	public Tesis findById(Long tesisId);
	public List<TesisFile> findFilesByUserId(Long tesisId);
	public List<TesisFile> FilesByUserId();
	public Tesis update(Tesis tesis);

	

}
