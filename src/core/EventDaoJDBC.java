package core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	public int getCode(String name) {
		Connection connection = this.dataSource.getConnection();
		int code = -1;
		try {
			String query = "SELECT event.eventcode FROM event,information WHERE information.informationid = event.information AND information.name=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				code = result.getInt(1);
			
			
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
		return code;
	}

	@Override
	public String getFeedback(int eventCode) {
		String feedback = " ";
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select feedback from event WHERE event.eventcode = ?";
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
	public Information getInfoByName(String value) {
		Information information = null;
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select information.informationid, information.date , information.place, information.description, information.city, information.name, information.img from information WHERE information.name= ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, value);
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
	public String getName(int eventCode) {
		String name = "";
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select information.name from event, information WHERE event.eventcode = ? AND information.informationid = information";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setInt(1, eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			if (result.next())
				name = result.getString("name");
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
		return name;
	}

	//TODO DA RIFARE
	@Override
	public List<Events> returnAllEvents() {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {

			String returnAll = "SELECT event.eventcode,event.feedback, event.organizator, event.category,"
					+ "event.information," + " FROM event, information "
					+ "WHERE event.information = information.informationid " + "ORDER BY information.date DESC";
			PreparedStatement statement = connection.prepareStatement(returnAll);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
			set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
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

	@Override
	public void save(Events ev) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into event(feedback, organizator, category, information) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, ev.getFeedback());
			statement.setString(2, ev.getOrganizator());
			statement.setInt(3, ev.getCategory());
			statement.setInt(4, ev.getInformation());
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
	public List<Events> searchByCategory(String category) {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select event.eventcode,event.feedback, event.organizator, event.category,event.information FROM event,category WHERE event.category = category.categorycode AND category.name=?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, category);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
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
		System.out.println(set.size());
		return set;
	}

	@Override
	public List<Events> searchByDate(LocalDate l) {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select * from event, information WHERE event.information = information.informationid AND information.date = ? ORDER BY information.date DESC";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setDate(1, Date.valueOf(l));
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
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
	public List<Events> searchByName(String value) {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {

			String returnAll = "SELECT event.eventcode,event.feedback, event.organizator, event.category,"
					+ "event.information" + " FROM event, information "
					+ "WHERE event.information = information.informationid AND information.name=? "
					+ "ORDER BY information.date DESC";
			PreparedStatement statement = connection.prepareStatement(returnAll);
			statement.setString(1, value);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
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

	@Override
	public List<Events> searchByOrganizator(String organizator) {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "SELECT * FROM event WHERE event.organizator = ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, organizator);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
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

	
	//TODO DA RIFARE
	@Override
	public List<Events> searchByPartecipants(String partecipant) {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "SELECT event.eventcode,event.feedback, event.organizator, event.category,event.information, event.partecipant "
					+ " FROM event, information, partecipant"
					+ " WHERE partecipant.name = ? AND event.partecipant = partecipant.partecipantid AND event.information=information.informationid ORDER BY information.date DESC ";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, partecipant);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
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
	public List<Events> searchByPlace(String city) {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "SELECT event.eventcode,event.feedback, event.organizator, event.category,event.information FROM event, information WHERE event.information = information.informationid AND information.city = ? ORDER BY information.date DESC";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, city);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
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
	public List<Events> searchByPrice(double price, boolean max) {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search;
			if (max)
				search = "SELECT * FROM event WHERE event.eventcode IN (SELECT event.eventcode"
						+ " FROM event, ticket, information"
						+ " WHERE ticket.event = event.eventcode AND price < ? AND event.information=information.informationid ORDER BY information.date DESC)";
			else
				search = "SELECT * FROM event WHERE event.eventcode IN (SELECT event.eventcode"
						+ " FROM event, ticket, information "
						+ " WHERE ticket.event = event.eventcode AND price > ? AND event.information=information.informationid ORDER BY information.date DESC)";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setDouble(1, price);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
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
			String update = "update event SET feedback = ?, organizator = ?, category = ?, information = ? WHERE eventcode =?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, ev.getFeedback());
			statement.setString(2, ev.getOrganizator());
			statement.setInt(3, ev.getCategory());
			statement.setInt(4, ev.getInformation());
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
	public List<Events> organizedEvents(String user) {
		List<Events> result = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "SELECT event.eventcode,event.feedback, event.organizator, event.category,event.information FROM event WHERE event.organizator=?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, user);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet results = statement.executeQuery();
			while (results.next())
				result.add(new Events(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4), results.getInt(5)));
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
		return result;
	
}
	
}
