package pe.akiramenai.project.unasam.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import pe.akiramenai.project.unasam.spring.model.Tesis;

@Repository
public interface TesisRepository extends CrudRepository<Tesis,Long>{
	
	
}
