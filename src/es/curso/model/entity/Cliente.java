package es.curso.model.entity;

public class Cliente {
	private int id;
	private String nombres;
	private String apellidos;
	private String dni;
	
	public Cliente() {
		super();
	}
	public Cliente(int id, String name, String apellido, String dni) {
		super();
		this.id = id;
		this.nombres = name;
		this.apellidos = apellido;
		this.dni=dni;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String name) {
		this.nombres = name;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellido) {
		this.apellidos = apellido;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombres=" + nombres + ", apellido="
				+ apellidos + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombres == null) ? 0 : nombres.hashCode());
		return result;
	}
	@Override
	//sirve para comparar objetos (por sus atributos), no sólo sus posiciones de memoria
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (id != other.id)
			return false;
		if (nombres == null) {
			if (other.nombres != null)
				return false;
		} else if (!nombres.equals(other.nombres))
			return false;
		return true;
	}
	
	
}
