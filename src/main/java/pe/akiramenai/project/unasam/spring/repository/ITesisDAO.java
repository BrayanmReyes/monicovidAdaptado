package pe.akiramenai.project.unasam.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.akiramenai.project.unasam.spring.model.Tesis;

@Repository
public interface ITesisDAO extends JpaRepository<Tesis, Integer>{
	
	@Query("from Tesis c where c.name like %:name%")
	Tesis findByUsername(@Param("name")String name);
	
	@Query("from Tesis c where c.name like %:name%")
	List<Tesis>buscarTesis(@Param("name")String name);

}
