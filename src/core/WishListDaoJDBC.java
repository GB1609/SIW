package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.WishListDao;
import tables.WishList;

public class WishListDaoJDBC implements WishListDao {

	private DataSource dataSource;

	public WishListDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(WishList l) {
		Connection connection = dataSource.getConnection();
		try {
			String codiceProprietario = l.getProprietario();
			String insert = "insert into wishlist (owner) values (?) ";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, codiceProprietario);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(WishList l) {
		int listcode = l.getCodiceLista();
		String owner = l.getProprietario();
		Connection connection = dataSource.getConnection();
		try {
			String update = "update wishlist SET listcode=? , owner=?  WHERE listcode=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, listcode);
			statement.setString(2, owner);
			statement.setInt(3, listcode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void delete(WishList l) {
		int id = l.getCodiceLista();
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM wishlist WHERE listcode=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, id);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
