package model;

public class Usuario
{
	private String nombre_;
	private String apellidos_;
	private String nacimiento_;
	private String correo_;
	private String ciudad_;
	private String sexo_;
	private String user_;
	private String password_;
	
	public Usuario (String nombre, String apellidos, String nacimiento, String correo, String ciudad, String sexo, String user, String password)
	{
		this.nombre_ = nombre;
		this.apellidos_ = apellidos;
		this.nacimiento_ = nacimiento;
		this.correo_ = correo;
		this.ciudad_ = ciudad;
		this.sexo_ = sexo;
		this.user_ = user;
		this.password_ = password;
	}
	
	public String getNombre()
	{
		return nombre_;
	}
	public String getApellidos()
	{
		return apellidos_;
	}
	public String getNacimiento()
	{
		return nacimiento_;
	}
	public String getCorreo()
	{
		return correo_;
	}
	public String getCiudad()
	{
		return ciudad_;
	}
	public String getSexo()
	{
		return sexo_;
	}
	public String getUser()
	{
		return user_;
	}
	public String getPassword()
	{
		return password_;
	}
}
