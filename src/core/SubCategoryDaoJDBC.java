package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.SubCategoryDao;
import tables.SubCategory;

public class SubCategoryDaoJDBC implements SubCategoryDao {

	private DataSource dataSource;

	public SubCategoryDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(String name) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM subcategory WHERE subcategory.name=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, name);
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

	@Override
	public void deleteAll() {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM subcategory";
			PreparedStatement statement = connection.prepareStatement(delete);
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

	@Override
	public List<String> getSubCategories() {
		Connection connection = this.dataSource.getConnection();
		List<String> cat = new ArrayList<String>();
		try {
			String returnAll = "SELECT subcategory.name FROM subcategory ORDER BY subcategory.name ";
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
			String returnAll = "SELECT subcategory.subcategorycode FROM subcategory WHERE subcategory.name=? GROUP BY subcategory.subcategorycode  ";
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
	public void save(SubCategory c) {

		Connection connection = this.dataSource.getConnection();
		try {
			String name = c.getName();
			int father = c.getFather();
			String insert = "insert into subcategory (name, father) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, name);
			statement.setInt(2, father);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
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
