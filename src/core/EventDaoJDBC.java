package core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import dao.EventsDao;
import tables.Events;
import tables.Information;

public class EventDaoJDBC implements EventsDao {
	private DataSource dataSource;

	public EventDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(int eventCode) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM event WHERE eventcode = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
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
	public String getFeedback(int eventCode) {
		String feedback = " ";
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select feedback from eventWHERE event.eventcode = ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setInt(1, eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				feedback = result.getString(1);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return feedback;
	}

	@Override
	public Information getInformation(int eventCode) {
		Information information = null;
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select information.informationid, information.date , information.place, information.description, information.city, information.name, information.img from event, information WHERE event.eventcode = ? AND information.informationid = information";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setInt(1, eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				information = new Information(result.getInt(1), result.getDate(2).toLocalDate(), result.getString(3),
						result.getString(4), result.getString(5), result.getString(6), result.getString(7));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return information;
	}

	@Override
	public void save(Events ev) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into event(feedback, organizator, category, information, partecipant) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, ev.getFeedback());
			statement.setString(2, ev.getOrganizator());
			statement.setInt(3, ev.getCategory());
			statement.setInt(4, ev.getInformation());
			statement.setInt(5, ev.getPartecipant());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
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
	public Set<Events> searchByCategory(int categoryCode) {
		Set<Events> set = new HashSet<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select * from event WHERE event.category = ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setInt(1, categoryCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5), result.getInt(6)));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return set;
	}

	@Override
	public Set<Events> searchByDate(LocalDate l) {
		Set<Events> set = new HashSet<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select * from event, information WHERE event.information = information.informationid AND information.date = ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setDate(1, Date.valueOf(l));
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5), result.getInt(6)));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return set;
	}

	@Override
	public Set<Events> searchByOrganizator(String organizator) {
		Set<Events> set = new HashSet<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select * from event WHERE event.organizator = ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, organizator);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5), result.getInt(6)));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return set;
	}

	@Override
	public Set<Events> searchByPartecipants(int idpartecipant) {
		Set<Events> set = new HashSet<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select * from event WHERE event.partecipant = ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setInt(1, idpartecipant);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5), result.getInt(6)));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return set;
	}

	@Override
	public Set<Events> searchByPlace(String name, String city) {
		Set<Events> set = new HashSet<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select * from event, information WHERE event.information = information.informationid AND information.place = ? AND information.city = ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, name);
			statement.setString(2, city);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5), result.getInt(6)));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return set;
	}

	@Override
	public Set<Events> searchByPrice(double price, boolean max) {
		Set<Events> set = new HashSet<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search;
			if (max)
				search = "SELECT * FROM event, ticket WHERE ticket.event = event.eventcode AND price < ?";
			else
				search = "SELECT * FROM event, ticket WHERE ticket.event = event.eventcode AND price > ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setDouble(1, price);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5), result.getInt(6)));
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return set;
	}

	@Override
	public void startSeller(Events e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void stopSeller(Events e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Events ev) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update event SET feedback = ?, organizator = ?, category = ?, information = ?, partecipant = ? WHERE eventcode =?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, ev.getFeedback());
			statement.setString(2, ev.getOrganizator());
			statement.setInt(3, ev.getCategory());
			statement.setInt(4, ev.getInformation());
			statement.setInt(5, ev.getPartecipant());
			statement.setInt(6, ev.getEventcode());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
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
	public Set<Events> returnAllEvents() {
		Set<Events> set = new HashSet<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			
			String returnAll = "SELECT * FROM event";
			PreparedStatement statement = connection.prepareStatement(returnAll);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5), result.getInt(6)));
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
		return set;
	}
}
