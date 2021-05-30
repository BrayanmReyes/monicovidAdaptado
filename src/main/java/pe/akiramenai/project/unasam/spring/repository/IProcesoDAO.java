package pe.akiramenai.project.unasam.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.akiramenai.project.unasam.spring.model.Proceso;

@Repository
public interface IProcesoDAO extends JpaRepository<Proceso, Long>{
	//@Query("from Proceso c where c.dniProceso like %:dniProceso%")
	//List<Proceso>buscarProceso(@Param("dniProceso")String dniProceso);
	
	//@Query("from Proceso c where c.emailProceso like %:emailProceso%")
	//List<Proceso>buscarEmailProceso(@Param("emailProceso")String emailProceso);
	
	@Query("from Proceso c where c.idProceso like %:idProceso%")
	List<Proceso>buscarIdProceso(@Param("idProceso")int idProceso);
	
	@Query("from Proceso c where c.estudiante.dni like %:dni%")
	List<Proceso> buscarbyEstudianteDNI(@Param("dni")String dni);
	
	@Query("from Proceso c where c.estudiante.username like %:username%")
	List<Proceso> buscarProcesobyCodigoTesista(@Param("username")String username);

}
