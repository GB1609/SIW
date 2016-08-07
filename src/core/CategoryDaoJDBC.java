package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDao;
import tables.Category;

public class CategoryDaoJDBC implements CategoryDao {
	private DataSource dataSource;

	public CategoryDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(int categoryCode) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM category WHERE categorycode = ?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, categoryCode);
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

	@Override
	public void deleteAll() {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM category";
			PreparedStatement statement = connection.prepareStatement(delete);
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

	@Override
	public List<String> getCategories() {
		Connection connection = this.dataSource.getConnection();
		List<String> cat = new ArrayList<String>();
		try {
			String returnAll = "SELECT category.name FROM category ";
			PreparedStatement statement = connection.prepareStatement(returnAll);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				if (!cat.contains(result.getString(1)))
					cat.add(result.getString(1));
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
		return cat;
	}

	@Override
	public int returnCode(String name) {
		Connection connection = this.dataSource.getConnection();

		int value = -1;

		try {
			String returnAll = "SELECT category.categorycode FROM category WHERE category.name=? GROUP BY category.categorycode  ";
			PreparedStatement statement = connection.prepareStatement(returnAll);
			statement.setString(1, name);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				value = result.getInt(1);
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
		return value;

	}

	@Override
	public void save(Category c) {
		Connection connection = this.dataSource.getConnection();
		try {
			String nome = c.getName();
			String insert = "insert into category (name) values(?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, nome);
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
}
