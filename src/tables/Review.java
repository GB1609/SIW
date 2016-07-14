package tables;

public class Review {
	int reviewCode;
	int vote;
	String user;
	int event;
	String description;

	public Review(int reviewCode, int vote, String user, int event, String description) {
		this.reviewCode = reviewCode;
		this.vote = vote;
		this.user = user;
		this.event = event;
		this.description = description;
	}

	public Review(int vote, String user, int event, String description) {
		this.vote = vote;
		this.user = user;
		this.event = event;
		this.description = description;
	}

	public String getDescription() {
		return this.description;
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

	public void setDescription(String description) {
		this.description = description;
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