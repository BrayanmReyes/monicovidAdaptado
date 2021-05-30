package pe.akiramenai.project.unasam.spring.serviceimpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.akiramenai.project.unasam.spring.model.TesisLink;
import pe.akiramenai.project.unasam.spring.repository.ITesisLinkDAO;
import pe.akiramenai.project.unasam.spring.service.ITesisLinkService;

@Service
@Transactional
public class TesisLinkServiceImpl implements ITesisLinkService{

	@Autowired
	private ITesisLinkDAO dTesis;
	
	private void aMayusculas(TesisLink obj){
		obj.setName(obj.getName().toUpperCase());
	}

	@Override
	@Transactional
	public boolean insertar(TesisLink Tesis)
	{
		aMayusculas(Tesis);
		TesisLink objTesis=dTesis.save(Tesis);
		if(objTesis==null)
			return false;
		else
			return true;
			}
	
	@Override
	@Transactional
	public boolean modificar(TesisLink Tesis)
	{
		boolean flag=false;
		try {
			dTesis.save(Tesis);
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
	public void eliminar(Long idTesis) {
		dTesis.deleteById(idTesis);
	}
	
	@Override
	public Optional<TesisLink>buscarId(Long idTesis){
		return dTesis.findById(idTesis);
	}
	
	@Override
	public Optional<TesisLink>listarId(Long idTesis){
		return dTesis.findById(idTesis);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<TesisLink>listar(){
		return dTesis.findAll();
	}
	
	@Override
	@Transactional
	public List<TesisLink>buscarTesis(String dniTesis){
		return dTesis.buscarTesis(dniTesis);
	}

	@Override
	@Transactional
	public List<TesisLink> buscarByNombreTesis(String name) {
		return dTesis.buscarByNombreTesis(name);
	}

	@Override
	@Transactional
	public List<TesisLink> buscarByTesistaApellido(String apellido) {
		return dTesis.buscarByTesistaApellido(apellido);
	}
	
	


	/*	
 	@Override
	public TesisLink findById(Long tesisId) {
		Optional<TesisLink> tesis = tTesis.findById(tesisId);
		if(tesis.isPresent()) {
			return tesis.get();
		}
		return null;
	}

	@Override
	public List<TesisFile> findFilesByUserId(Long tesisId) {
		
		return uTesisFile.findFileByUserId(tesisId);
	}
	
 
	@Override
	public List<TesisLink> FilesByUserId() {
		
		return (List<TesisLink>) uTesisFile.findAll();
	}
	@Override
	public Tesis save(Tesis tesis) {
		//tesis.setDate(new Date());
		Tesis dbTesis = dTesis.save(tesis);
		if(dbTesis!=null && tesis.getFiles()!=null && tesis.getFiles().size()>0) {
			for(MultipartFile file: tesis.getFiles()) {
				String fileName =file.getOriginalFilename();
				String modifiedFileName =FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
				File storeFile=uTesis.getFilePath(modifiedFileName,"images");
				if(storeFile!=null) {
					try {
						FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			TesisFile files=new TesisFile();
			files.setFileExtension(FilenameUtils.getExtension(fileName));
			files.setFileName(fileName);
			files.setModifiedName(modifiedFileName);
			files.setTesis(dbTesis);
			
			uTesisFile.save(files);
			}
		}
		return dbTesis;
	}
	@Override
	public Tesis update(Tesis tesis) {
		//tesis.setUpdateDate(new Date());
				Tesis dbTesis = dTesis.save(tesis);
				if(tesis!=null && tesis.getRemove()!=null && tesis.getRemove().size()>0){
					uTesisFile.deleteByUserIdAndImagesNames(tesis.getId(),tesis.getRemove());
					for(String file : tesis.getRemove()) {
						File dbFile=new File(context.getRealPath("/images/"+File.separator+file));  
						if(dbFile.exists()) {
							dbFile.delete();
						}
					}
					
				}

				if(dbTesis!=null && tesis.getFiles()!=null && tesis.getFiles().size()>0) {
					for(MultipartFile file: tesis.getFiles()) {
						String fileName =file.getOriginalFilename();
						String modifiedFileName =FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
						File storeFile=uTesis.getFilePath(modifiedFileName,"images");
						if(storeFile!=null) {
							try {
								FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
					TesisFile files=new TesisFile();
					files.setFileExtension(FilenameUtils.getExtension(fileName));
					files.setFileName(fileName);
					files.setModifiedName(modifiedFileName);
					files.setTesis(dbTesis);
					
					uTesisFile.save(files);
					}
				}
				return dbTesis;
	}

	*/


	
}
