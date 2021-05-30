package pe.akiramenai.project.unasam.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.akiramenai.project.unasam.spring.model.Proceso;
import pe.akiramenai.project.unasam.spring.repository.IProcesoDAO;
import pe.akiramenai.project.unasam.spring.service.IProcesoService;

@Service
public class ProcesoServiceImpl implements IProcesoService{
	@Autowired
	private IProcesoDAO dProceso;
	
	private void aMayusculas(Proceso pro){
		pro.setNombreProceso(pro.getNombreProceso().toUpperCase());
	}
	
	@Override
	@Transactional
	public boolean insertar(Proceso Proceso)
	{
		aMayusculas(Proceso);
		Proceso objProceso=dProceso.save(Proceso);
		if(objProceso==null)
			return false;
		else
			return true;
			}
	
	@Override
	@Transactional
	public boolean modificar(Proceso Proceso)
	{
		boolean flag=false;
		try {
			dProceso.save(Proceso);
			flag=true;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return flag;			
	}
	
	@Override
	@Transactional
	public void eliminar(Long idProceso) {
		dProceso.deleteById(idProceso);
	}
	
	@Override
	public Optional<Proceso>buscarId(Long idProceso){
		return dProceso.findById(idProceso);
	}
	
	@Override
	public Optional<Proceso>listarId(Long idProceso){
		return dProceso.findById(idProceso);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Proceso>listar(){
		return dProceso.findAll();
	}

	@Override
	public List<Proceso> listarbyEstudianteDNI(String dni) {
		return dProceso.buscarbyEstudianteDNI(dni);
	}

	@Override
	public List<Proceso> listarProcesobyCodigoTesista(String username) {
		return dProceso.buscarProcesobyCodigoTesista(username);
	}
	

}
