package pe.akiramenai.project.unasam.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="tesis")
public class TesisLink implements Serializable{
	
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
	

	
	public TesisLink() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public TesisLink(Long id, String name,String fileslink ,Usuario estudiante) {
		super();
		this.id = id;
		this.name = name;
		this.estudiante=estudiante;
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
