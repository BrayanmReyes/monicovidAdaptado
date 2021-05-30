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
@Table(name="Asesoria")
public class Asesoria implements Serializable{

	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAsesoria;
	
	@Column(name="codigoAsesoria", nullable=false)
	private String codigoAsesoria;
	
	@ManyToOne
	@JoinColumn(name="idEstudiante")
	private Usuario estudiante;
	
	@ManyToOne
	@JoinColumn(name="idAsesor")
	private Usuario asesor;
	
	public Asesoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Asesoria(int idAsesoria, String codigoAsesoria, Usuario estudiante, Usuario asesor) {
		super();
		this.idAsesoria = idAsesoria;
		this.codigoAsesoria = codigoAsesoria;
		this.estudiante = estudiante;
		this.asesor = asesor;
	}

	public int getIdAsesoria() {
		return idAsesoria;
	}

	public void setIdAsesoria(int idAsesoria) {
		this.idAsesoria = idAsesoria;
	}

	public Usuario getAsesor() {
		return asesor;
	}

	public void setAsesor(Usuario asesor) {
		this.asesor = asesor;
	}

	public Usuario getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Usuario estudiante) {
		this.estudiante = estudiante;
	}

	public String getCodigoAsesoria() {
		return codigoAsesoria;
	}

	public void setCodigoAsesoria(String codigoAsesoria) {
		this.codigoAsesoria = codigoAsesoria;
	}


	
}
