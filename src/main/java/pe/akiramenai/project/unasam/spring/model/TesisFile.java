package pe.akiramenai.project.unasam.spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="tesis_files")
public class TesisFile implements Serializable{
	
	private static final long serialVersionUID=6L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String fileName;
	
	private String modifiedName;
	
	private String fileExtension;
	
	@ManyToOne
	@JoinColumn(name="tesis_id")
	private Tesis tesis;
	
	@Transient
	private List<MultipartFile> files = new ArrayList<MultipartFile>();
	
	@Transient
	private List<String> remove = new ArrayList<String>();

		
	public TesisFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public TesisFile(Long id, String fileName, String modifiedName, String fileExtension, Tesis tesis,
			List<MultipartFile> files, List<String> remove) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.modifiedName = modifiedName;
		this.fileExtension = fileExtension;
		this.tesis = tesis;
		this.files = files;
		this.remove = remove;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getModifiedName() {
		return modifiedName;
	}

	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public Tesis getTesis() {
		return tesis;
	}

	public void setTesis(Tesis tesis) {
		this.tesis = tesis;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public List<String> getRemove() {
		return remove;
	}

	public void setRemove(List<String> remove) {
		this.remove = remove;
	}
	


	
}
