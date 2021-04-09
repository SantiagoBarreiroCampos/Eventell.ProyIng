package model;

import java.util.Vector;

public class ListaArtistasFavoritos
{
	private String user_;
	private Vector<Artista> artistas_;
	
	public ListaArtistasFavoritos(String user, Vector<Artista> artistas)
	{
		this.user_ = user;
		this.artistas_ = artistas;
	}
	
	public String getUser()
	{
		return user_;
	}
	public Vector<Artista> getArtistas()
	{
		return artistas_;
	}
}