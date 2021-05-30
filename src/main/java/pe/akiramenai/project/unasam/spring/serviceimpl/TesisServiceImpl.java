package pe.akiramenai.project.unasam.spring.serviceimpl;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.akiramenai.project.unasam.spring.model.Tesis;
import pe.akiramenai.project.unasam.spring.model.TesisFile;
import pe.akiramenai.project.unasam.spring.repository.ITesisDAO;
import pe.akiramenai.project.unasam.spring.repository.TesisFileRepository;
import pe.akiramenai.project.unasam.spring.repository.TesisRepository;
import pe.akiramenai.project.unasam.spring.service.ITesisService;
import pe.akiramenai.project.unasam.spring.service.IUploadPathService;

import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class TesisServiceImpl implements ITesisService{

	@Autowired
	private ITesisDAO dTesis;
	
	@Autowired
	private TesisRepository tTesis;
	
	@Autowired
	private IUploadPathService uTesis;
	
	@Autowired
	private TesisFileRepository uTesisFile;
	
	@Autowired
	private ServletContext context;
	
	
	@Override
	@Transactional
	public boolean insertar(Tesis Tesis)
	{
		Tesis objTesis=dTesis.save(Tesis);
		if(objTesis==null)
			return false;
		else
			return true;
			}
	
	@Override
	@Transactional
	public boolean modificar(Tesis Tesis)
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
	public void eliminar(int idTesis) {
		dTesis.deleteById(idTesis);
	}
	
	@Override
	public Optional<Tesis>buscarId(int idTesis){
		return dTesis.findById(idTesis);
	}
	
	@Override
	public Optional<Tesis>listarId(int idTesis){
		return dTesis.findById(idTesis);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Tesis>listar(){
		return dTesis.findAll();
	}
	@Override
	public List<TesisFile> FilesByUserId() {
		
		return (List<TesisFile>) uTesisFile.findAll();
	}
	
	@Override
	@Transactional
	public List<Tesis>buscarTesis(String dniTesis){
		return dTesis.buscarTesis(dniTesis);
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
	public Tesis findById(Long tesisId) {
		Optional<Tesis> tesis = tTesis.findById(tesisId);
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

	


	
}
