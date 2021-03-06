package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import dao.OrdersDao;
import tables.Order;

public class OrdersDaoJDBC implements OrdersDao {
	private DataSource dataSource;

	public OrdersDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(int key) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM orders WHERE ordercode=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, key);
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
			String delete = "delete FROM orders";
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
	public void save(Order o) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into orders (ticket,users) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, o.getTicket());
			statement.setString(2, o.getUser());
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

	@Override
	public Set<Order> searchByClients(String username) {
		Set<Order> myResult = new HashSet<Order>();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM orders WHERE orders.users=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Order o = new Order(result.getInt("ordercode"), result.getInt("ticket"), result.getString("users"));
				myResult.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return myResult;
	}

	@Override
	public void update(Order o) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update orders SET ordercode=?, ticket= ?, users=? WHERE ordercode=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, o.getOrderCode());
			statement.setInt(2, o.getTicket());
			statement.setString(3, o.getUser());
			statement.setInt(4, o.getOrderCode());
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
