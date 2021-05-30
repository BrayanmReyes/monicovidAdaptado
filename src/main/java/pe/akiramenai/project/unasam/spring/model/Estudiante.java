package pe.akiramenai.project.unasam.spring.model;
/*
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="estudiante")
public class Estudiante implements Serializable{

	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@OneToOne(mappedBy="estudiante")
	private Usuario usuario;
	
	@PastOrPresent(message = "No puedes seleccionar una fecha futura")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechaIngreso",nullable = false)
	private Date fechaIngreso;

	@PastOrPresent(message = "No puedes seleccionar una fecha futura")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechaEgreso",nullable = false)
	private Date fechaEgreso;

	public Estudiante() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estudiante(Long id) {
		super();
		this.id= id;
	}

	public Long getId() {
		return id;
	}

	public void setIdEstudiante(Long id) {
		this.id= id;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	
	


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaEgreso == null) ? 0 : fechaEgreso.hashCode());
		result = prime * result + ((fechaIngreso == null) ? 0 : fechaIngreso.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		if (fechaEgreso == null) {
			if (other.fechaEgreso != null)
				return false;
		} else if (!fechaEgreso.equals(other.fechaEgreso))
			return false;
		if (fechaIngreso == null) {
			if (other.fechaIngreso != null)
				return false;
		} else if (!fechaIngreso.equals(other.fechaIngreso))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", usuario=" + usuario + ", fechaIngreso=" + fechaIngreso + ", fechaEgreso="
				+ fechaEgreso + "]";
	}

	
	
	
	
	
}*/