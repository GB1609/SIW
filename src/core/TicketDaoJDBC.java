package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
		Connection connection = this.dataSource.getConnection();
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
	public void deleteAll() {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM ticket";
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
	public boolean getState(int code) {
		boolean state = false;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT sell FROM ticket WHERE ticketcode = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, code);
			ResultSet result = statement.executeQuery();
			if (result.next())
				state = result.getBoolean("sell");
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return state;
	}

	@Override
	public Ticket getTicket(int ticketcode) {
		Ticket ticket = new Ticket();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM ticket WHERE ticketcode = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, ticketcode);
			ResultSet result = statement.executeQuery();
			if (result.next())
				ticket = new Ticket(result.getInt("ticketcode"), result.getInt("event"), result.getDouble("price"),
						result.getString("type"), result.getBoolean("sell"));
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ticket;
	}

	@Override
	public List<String> getTipology() {
		Connection connection = this.dataSource.getConnection();
		List<String> typ = new ArrayList<String>();
		try {
			String query = "SELECT ticket.type FROM ticket ORDER BY ticket.type";
			PreparedStatement statement = connection.prepareStatement(query);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				if (!typ.contains(result.getString(1)))
					typ.add(result.getString(1));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return typ;

	}

	@Override
	public void save(Ticket bd) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into ticket(price,type,event,sell) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setDouble(1, bd.getPrice());
			statement.setString(2, bd.getType());
			statement.setInt(3, bd.getCodeEvent());
			statement.setBoolean(4, bd.isSeller());
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
	public void save(Ticket bd, int ticketNumber) {
		Connection connection = this.dataSource.getConnection();
		try {
			for (int i = 0; i < ticketNumber; i++) {
				String insert = "insert into ticket(price,type,event,sell) values (?,?,?,?)";
				PreparedStatement statement = connection.prepareStatement(insert);
				statement.setDouble(1, bd.getPrice());
				statement.setString(2, bd.getType());
				statement.setInt(3, bd.getCodeEvent());
				statement.setBoolean(4, bd.isSeller());
				connection.setAutoCommit(false);
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				statement.executeUpdate();
				connection.commit();
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
	}

	@Override
	public List<String> searchByEvents(int e) {
		List<String> myResult = new ArrayList<>();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT price, type FROM ticket WHERE event=? AND sell = true GROUP BY price, type";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, e);
			ResultSet result = statement.executeQuery();
			while (result.next())
				myResult.add(result.getString("type") + "_" + result.getString("price"));
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
	public Set<Ticket> searchByLocality(Place l) {
		Set<Ticket> myResult = new HashSet<Ticket>();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM ticket WHERE event IN (SELECT eventcode FROM event,information WHERE event.information = information.informationid AND information.place=? AND information.city=?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, l.getCity());
			statement.setString(2, l.getName());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket(result.getInt("ticketcode"), result.getInt("event"), result.getDouble("price"),
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
		Connection connection = this.dataSource.getConnection();
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
		Connection connection = this.dataSource.getConnection();
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
	public Ticket searchTicket(String type, int event, double price) {
		Ticket ticket = new Ticket();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM ticket WHERE type = ? AND event = ? AND price = ? AND sell = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, type);
			statement.setInt(2, event);
			statement.setDouble(3, price);
			statement.setBoolean(4, true);
			ResultSet result = statement.executeQuery();
			if (result.next())
				ticket = new Ticket(result.getInt("ticketcode"), result.getInt("event"), result.getDouble("price"),
						result.getString("type"), result.getBoolean("sell"));
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ticket;
	}

	@Override
	public void setState(boolean sell, int code) {
		Connection connection = this.dataSource.getConnection();
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
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update ticket SET price =?, type=?, event=?, ticketcode=? , sell=? WHERE ticketcode=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setDouble(1, bd.getPrice());
			statement.setString(2, bd.getType());
			statement.setInt(3, bd.getCodeEvent());
			statement.setInt(4, bd.getTicketCode());
			statement.setBoolean(5, bd.isSeller());
			statement.setInt(6, bd.getTicketCode());
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
