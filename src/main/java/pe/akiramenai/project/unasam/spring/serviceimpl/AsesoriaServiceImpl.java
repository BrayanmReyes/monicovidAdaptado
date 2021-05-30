package pe.akiramenai.project.unasam.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.akiramenai.project.unasam.spring.model.Asesoria;
import pe.akiramenai.project.unasam.spring.repository.IAsesoriaDAO;
import pe.akiramenai.project.unasam.spring.service.IAsesoriaService;

@Service
public class AsesoriaServiceImpl implements IAsesoriaService{

	@Autowired
	private IAsesoriaDAO dAsesoria;
	
	@Override
	@Transactional
	public boolean insertar(Asesoria Asesoria)
	{
		Asesoria objAsesoria=dAsesoria.save(Asesoria);
		if(objAsesoria==null)
			return false;
		else
			return true;
			}
	
	@Override
	@Transactional
	public boolean modificar(Asesoria Asesoria)
	{
		boolean flag=false;
		try {
			dAsesoria.save(Asesoria);
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
	public void eliminar(int idAsesoria) {
		dAsesoria.deleteById(idAsesoria);
	}
	
	@Override
	public Optional<Asesoria>buscarId(int idAsesoria){
		return dAsesoria.findById(idAsesoria);
	}
	
	@Override
	public Optional<Asesoria>listarId(int idAsesoria){
		return dAsesoria.findById(idAsesoria);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Asesoria>listar(){
		return dAsesoria.findAll();
	}
	
	@Override
	@Transactional
	public List<Asesoria>buscarAsesoria(int idAsesoria){
		return dAsesoria.buscarAsesoria(idAsesoria);
	}

	@Override
	public Optional<Asesoria> buscarByEstudiante(Long idEstudiante) {
		
		return dAsesoria.findByEstudiante(idEstudiante);
	}
	@Override
	@Transactional
	public List<Asesoria>buscarAsesoriaDniEstudiante(String dni){
		List<Asesoria>lista= dAsesoria.findAll();
		List<Asesoria>listaR=new ArrayList<Asesoria>();
		for(int i=0;i<lista.size();i++)
			if(lista.get(i).getEstudiante().getDni()==dni)
				listaR.add(lista.get(i));
		return listaR;
	}
	@Override
	@Transactional
	public List<Asesoria>buscarAsesoriaCodigoEstudiante(String codigo){
		List<Asesoria>lista= dAsesoria.findAll();
		List<Asesoria>listaR=new ArrayList<Asesoria>();
		for(int i=0;i<lista.size();i++)
			if(lista.get(i).getEstudiante().getUsername().equals(codigo))
				listaR.add(lista.get(i));
		return listaR;
	}
	
	@Override
	@Transactional
	public List<Asesoria>buscarAsesoriaCodigoAsesor(String codigo){
		List<Asesoria>lista= dAsesoria.findAll();
		List<Asesoria>listaR=new ArrayList<Asesoria>();
		for(int i=0;i<lista.size();i++)
			if(lista.get(i).getAsesor().getUsername().equals(codigo))
				listaR.add(lista.get(i));
		return listaR;
	}
	
	@Override
	@Transactional
	public List<Asesoria>buscarAsesoriaDniAsesor(String dni){
		List<Asesoria>lista= dAsesoria.findAll();
		List<Asesoria>listaR=new ArrayList<Asesoria>();
		for(int i=0;i<lista.size();i++)
			if(lista.get(i).getAsesor().getDni()==dni)
				listaR.add(lista.get(i));
		return listaR;
	}
}
