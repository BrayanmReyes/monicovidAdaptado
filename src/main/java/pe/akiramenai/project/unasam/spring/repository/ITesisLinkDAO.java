package pe.akiramenai.project.unasam.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.akiramenai.project.unasam.spring.model.TesisLink;

@Repository
public interface ITesisLinkDAO extends JpaRepository<TesisLink, Long>{
	
	@Query("from TesisLink c where c.name like %:name%")
	List<TesisLink>buscarTesis(@Param("name")String name);
	
	@Query("from TesisLink c where c.name like %:name%")
	List<TesisLink> buscarByNombreTesis(@Param("name") String name);
	
	@Query("from TesisLink c where c.estudiante.apellido like %:apellido%")
	List<TesisLink> buscarByTesistaApellido(@Param("apellido") String apellido);
	

}
