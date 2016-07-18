package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ReviewDao;
import tables.Review;

public class ReviewDaoJDBC implements ReviewDao {
	private DataSource dataSource;

	public ReviewDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(Review r) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM review WHERE reviewcode = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, r.getReviewCode());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void save(Review r) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into review(vote, users, event, description) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, r.getVote());
			statement.setString(2, r.getUser());
			statement.setInt(3, r.getEvent());
			statement.setString(4, r.getDescription());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<String> searchByEvents(int eventCode) {
		List<String> reviews = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select users, description from review WHERE review.event = ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setInt(1, eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next()){
				reviews.add(result.getString("users"));
				reviews.add(result.getString("description"));
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reviews;
	}

	@Override
	public void update(Review r) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update review SET vote = ?, users = ?, event= ?, description = ? WHERE reviewcode =?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, r.getVote());
			statement.setString(2, r.getUser());
			statement.setInt(3, r.getEvent());
			statement.setInt(4, r.getReviewCode());
			statement.setString(5, r.getDescription());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
