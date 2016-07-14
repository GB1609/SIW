package dao;

import java.util.Set;

import tables.Review;

public interface ReviewDao {
	public void delete(Review r);

	public void save(Review r);

	public Set<Review> searchByEvents(int eventCode);

	public void update(Review r);
}
