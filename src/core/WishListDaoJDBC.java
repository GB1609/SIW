package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.WishListDao;
import tables.WishList;

public class WishListDaoJDBC implements WishListDao {
	private DataSource dataSource;

	public WishListDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(int listCode) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM wishlist WHERE listcode=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, listCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
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
	public void save(WishList l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into wishlist (owner, name) values (?,?) ";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, l.getOwner());
			statement.setString(2, l.getName());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
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
	public List<String> searchByOwner(String owner) {
		List<String> myResult = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT listcode, name FROM wishlist WHERE owner = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, owner);
			ResultSet result = statement.executeQuery();
			while (result.next()){
				myResult.add(result.getString("listcode"));
				myResult.add(result.getString("name"));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return myResult;
	}

	@Override
	public void update(WishList l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update wishlist SET listcode=? , owner=?, name=?  WHERE listcode=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, l.getListCode());
			statement.setString(2, l.getOwner());
			statement.setString(3, l.getName());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
