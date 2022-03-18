package db;

import ContactPerson.person;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DoAa
 */

public class Db {

    Statement stmt;
    Connection conn = null;
     public Vector<person> listPersons = new Vector<person>();

    public Db() throws SQLException {
        // add postgre driver
        DriverManager.registerDriver(new org.postgresql.Driver());
        String dbURL = "jdbc:postgresql://localhost:5432/postgres";
        // Connection conn = DriverManager.getConnection(dbURL, "postgres" ,"12345678");
        conn = DriverManager.getConnection(dbURL, "postgres", "12345678");
        System.out.println("Connected succsesfully");

    }

    // create person table
    public void createTablePerson() throws SQLException {

        stmt = conn.createStatement();
        String sql = "CREATE TABLE PERSON "
                + "(ID SERIAL PRIMARY KEY,fname VARCHAR(20) NOT NULL, mname VARCHAR(20) NOT NULL,lname VARCHAR(20) NOT NULL"
                + ",email VARCHAR(20) UNIQUE   NOT NULL , phone INTEGER UNIQUE   NOT NULL  )";
        stmt.executeUpdate(sql);
    }
   public void getAllPerson() throws SQLException {
        stmt = conn.createStatement();
        String queryString = new String("select * from person");
        ResultSet rs = stmt.executeQuery(queryString);
        while (rs.next()) {
            listPersons.add(new person(rs.getInt("id"), rs.getString("fname"), rs.getString("mname"), rs.getString("lname"), rs.getString("email"), rs.getString("phone")));
//            System.out.println(rs.getString("phone"));
//            System.out.println(rs.getString(1));
        }

    }
   public void Add(person p) throws SQLException {
       
       PreparedStatement Pst =conn.prepareStatement("insert into person(fname,mname,lname,email, phone) values(?,?,?,?,?) RETURNING id");
       Pst.setString (1,p.getFirstName());
       Pst.setString (2,p.getMiddleName());
       Pst.setString (3,p.getLastName());
       Pst.setString (4,p.getEmail());
       Pst.setInt (5,Integer.parseInt(p.getPhone()));
       ResultSet RS=Pst.executeQuery();
       RS.next();
       p.setId(RS.getInt(1));
       listPersons.add(p);
   }
   public void updatePerson( person p) {
        try {

            PreparedStatement pst = conn.prepareStatement("update person set fname = ? , lname = ? , mname = ? , email = ? , phone = ? where id = ? ");
            pst.setString(1, p.getFirstName());
            pst.setString(2, p.getLastName());
            pst.setString(3, p.getMiddleName());
            pst.setString(4, p.getEmail());
            pst.setInt(5, Integer.parseInt(p.getPhone()));
            pst.setInt(6, p.getId());
            pst.executeUpdate();


            for (int i = 0; i < listPersons.size(); i++) {
                person personFound = listPersons.get(i);
                if (personFound.getId() == p.getId()) {
                    listPersons.set(i, p);
                }
            }

//                    System.out.println("ss");
        } catch (SQLException ex) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * @param args the command line arguments
     */
    

}
