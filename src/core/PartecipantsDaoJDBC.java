package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PartecipantsDao;
import tables.Partecipants;

public class PartecipantsDaoJDBC implements PartecipantsDao {
	private DataSource dataSource;

	public PartecipantsDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(int partecipantId) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM partecipant WHERE partecipantid = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, partecipantId);
			statement.executeUpdate();
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
	public void deleteAll() {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM partecipant";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.executeUpdate();
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
	public List<Partecipants> getAllPartecipants() {
		Connection connection = this.dataSource.getConnection();
		List<Partecipants> partecipants = new ArrayList<Partecipants>();
		try {
			String returnAll = "SELECT * FROM partecipant ORDER BY partecipant.name";
			PreparedStatement statement = connection.prepareStatement(returnAll);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				partecipants.add(new Partecipants(result.getString(2), result.getString(3), result.getString(4),
						result.getInt(1)));
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
		return partecipants;
	}

	@Override
	public int getPartecipantCode(String name) {
		Connection connection = this.dataSource.getConnection();

		int value = -1;

		try {
			String returnAll = "SELECT partecipant.partecipantid FROM partecipant WHERE partecipant.name=? GROUP BY partecipant.partecipantid  ";
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
	public void save(Partecipants i) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into partecipant(name, type, city) values (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, i.getName());
			statement.setString(2, i.getType());
			statement.setString(3, i.getCity());
			statement.executeUpdate();
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
	public void update(Partecipants i) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update partecipant SET name = ?, type = ?, city = ?  WHERE partecipantid = ?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, i.getName());
			statement.setString(2, i.getType());
			statement.setString(3, i.getCity());
			statement.setInt(4, i.getIdPartecipant());
			statement.executeUpdate();
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