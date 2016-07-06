package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Set;

import dao.OrdersDao;
import tables.Order;

public class OrdersDaoJDBC implements OrdersDao {

	private DataSource dataSource;

	public OrdersDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(Order o) {
		Connection connection = dataSource.getConnection();
		try {
			String insert = "insert into orders (ordercode,ticket,users) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1,o.getCodiceOrdine());
			statement.setInt(2, o.getBiglietto());
			statement.setString(3, o.getUtente());
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
	public void delete(int key) {
		Connection connection = dataSource.getConnection();
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
	public Set<Order> searchByClients(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
