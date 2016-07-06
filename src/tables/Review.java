package tables;

public class Review {

	int reviewCode;
	int vote;
	String user;
	int event;

	public Review(int reviewCode, int vote, String user, int event) {
		this.reviewCode = reviewCode;
		this.vote = vote;
		this.user = user;
		this.event = event;
	}

	public int getEvent() {
		return this.event;
	}

	public int getReviewCode() {
		return this.reviewCode;
	}

	public String getUser() {
		return this.user;
	}

	public int getVote() {
		return this.vote;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}
}