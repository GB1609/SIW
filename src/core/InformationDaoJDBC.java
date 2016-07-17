package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import dao.InformationDao;
import tables.Information;

public class InformationDaoJDBC implements InformationDao {
	private DataSource dataSource;

	public InformationDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(Information i) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM information WHERE id = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, i.getInformationId());
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
	public void save(Information i) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into information (place,date,description,city, name, img) values (?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, i.getLocality());
			LocalDate dateOfExecution = i.getDate();
			statement.setDate(2, java.sql.Date.valueOf(dateOfExecution));
			statement.setString(3, i.getDescription());
			statement.setString(4, i.getCity());
			statement.setString(5, i.getName());
			statement.setString(6, i.getImg());
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

	@Override
	public void update(Information i) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update informatione SET informazioneid = ?, place = ?, date = ?, description=?, city=?, name = ?, img = ?  WHERE informationid=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setLong(1, i.getInformationId());
			statement.setString(2, i.getLocality());
			LocalDate dateOfExecution = i.getDate();
			statement.setDate(3, java.sql.Date.valueOf(dateOfExecution));
			statement.setString(4, i.getDescription());
			statement.setString(5, i.getCity());
			statement.setString(6, i.getName());
			statement.setString(7, i.getImg());
			statement.setLong(8, i.getInformationId());
			statement.executeUpdate();
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
	public Set<Information> getAllInfo(int code) {
		Set<Information> info = new HashSet<Information>();
		Connection connection = dataSource.getConnection();
		try {
			String req = "SELECT * FROM information WHERE informationid=?";
			PreparedStatement statement = connection.prepareStatement(req);
			statement.setInt(1,code );
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				info.add(new Information(result.getInt(1),(result.getDate(3)).toLocalDate(), result.getString(2), result.getString(4),result.getString(5), result.getString(6), result.getString(7)));
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
		return null;
	}
}
