package pe.akiramenai.project.unasam.spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
@Table(name="tesis")
public class Tesis implements Serializable{
	
	private static final long serialVersionUID=6L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="nombreTesis")
	private String name;
	
	@Column(name="fileslink")
	private String fileslink;
	
	@ManyToOne
	@JoinColumn(name="idEstudiante")
	private Usuario estudiante;
	
	@Transient
	private List<MultipartFile> files = new ArrayList<MultipartFile>();
	
	@Transient
	private List<String> remove = new ArrayList<String>();
	
	public Tesis() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public Tesis(Long id, String name,String fileslink ,Usuario estudiante ,List<MultipartFile> files, List<String> remove) {
		super();
		this.id = id;
		this.name = name;
		this.estudiante=estudiante;
		this.files = files;
		this.remove = remove;
		this.fileslink=fileslink;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Usuario getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Usuario estudiante) {
		this.estudiante = estudiante;
	}

	public String getFileslink() {
		return fileslink;
	}

	public void setFileslink(String fileslink) {
		this.fileslink = fileslink;
	}
	
}
