package DAO.flightshare.dao;

public class Reponse {
	public Reponse(long id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	
	protected long id;
	protected String message;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Reponse [id=" + id + ", message=" + message + "]";
	}
	

}
