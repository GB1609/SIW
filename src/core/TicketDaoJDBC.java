package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import dao.TicketDao;
import tables.Place;
import tables.Ticket;

public class TicketDaoJDBC implements TicketDao {
	private DataSource dataSource;

	public TicketDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(int code) {
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM ticket WHERE ticketcode=? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, code);
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
	public void save(Ticket bd) {
		Connection connection = this.dataSource.getConnection();
		double price = bd.getPrezzo();
		String type = bd.getTipo();
		int eventCode = bd.getCodiceEvento();
		try {
			String insert = "insert into ticket(price,type,event,sell) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setDouble(1, price);
			statement.setString(2, type);
			statement.setInt(3, eventCode);
			statement.setBoolean(4, bd.isVenduto());
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
	public Set<Ticket> searchByEvents(int e) {

		Set<Ticket> myResult = new HashSet<Ticket>();
		Connection connection = dataSource.getConnection();
		try {
			String query = "SELECT * FROM ticket WHERE event=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, e);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket(result.getInt("ticketcode"), result.getInt("event"), result.getDouble("price"),
						result.getString("type"), result.getBoolean("sell"));
				myResult.add(t);
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

	public Set<Ticket> searchByLocality(Place l) {
		Set<Ticket> myResult = new HashSet<Ticket>();
		Connection connection = dataSource.getConnection();
		try {
			String query = "SELECT * FROM ticket WHERE event IN (SELECT eventcode FROM event,information WHERE event.information = information.informationid AND information.place=? AND information.city=?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, l.getCitta());
			statement.setString(2, l.getNome());
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				Ticket t =  new Ticket(result.getInt("ticketcode"), result.getInt("event"), result.getDouble("price"),
						result.getString("type"), result.getBoolean("sell"));
				myResult.add(t);
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
	public Set<Ticket> searchByPrice(double price, boolean max) {
		Set<Ticket> myResult = new HashSet<Ticket>();
		Connection connection = dataSource.getConnection();
		try {
			String query;
			if (max)
				query = "SELECT * FROM ticket WHERE price<?";
			else
				query = "SELECT * FROM ticket WHERE price>?";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDouble(1, price);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket(result.getInt("ticketcode"), result.getInt("event"), result.getDouble("price"),
						result.getString("type"), result.getBoolean("sell"));
				myResult.add(t);
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
	public Set<Ticket> searchByPrice(double min, double max) {
		Set<Ticket> myResult = new HashSet<Ticket>();
		Connection connection = dataSource.getConnection();
		try {
			String query = "SELECT * FROM ticket WHERE price<? AND price>?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setDouble(1, max);
			statement.setDouble(2, min);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket(result.getInt("ticketcode"), result.getInt("event"), result.getDouble("price"),
						result.getString("type"), result.getBoolean("sell"));
				myResult.add(t);
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
	public void setState(boolean sell, int code) {

		Connection connection = dataSource.getConnection();
		try {
			String update = "update ticket SET ticketcode=? , sell=? WHERE ticketcode=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, code);
			statement.setBoolean(2, sell);
			statement.setInt(3, code);
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
	public void update(Ticket bd) {
		Connection connection = dataSource.getConnection();
		try {
			String update = "update ticket SET price =?, type=?, event=?, ticketcode=? , sell=? WHERE ticketcode=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setDouble(1, bd.getPrezzo());
			statement.setString(2, bd.getTipo());
			statement.setInt(3, bd.getCodiceEvento());
			statement.setInt(4, bd.getCodiceBiglietto());
			statement.setBoolean(5, bd.isVenduto());
			statement.setInt(6, bd.getCodiceBiglietto());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
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
}
