package core;

import dao.UserDao;

public class UserDaoJDBC implements UserDao {
	public UserDaoJDBC(DataSource dataSource) {
	}
	// @Override
	// public void create(User i) {
	// Connection connection = this.dataSource.getConnection();
	// try {
	// String insert = "insert into organizator (username, password, lastname,
	// firstname, birthdate, address) values (?,?,?,?,?,?)";
	// PreparedStatement statement = connection.prepareStatement(insert);
	// statement.setString(1, i.getUsername());
	// statement.setString(2, i.getPassword());
	// statement.setString(3, i.getLastName());
	// statement.setString(4, i.getFirstName());
	// // LocalDate secs = LocalDate.of(2010, Month.APRIL, 23);
	// statement.setDate(5, java.sql.Date.valueOf(i.getBirthDate()));
	// statement.setString(6, i.getAddress());
	// connection.setAutoCommit(false);
	// connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	// statement.executeUpdate();
	// connection.commit();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// connection.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// @Override
	// public void delete(User i) {
	// Connection connection = this.dataSource.getConnection();
	// try {
	// String delete = "delete FROM user WHERE username = ? ";
	// PreparedStatement statement = connection.prepareStatement(delete);
	// statement.setString(1, i.getUsername());
	// connection.setAutoCommit(false);
	// connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	// statement.executeUpdate();
	// connection.commit();
	// } catch (SQLException e) {
	// } finally {
	// try {
	// connection.close();
	// } catch (SQLException e) {
	// }
	// }
	// }
	//
	// @Override
	// public void update(User i) {
	// Connection connection = this.dataSource.getConnection();
	// try {
	// String update = "update user SET password = ?, lastname = ?, firstname =
	// ?, birthdate = ?, address = ?, WHERE username=?";
	// PreparedStatement statement = connection.prepareStatement(update);
	// statement.setString(1, i.getPassword());
	// statement.setString(2, i.getLastName());
	// statement.setString(3, i.getFirstName());
	// LocalDate secs = LocalDate.of(2010, Month.APRIL, 23);
	// statement.setDate(4, java.sql.Date.valueOf(secs));
	// statement.setString(5, i.getAddress());
	// statement.setString(6, i.getUsername());
	// connection.setAutoCommit(false);
	// connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
	// statement.executeUpdate();
	// connection.commit();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// connection.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// }
}
