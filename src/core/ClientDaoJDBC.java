package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.ClientsDao;
import dao.TicketDao;
import tables.Clients;
import tables.Ticket;

public class ClientDaoJDBC implements ClientsDao {
	private DataSource dataSource;

	public ClientDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void delete(String username) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM client WHERE username = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, username);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
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
			String delete = "delete FROM client";
			PreparedStatement statement = connection.prepareStatement(delete);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean existUser(String userToVerify) {
		boolean exsist = false;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select * FROM client	where username=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userToVerify);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.commit();
			ResultSet result = statement.executeQuery();
			if (result.next())
				exsist = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exsist;
	}

	@Override
	public void save(Clients c) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into client (username, password, lastname, firstname, birthdate, address, credit) values (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, c.getUsername());
			statement.setString(2, c.getPassword());
			statement.setString(3, c.getLastName());
			statement.setString(4, c.getFirstName());
			statement.setDate(5, java.sql.Date.valueOf(c.getBirthDate()));
			statement.setString(6, c.getAddress());
			statement.setFloat(7, c.getCredit());
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
	public void sellTicket(int ticket, int priceSelect) {
		Connection connection = this.dataSource.getConnection();
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		TicketDao t = dao.getTicketDao();
		t.setState(true, ticket);
		String update = "update ticket SET price=? WHERE ticketcode=?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(update);
			statement.setInt(1, priceSelect);
			statement.setInt(2, ticket);
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
	public Set<Ticket> ticketBuyed(String userName) {
		List<Integer> ticket = new ArrayList<Integer>();
		Set<Ticket> ticketDetail = new HashSet<>();
		Connection connection = this.dataSource.getConnection();
		try {
			String search = "select ticket from orders WHERE users= ?";
			PreparedStatement statement = connection.prepareStatement(search);
			statement.setString(1, userName);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
				ticket.add(result.getInt(1));
			for (int i = 0; i < ticket.size(); i++) {
				String searchAnother = "select * from ticket WHERE ticketcode=?";
				PreparedStatement statement2 = connection.prepareStatement(searchAnother);
				statement2.setInt(1, ticket.get(i));
				connection.setAutoCommit(false);
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
				result = statement.executeQuery();
				ticketDetail.add(new Ticket(result.getInt(1), result.getInt(2), result.getDouble(3),
						result.getString(4), result.getBoolean(5)));
			}
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
		return ticketDetail;
	}

	@Override
	public void update(Clients c) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update client SET password = ?, lastname = ?, firstname = ?, birthdate = ?, address = ?,credit=?   WHERE username=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, c.getPassword());
			statement.setString(2, c.getLastName());
			statement.setString(3, c.getFirstName());
			statement.setDate(4, java.sql.Date.valueOf(c.getBirthDate()));
			statement.setString(5, c.getAddress());
			statement.setFloat(6, c.getCredit());
			statement.setString(7, c.getUsername());
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
			}
		}
	}

	@Override
	public boolean verifyClients(String userName, String password) {
		boolean exsist = false;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select * FROM client where username=? and password=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userName);
			statement.setString(2, password);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.commit();
			ResultSet result = statement.executeQuery();
			if (result.next())
				exsist = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exsist;
	}
}
