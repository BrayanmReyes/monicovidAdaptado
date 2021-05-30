package pe.akiramenai.project.unasam.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.akiramenai.project.unasam.spring.model.Asesoria;

@Repository
public interface IAsesoriaDAO extends JpaRepository<Asesoria, Integer>{
	@Query("from Asesoria c where c.idAsesoria like %:idAsesoria%")
	List<Asesoria>buscarAsesoria(@Param("idAsesoria")int idAsesoria);
	
	@Query("select c from Asesoria as c where c.estudiante.id = ?1")
	Optional<Asesoria>findByEstudiante(Long idEstudiante);
	

}
