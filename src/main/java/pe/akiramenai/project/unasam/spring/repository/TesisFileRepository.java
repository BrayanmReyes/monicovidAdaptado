package pe.akiramenai.project.unasam.spring.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pe.akiramenai.project.unasam.spring.model.TesisFile;

@Repository
public interface TesisFileRepository extends CrudRepository<TesisFile,Long>{
	
	@Query("select f from TesisFile as f where f.tesis.id = ?1")
	List<TesisFile> findFileByUserId(Long tesisId);
	
	@Modifying
	@Query("delete from TesisFile as f where f.tesis.id = ?1 and f.modifiedName in (?2)")
	void deleteByUserIdAndImagesNames(Long id, List<String> remove);
	
	
	
}
