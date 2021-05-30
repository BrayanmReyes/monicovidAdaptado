package pe.akiramenai.project.unasam.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.akiramenai.project.unasam.spring.model.Role;
import pe.akiramenai.project.unasam.spring.model.Usuario;
import pe.akiramenai.project.unasam.spring.repository.IRoleDAO;
import pe.akiramenai.project.unasam.spring.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	@Autowired
	private IRoleDAO dRole;
	
	@Override
	@Transactional
	public boolean insertar(Role Role)
	{
		Role objRole=dRole.save(Role);
		if(objRole==null)
			return false;
		else
			return true;
			}
	
	@Override
	@Transactional
	public boolean modificar(Role Role)
	{
		boolean flag=false;
		try {
			dRole.save(Role);
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
	public void eliminar(Long id) {
		dRole.deleteById(id);
	}
	
	@Override
	public Optional<Role>buscarId(Long id){
		return dRole.findById(id);
	}
	
	@Override
	public Optional<Role>listarId(Long id){
		return dRole.findById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Role>listar(){
		return dRole.findAll();
	}
	
	
	@Override
	@Transactional
	public List<Role> listarTiposRole() {
		List<Role>listaR=new ArrayList<Role>();
		
		Role rol= new Role((long)1,"Administrador");
		Role rol0= new Role((long)2,"Usuario");
		Role rol1= new Role((long)2,"Estudiante");
		Role rol2= new Role((long)3,"Asesor");
		Role rol3= new Role((long)4,"Juez");
		Role rol4= new Role((long)4,"Mixto");
		
		listaR.add(rol);
		listaR.add(rol0);
		listaR.add(rol1);
		listaR.add(rol2);
		listaR.add(rol3);
		listaR.add(rol4);
		return listaR;
	}
	
	@Override
	@Transactional
	public int insertarRolexUsuario(Usuario usuario)
	{
		List<Role>lista=dRole.findAll();
		Role rol=new Role();
		if(usuario.getAutoridad().contains("ADMIN")) {
			rol.setAuthority("ROLE_ADMIN");
		}
		else{
			if(usuario.getAutoridad().contains("USUARIO")) {
			rol.setId((long)lista.size());
			rol.setAuthority("ROLE_USER");
			}
			
		else
			if(usuario.getAutoridad().contains("ESTUDIANTE")) {
				rol.setId((long)lista.size());
				rol.setAuthority("ROLE_ESTUDIANTE");
				}
			else { 
				if(usuario.getAutoridad().contains("ASESOR")) {
					rol.setId((long)lista.size());
					rol.setAuthority("ROLE_ASESOR");
				}
				else {
					if(usuario.getAutoridad().contains("JUEZ")) {
						rol.setId((long)lista.size());
						rol.setAuthority("ROLE_JUEZ");
					}
					else if (usuario.getAutoridad().contains("MIXTO")) {
						rol.setId((long)lista.size());
						rol.setAuthority("ROLE_MIXTO");
					}
						
					}
				}
			}

		return dRole.insertRole(rol.getAuthority(), usuario.getId());
			
	}
}
