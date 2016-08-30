package core;

import dao.ReviewDao;
import tables.Review;

public class Main {
	public static void main(String[] args) {
		System.out.println("main start");
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		ReviewDao rd = dao.getReviewDao();
		rd.save(new Review(5, "vic07", 64, "bello"));
		rd.save(new Review(5, "gio", 64, "bellissimo"));
	}
}