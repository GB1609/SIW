package dao;

import java.util.List;

import tables.Review;

public interface ReviewDao {
	public void delete(int reviewCode);

	public void deleteAll();

	public boolean save(Review r);

	public List<String> searchByEvents(int eventCode);

	public void update(Review r);
}
