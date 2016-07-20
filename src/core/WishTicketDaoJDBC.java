package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import dao.WishTicketDao;
import tables.Clients;
import tables.Ticket;
import tables.WishTicket;

public class WishTicketDaoJDBC implements WishTicketDao {
	private DataSource dataSource;

	public WishTicketDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean alreadyExist(WishTicket wt) {
		boolean exist = false;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select * from desiredtickets where listcode=? AND ticketcode=?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, wt.getListCode());
			statement.setInt(2, wt.getTicketCode());
			ResultSet result = statement.executeQuery();
			if (result.next())
				exist=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exist;
	}

	@Override
	public void delete(WishTicket bd) {
		Connection connection = this.dataSource.getConnection();
		int listCode = bd.getListCode();
		int ticketCode = bd.getTicketCode();
		try {
			String delete = "delete FROM desiredtickets WHERE listcode=? AND ticketcode=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, listCode);
			statement.setInt(2, ticketCode);
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
	public String getListOwner(WishTicket bd) {
		String owner = null;
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select owner from wishlist where wishlist.listcode = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, bd.getListCode());
			ResultSet result = statement.executeQuery();
			if (result.next())
				owner = result.getString("owner");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return owner;
	}

	@Override
	public int save(WishTicket bd, String owner) {
		Connection connection = this.dataSource.getConnection();
		int listCode = bd.getListCode();
		int ticketCode = bd.getTicketCode();
		if (getListOwner(bd).equals(owner))
			if(!this.alreadyExist(bd))
				try {
					String insert = "insert into desiredtickets(listcode,ticketcode) values (?,?)";
					PreparedStatement statement = connection.prepareStatement(insert);
					statement.setInt(1, listCode);
					statement.setInt(2, ticketCode);
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
			else
				return 1;
		else
			return -1;
		return 0;
	}

	@Override
	public Set<Clients> searchInterested(Ticket b) {
		Set<Clients> myResult = new HashSet<Clients>();
		int cod = b.getTicketCode();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT client.username, client.password, client.lastname, "
					+ "client.firstname, client.birthdate, client.address, client.credit "
					+ "FROM client,desiredtickets,wishlist " + "WHERE desiredtickets.ticketcode=? AND "
					+ "wishlist.owner=client.username AND " + "desiredtickets.listcode=wishlist.listcode ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, cod);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Clients c = new Clients(result.getString("username"), result.getString("password"),
						result.getString("lastname"), result.getString("firstname"),
						result.getDate("birthdate").toLocalDate(), result.getString("address"),
						(float) result.getDouble("credit"));
				myResult.add(c);
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
	public Set<Ticket> searchOwners(Clients c) {
		Set<Ticket> myResult = new HashSet<Ticket>();
		String cod = c.getUsername();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT ticket.price, ticket.type, ticket.event, ticket.ticketcode "
					+ "FROM ticket,desiredtickets,wishlist " + "WHERE ticket.ticketcode=desiredtickets.ticketcode AND "
					+ "wishlist.owner=? AND " + "desiredtickets.listcode=wishlist.listcode ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, cod);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Ticket t = new Ticket();
				t.setCodeTicket(result.getInt("ticketcode"));
				t.setCodeEvent(result.getInt("event"));
				t.setPrice(result.getDouble("price"));
				t.setType(result.getString("type"));
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
}
