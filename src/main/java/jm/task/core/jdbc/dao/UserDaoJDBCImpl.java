package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.metamodel.internal.JpaMetaModelPopulationSetting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;

        String sql = "CREATE TABLE USERS "
                + "(id INTEGER not NULL AUTO_INCREMENT, "
                + " name VARCHAR(255), "
                + " lastName VARCHAR(255), "
                + " age TINYINT, "
                + " PRIMARY KEY ( id ))";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
//            System.out.println("Already exists?");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;

        String sql = "DROP TABLE IF EXISTS USERS";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
//            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM USERS WHERE ID=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
//            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getConnection();
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USERS";
        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                userList.add(user);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        Statement statement = null;

        String sql = "DELETE FROM USERS";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
//            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
            }
        }
    }
}
