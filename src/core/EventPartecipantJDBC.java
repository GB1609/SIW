package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.EventPartecipantDao;
import tables.EventPartecipant;

public class EventPartecipantJDBC implements EventPartecipantDao {
	private DataSource dataSource;

	public EventPartecipantJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(EventPartecipant ep) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM eventpartecipant WHERE partecipant = ? and event=? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, ep.getPartecipant());
			statement.setLong(2, ep.getEvent());
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
			String delete = "delete FROM eventpartecipant";
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
	public void save(EventPartecipant p) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into eventpartecipant (partecipant,event) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setLong(1, p.getPartecipant());
			statement.setLong(2, p.getEvent());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
			System.out.println("information insert");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e1) {
				System.out.println("ciao1");
				e1.printStackTrace();
			}
		}
	}
}
