package pe.akiramenai.project.unasam.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="proceso")
public class Proceso implements Serializable{
	
	private static final long serialVersionUID=10L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idProceso;
	
	@Column(name="codigoProceso")
	private String codigoProceso;
	
	@ManyToOne
	@JoinColumn(name="idEstudiante")
	private Usuario estudiante;
	
	@Column(name="nombreProceso")
	private String nombreProceso;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechaRegistro",nullable = false)
	private Date fechaRegistro;
	
	@Column(name="tipoProceso", nullable = false)
	private String tipoProceso;
		
	//RESOLUCION O CONTANCIA
	@Column(name="linkDocumento", nullable = false)
	private String linkDocumento;
	
	//PRESENTACION
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechaPresentacion")
	private Date fechaPresentacion;
	
	public Proceso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Proceso(Long idProceso,String codigoProceso,Usuario estudiante, String nombreProceso, Date fechaRegistro, String tipoProceso,
			String linkDocumento, Date fechaPresentacion) {
		super();
		this.idProceso = idProceso;
		this.codigoProceso = codigoProceso;
		this.estudiante = estudiante;
		this.nombreProceso = nombreProceso;
		this.fechaRegistro = fechaRegistro;
		this.tipoProceso = tipoProceso;
		this.linkDocumento = linkDocumento;
		this.fechaPresentacion = fechaPresentacion;
	}

	public Long getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}

	public Usuario getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Usuario estudiante) {
		this.estudiante = estudiante;
	}

	public String getNombreProceso() {
		return nombreProceso;
	}

	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getTipoProceso() {
		return tipoProceso;
	}

	public void setTipoProceso(String tipoProceso) {
		this.tipoProceso = tipoProceso;
	}

	public String getLinkDocumento() {
		return linkDocumento;
	}

	public void setLinkDocumento(String linkDocumento) {
		this.linkDocumento = linkDocumento;
	}

	public Date getFechaPresentacion() {
		return fechaPresentacion;
	}

	public void setFechaPresentacion(Date fechaPresentacion) {
		this.fechaPresentacion = fechaPresentacion;
	}

	public String getCodigoProceso() {
		return codigoProceso;
	}

	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	
}
