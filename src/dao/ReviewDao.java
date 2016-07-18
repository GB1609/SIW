package dao;
import tables.Review;
public interface ReviewDao
{
	public void delete(Review r);
	public void save(Review r);
	public void update(Review r);
}
