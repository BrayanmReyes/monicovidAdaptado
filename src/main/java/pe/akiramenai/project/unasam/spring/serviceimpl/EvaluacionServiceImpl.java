package pe.akiramenai.project.unasam.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.akiramenai.project.unasam.spring.model.Evaluacion;
import pe.akiramenai.project.unasam.spring.model.Usuario;
import pe.akiramenai.project.unasam.spring.repository.IEvaluacionDAO;
import pe.akiramenai.project.unasam.spring.repository.IUsuarioDAO;
import pe.akiramenai.project.unasam.spring.service.IEvaluacionService;

@Service
public class EvaluacionServiceImpl implements IEvaluacionService{

	@Autowired
	private IEvaluacionDAO dEvaluacion;
	
	@Autowired
	private IUsuarioDAO dUsuario;
	
	@Override
	@Transactional
	public boolean insertar(Evaluacion Evaluacion)
	{
		Evaluacion objEvaluacion=dEvaluacion.save(Evaluacion);
		if(objEvaluacion==null)
			return false;
		else
			return true;
			}
	
	@Override
	@Transactional
	public boolean modificar(Evaluacion Evaluacion)
	{
		boolean flag=false;
		try {
			dEvaluacion.save(Evaluacion);
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
	public void eliminar(int idEvaluacion) {
		dEvaluacion.deleteById(idEvaluacion);
	}
	
	@Override
	public Optional<Evaluacion>buscarId(int idEvaluacion){
		return dEvaluacion.findById(idEvaluacion);
	}
	
	@Override
	public Optional<Evaluacion>listarId(int idEvaluacion){
		return dEvaluacion.findById(idEvaluacion);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Evaluacion>listar(){
		return dEvaluacion.findAll();
	}
	
	@Override
	@Transactional
	public List<Evaluacion>buscarEvaluacion(int idEvaluacion){
		return dEvaluacion.buscarEvaluacion(idEvaluacion);
	}
	@Override
	@Transactional
	public List<Usuario>buscarJuezDniEstudiante(String dni){
		List<Evaluacion>lista= buscarEvaluacionDniEstudiante(dni);
		List<Usuario>listaJ=dUsuario.findAll();
		List<Usuario>listaR=new ArrayList<Usuario>();
		
		for(int i=0;i<lista.size();i++)
			for(int j=0;j<listaJ.size();j++)
			if(lista.get(i).getJuez1().getDni()==listaJ.get(j).getDni())
				listaR.add(listaJ.get(j));
					
		for(int i=0;i<lista.size();i++)
			for(int j=0;j<listaJ.size();j++)
			if(lista.get(i).getJuez2().getDni()==listaJ.get(j).getDni())
				listaR.add(listaJ.get(j));

		for(int i=0;i<lista.size();i++)
			for(int j=0;j<listaJ.size();j++)
			if(lista.get(i).getJuez3().getDni()==listaJ.get(j).getDni())
				listaR.add(listaJ.get(j));
		return listaR;
	}
	
	@Override
	@Transactional
	public List<Usuario>buscarJuezCodigoEstudiante(String codigo){
		List<Evaluacion>lista= buscarEvaluacionCodigoEstudiante(codigo);
		List<Usuario>listaJ=dUsuario.findAll();
		List<Usuario>listaR=new ArrayList<Usuario>();
		
		for(int i=0;i<lista.size();i++)
			for(int j=0;j<listaJ.size();j++)
			if(lista.get(i).getJuez1().getDni()==listaJ.get(j).getDni())
				listaR.add(listaJ.get(j));
					
		for(int i=0;i<lista.size();i++)
			for(int j=0;j<listaJ.size();j++)
			if(lista.get(i).getJuez2().getDni()==listaJ.get(j).getDni())
				listaR.add(listaJ.get(j));

		for(int i=0;i<lista.size();i++)
			for(int j=0;j<listaJ.size();j++)
			if(lista.get(i).getJuez3().getDni()==listaJ.get(j).getDni())
				listaR.add(listaJ.get(j));
		return listaR;
	}

	@Override
	@Transactional
	public List<Evaluacion>buscarEvaluacionCodigoEstudiante(String codigo){
		List<Evaluacion>lista= dEvaluacion.findAll();
		List<Evaluacion>listaR=new ArrayList<Evaluacion>();
		
		for(int i=0;i<lista.size();i++)
			if(lista.get(i).getAsesoria().getEstudiante().getUsername().equals(codigo))
				listaR.add(lista.get(i));
		return listaR;
	}

	@Override
	@Transactional
	public List<Evaluacion>buscarEvaluacionCodigoJuez(String codigo){
		List<Evaluacion>lista= dEvaluacion.findAll();
		List<Evaluacion>listaR=new ArrayList<Evaluacion>();
		
		for(int i=0;i<lista.size();i++)
			if(lista.get(i).getJuez1().getUsername().equals(codigo)||lista.get(i).getJuez2().getUsername().equals(codigo)||lista.get(i).getJuez3().getUsername().equals(codigo))
				listaR.add(lista.get(i));
		return listaR;
	}

	@Override
	@Transactional
	public List<Evaluacion>buscarEvaluacionDniEstudiante(String dni){
		List<Evaluacion>lista= dEvaluacion.findAll();
		List<Evaluacion>listaR=new ArrayList<Evaluacion>();
		
		for(int i=0;i<lista.size();i++)
			if(lista.get(i).getAsesoria().getEstudiante().getDni()==dni)
				listaR.add(lista.get(i));
		return listaR;
	}
	@Override
	@Transactional
	public List<Evaluacion>buscarEvaluacionDniJuez(String dni){
		List<Evaluacion>lista= dEvaluacion.findAll();
		List<Evaluacion>listaR=new ArrayList<Evaluacion>();
		
		for(int i=0;i<lista.size();i++)
			if(lista.get(i).getJuez1().getDni()==dni||lista.get(i).getJuez2().getDni()==dni||lista.get(i).getJuez3().getDni()==dni)
				listaR.add(lista.get(i));
		return listaR;
	}
	
	@Override
	@Transactional
	public List<Usuario>buscarEstudianteCodigoJuez(String codigo){
		List<Evaluacion>lista= buscarEvaluacionCodigoJuez(codigo);
		List<Usuario>listaE=dUsuario.findAll();
		List<Usuario>listaR=new ArrayList<Usuario>();
		
		for(int i=0;i<lista.size();i++)
			for(int j=0;j<listaE.size();j++)
			if(lista.get(i).getAsesoria().getEstudiante().getDni()==listaE.get(j).getDni())
				listaR.add(listaE.get(j));
		return listaR;
	}

}
