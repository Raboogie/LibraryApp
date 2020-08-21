package Library.assistant.database;

import javax.swing.*;
import java.sql.*;

/*For testing I created a new instance of "DatabaseHandler" in every controller class, but now I made the
DatabaseHandler constructor private. Now, the static method I created will be used to check the instance of the
DatabaseHandler because now all Controller classes can have access to a single DatabaseHandler instance.
*/
public class DatabaseHandler {

    private static DatabaseHandler handler = null;

    private static final String DB_URL = "jdbc:derby:database;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;

    private DatabaseHandler() {
        createConnection();
        setupBookTable();
        setMemberTable();
    }

    // used to get the DatabaseHandler object
    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    public void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").getDeclaredConstructor().newInstance();
            // ".newInstance" is deprecated, so I add ".getDeclaredConstructor().newInstance();".
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupBookTable() {
        String TABLE_NAME = "Book";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists, Ready to go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + " id varchar(200) primary key,\n"
                        + " title varchar(200),\n"
                        + " author varchar(200),\n"
                        + " publisher varchar(100),\n"
                        + " isAvail boolean default true"
                        + " )");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + " ....setupDatabase");
        }
    }

    private void setMemberTable() {
        String TABLE_NAME = "MEMBER";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists, Ready to go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + " id varchar(9) primary key,\n"
                        + " name varchar(50),\n"
                        + " mobile varchar(20),\n"
                        + " email varchar(100)"
                        + " )");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage() + " ....setupDatabase");
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        return result;
    }

    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error: " + ex.getMessage(), "Error Occurred", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        }
    }


}
