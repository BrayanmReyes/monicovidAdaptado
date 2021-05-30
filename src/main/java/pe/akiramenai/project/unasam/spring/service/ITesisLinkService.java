package pe.akiramenai.project.unasam.spring.service;

import java.util.List;
import java.util.Optional;

import pe.akiramenai.project.unasam.spring.model.TesisLink;

public interface ITesisLinkService {

	public boolean insertar(TesisLink Tesis);
	public boolean modificar(TesisLink Tesis);
	public void eliminar(Long idTesis);
	public Optional<TesisLink> buscarId(Long idTesis);
	public Optional<TesisLink> listarId(Long idTesis);
	public List<TesisLink> listar();
	public List<TesisLink> buscarTesis(String dniTesis);
	//public TesisLink findById(Long tesisId);
	public List<TesisLink> buscarByNombreTesis(String name);
	public List<TesisLink> buscarByTesistaApellido(String apellido);


	

}
