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
@Table(name="Evaluacion")
public class Evaluacion implements Serializable{

	private static final long serialVersionUID=10L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEvaluacion;
	
	@Column(name="codigoEvaluacion", nullable=false)
	private String codigoEvaluacion;
	
	@ManyToOne
	@JoinColumn(name="idAsesoria")
	private Asesoria asesoria;
	
	@ManyToOne
	@JoinColumn(name="idJuez1")
	private Usuario juez1;
	
	@ManyToOne
	@JoinColumn(name="idJuez2")
	private Usuario juez2;
	
	@ManyToOne
	@JoinColumn(name="idJuez3")
	private Usuario juez3;
	//
	
	public Evaluacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Evaluacion(int idEvaluacion, String codigoEvaluacion, Asesoria asesoria, Usuario juez1, Usuario juez2,
			Usuario juez3) {
		super();
		this.idEvaluacion = idEvaluacion;
		this.codigoEvaluacion = codigoEvaluacion;
		this.asesoria = asesoria;
		this.juez1 = juez1;
		this.juez2 = juez2;
		this.juez3 = juez3;
	}



	public int getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(int idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	
	public Asesoria getAsesoria() {
		return asesoria;
	}


	public void setAsesoria(Asesoria asesoria) {
		this.asesoria = asesoria;
	}


	public Usuario getJuez1() {
		return juez1;
	}

	public void setJuez1(Usuario juez1) {
		this.juez1 = juez1;
	}

	public Usuario getJuez2() {
		return juez2;
	}

	public void setJuez2(Usuario juez2) {
		this.juez2 = juez2;
	}

	public Usuario getJuez3() {
		return juez3;
	}

	public void setJuez3(Usuario juez3) {
		this.juez3 = juez3;
	}

	public String getCodigoEvaluacion() {
		return codigoEvaluacion;
	}

	public void setCodigoEvaluacion(String codigoEvaluacion) {
		this.codigoEvaluacion = codigoEvaluacion;
	}


	
}
