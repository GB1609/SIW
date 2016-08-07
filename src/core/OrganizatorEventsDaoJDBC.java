package core;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dao.OrganizatorEventsDao;
import tables.OrganizatorEvents;

public class OrganizatorEventsDaoJDBC implements OrganizatorEventsDao {
	private DataSource dataSource;

	public OrganizatorEventsDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(OrganizatorEvents oe) {
		Connection connection = this.dataSource.getConnection();
		int id = oe.getCodeEvent();
		String un = oe.getOrganizatorCode();
		try {
			String delete = "delete FROM organizatorevent WHERE eventcode=? AND organizatorusername=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, id);
			statement.setString(2, un);
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
			String delete = "delete FROM organizatorevent";
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
	public void save(OrganizatorEvents oe) {
		Connection connection = this.dataSource.getConnection();
		int eventCode = oe.getCodeEvent();
		String organizatorCode = oe.getOrganizatorCode();
		try {
			String insert = "insert into organizatorevent(eventcode, organizatorusername) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, eventCode);
			statement.setString(2, organizatorCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
