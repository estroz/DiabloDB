
public class Poster {

	public String userName;
	public int reputation;
	public int numberOfPages;
	public Integer adminID;

	public Poster(String userName, int reputation, int numberOfPages, Integer adminID) {
		this.userName = userName;
		this.reputation = reputation;
		this.numberOfPages = numberOfPages;
		if (adminID == null) {
			this.adminID = null;
		} else {
			this.adminID = adminID;
		}
	}
	
	public String toString() {
		return "Poster Object, name: " + userName + " rep: " + Integer.toString(reputation);
	}
}
