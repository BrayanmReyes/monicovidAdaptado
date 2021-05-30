package pe.akiramenai.project.unasam.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.akiramenai.project.unasam.spring.model.Evaluacion;

@Repository
public interface IEvaluacionDAO extends JpaRepository<Evaluacion, Integer>{
	@Query("from Evaluacion c where c.idEvaluacion like %:idEvaluacion%")
	List<Evaluacion>buscarEvaluacion(@Param("idEvaluacion")int idEvaluacion);

}
