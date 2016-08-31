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
	public void deleteAll() {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM ticket";
			PreparedStatement statement = connection.prepareStatement(delete);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
			delete = "delete FROM eventpartecipant";
			statement = connection.prepareStatement(delete);
			statement.executeUpdate();
			connection.commit();
			delete = "delete FROM event";
			statement = connection.prepareStatement(delete);
			statement.executeUpdate();
			connection.commit();
			delete = "delete FROM information";
			statement = connection.prepareStatement(delete);
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
	public void deleteAllForOne(int eventCode, String name) {
		Connection connection = this.dataSource.getConnection();
		try {
			
			String delete = "delete FROM orders WHERE orders.ticket IN (SELECT ticket.ticketcode FROM ticket WHERE event=?)";
			PreparedStatement statementdd = connection.prepareStatement(delete);
			statementdd.setInt(1, eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statementdd.executeUpdate();
			connection.commit();
			delete = "delete FROM wishtickets WHERE ticketcode IN (SELECT ticketcode FROM ticket WHERE event=?)";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
			delete = "delete FROM ticket WHERE event=?";
			PreparedStatement statementX = connection.prepareStatement(delete);
			statementX.setInt(1, eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statementX.executeUpdate();
			connection.commit();
			delete = "delete FROM eventpartecipant WHERE event=?";
			PreparedStatement statement2 = connection.prepareStatement(delete);
			statement2.setInt(1, eventCode);
			statement2.executeUpdate();
			connection.commit();
			
			delete = "delete FROM review WHERE event=?";
			PreparedStatement statement12 = connection.prepareStatement(delete);
			statement12.setInt(1, eventCode);
			statement12.executeUpdate();
			connection.commit();
			
			
			delete = "delete FROM event WHERE eventcode=?";
			PreparedStatement statement3 = connection.prepareStatement(delete);
			statement3.setInt(1, eventCode);
			statement3.executeUpdate();
			connection.commit();
			delete = "delete FROM information WHERE name=? ";
			PreparedStatement statement4 = connection.prepareStatement(delete);
			statement4.setString(1, name);
			statement4.executeUpdate();
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
	public boolean getEventState(String name) {
		boolean res = true;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT startstop FROM event,information WHERE event.information=information.informationid AND information.name=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				res = rs.getBoolean(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
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
	public String getImg(int eventCode) {
		String img = "";
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select information.img from event, information WHERE event.eventcode = ? AND information.informationid = information";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setInt(1, eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			if (result.next())
				img = result.getString("img");
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
		return img;
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

	@Override
	public int getRemainTicket(int eventCode) {
		Connection connection = this.dataSource.getConnection();
		int ticket = -1;
		try {
			String query = "SELECT event.remainticket FROM event WHERE eventcode=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				ticket = result.getInt("remainticket");
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
		return ticket;
	}

	@Override
	public void insertPartecipant(int partecipant, int eventCode) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into eventpartecipant(event, partecipant) values (?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, eventCode);
			statement.setInt(2, partecipant);
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
			String search = "SELECT event.eventcode,event.feedback, event.organizator, event.category,event.information, event.totticket, event.remainticket FROM event WHERE event.organizator=?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, user);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet results = statement.executeQuery();
			while (results.next())
				result.add(new Events(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4),
						results.getInt(5), results.getInt(6), results.getInt(7)));
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

	@Override
	public List<Events> returnAllEvents() {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {

			String returnAll = "SELECT event.eventcode,event.feedback, event.organizator, event.category,"
					+ "event.information " + " FROM event, information "
					+ "WHERE event.information = information.informationid " + "ORDER BY information.date DESC";
			PreparedStatement statement = connection.prepareStatement(returnAll);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5)));
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
			String insert = "insert into event(feedback, organizator, category, information, totticket, remainticket, startstop) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, ev.getFeedback());
			statement.setString(2, ev.getOrganizator());
			statement.setInt(3, ev.getCategory());
			statement.setInt(4, ev.getInformation());
			statement.setInt(5, ev.getNumBigl());
			statement.setInt(6, ev.getRemBigl());
			statement.setBoolean(7, ev.isStartstorpsell());
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
			String search = "select event.eventcode,event.feedback, event.organizator, event.category,event.information FROM event,subcategory,category WHERE event.category = subcategory.subcategorycode AND category.name=? AND subcategory.father=category.categorycode";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, category);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5)));
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
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5)));
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
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5)));
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
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5), result.getInt(6), result.getInt(7)));
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
	public List<Events> searchByPartecipants(String partecipant) {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "SELECT event.eventcode,event.feedback, event.organizator, event.category,event.information "
					+ " FROM event, information, partecipant, eventpartecipant"
					+ " WHERE partecipant.name = ? AND event.eventcode = eventpartecipant.event AND partecipant.partecipantid=eventpartecipant.partecipant AND event.information=information.informationid ORDER BY information.date DESC ";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, partecipant);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5)));
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
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5)));
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
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5)));
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
	public List<Events> searchBySubCategory(String category) {
		List<Events> set = new ArrayList<Events>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select event.eventcode,event.feedback, event.organizator, event.category,event.information FROM event,subcategory WHERE event.category = subcategory.subcategorycode AND subcategory.name=?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, category);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4),
						result.getInt(5)));
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
	public void startSeller(int e) {
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "update event SET startstop = ? WHERE event.eventcode=? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setBoolean(1, true);
			statement.setInt(2, e);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void stopSeller(int e) {
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "update event SET startstop = ? WHERE event.eventcode=? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setBoolean(1, false);
			statement.setInt(2, e);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public void update(Events ev) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update event SET feedback = ?, organizator = ?, category = ?, information = ?, startstop = ? WHERE eventcode =?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, ev.getFeedback());
			statement.setString(2, ev.getOrganizator());
			statement.setInt(3, ev.getCategory());
			statement.setInt(4, ev.getInformation());
			statement.setInt(6, ev.getEventcode());
			statement.setBoolean(7, ev.isStartstorpsell());
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
	public void updateTicketsNumber(int eventCode, int ticketQuantity) {
		int ticketNumber;
		ticketNumber = this.getRemainTicket(eventCode);
		Connection connection = this.dataSource.getConnection();
		try {
			ticketNumber -= ticketQuantity;
			String update = "update event SET remainticket = ? WHERE eventcode =?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, ticketNumber);
			statement.setInt(2, eventCode);
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
}
