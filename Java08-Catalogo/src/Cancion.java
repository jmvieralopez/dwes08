
public class Cancion {
	private int id;
	private int idArtista;
	private String cancion;
	private String artista;
	private String album;
	private String caratula;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdArtista() {
		return idArtista;
	}
	public void setIdArtista(int idArtista) {
		this.idArtista = idArtista;
	}
	public String getCancion() {
		return cancion;
	}
	public void setCancion(String cancion) {
		this.cancion = cancion;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getCaratula() {
		return caratula;
	}
	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}
	public Cancion(int id, int idArtista, String cancion, String artista, String album, String caratula) {
		super();
		this.id = id;
		this.idArtista = idArtista;
		this.cancion = cancion;
		this.artista = artista;
		this.album = album;
		this.caratula = caratula;
	}

}
