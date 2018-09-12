package game.repository.dao.impl;

import game.model.AccountEntity;
import game.model.UserEntity;
import game.repository.dao.AccountDao;
import game.repository.dao.UserDao;
import game.repository.helper.QueryHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author by ruslan.gramatic
 */
public class UserDaoImpl implements UserDao {

    public String getTokenByUserId(UserEntity user) {
        return new QueryHelper<String>() {
            protected void executeQuery(Statement statement, Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT token FROM User WHERE name = ? and password = ?;");
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getPassword());
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    returnResult(rs.getString("token"));
                }
            }
        }.run();
    }

    public void logoutUser(String token) {
        new QueryHelper() {
            protected void executeQuery(Statement statement, Connection connection) throws SQLException {

                PreparedStatement pstmt = connection.prepareStatement(
                        "UPDATE Account a " +
                                "JOIN User u ON u.id=a.user_id " +
                                "SET a.room_id = NULL " +
                                "WHERE u.token = ?; ");// TODO: Updates Room where user was
                pstmt.setString(1, token);
                pstmt.executeUpdate();
            }
        }.run();
    }

    public String createNewUser(UserEntity user) {

        return new QueryHelper<String>() {
            protected void executeQuery(Statement statement, Connection connection) throws SQLException {

                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT IGNORE INTO User (name, password, token) VALUES (?,?,?);");
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getToken());

                int status = pstmt.executeUpdate();
                if(status > 0) {
                    returnResult(user.getToken());
                pstmt = connection.prepareStatement("INSERT  INTO Account(user_id) SELECT id FROM User WHERE name = ? AND password = ?; ");
                pstmt.setString(1,user.getName());
                pstmt.setString(2,user.getPassword());
                pstmt.executeUpdate();
                }
            }



        }.run();


    }



    public UserEntity getUserByToken(String token) {
        return new QueryHelper<UserEntity>() {
            protected void executeQuery(Statement statement, Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT id, name, token FROM User WHERE token = ?;");
                pstmt.setString(1, token);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    returnResult(new UserEntity(
                            rs.getInt("id"),
                            rs.getString("name"),
                            null,
                            rs.getString("token")
                    ));
                }
            }
        }.run();
    }

    public UserEntity getUserById(Integer userId) {
        return new QueryHelper<UserEntity>() {
            protected void executeQuery(Statement statement, Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT id, name, password, token FROM User WHERE id = ?;");
                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()) {
                    returnResult(new UserEntity(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("password"),
                            rs.getString("token")
                    ));
                }
            }
        }.run();
    }
}
