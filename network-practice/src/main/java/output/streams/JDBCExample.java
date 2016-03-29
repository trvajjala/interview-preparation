package output.streams;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "admin");

        final Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        conn.setAutoCommit(false);

        stm.execute("INSERT INTO IdentityBean(username) values('MyName Badsha') ");

        stm.executeUpdate("UPDATE IdentityBean SET username='sfsdfsdfsdfds'");

        ResultSet rs = stm.executeQuery("SELECT * FROM IdentityBean");

        while (rs.next()) {

            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }

        PreparedStatement ps = conn.prepareStatement("INSERT INTO IdentityBean(username) Value(?) ");
        ps.setString(1, "Something to do");
        final boolean flag = ps.execute();

        System.out.println(" flag " + flag);
        conn.commit();

        ps = conn.prepareStatement("SELECT  * FROM IdentityBean WHERE username!=? ");
        ps.setString(1, "Something to do");
        rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }

        final CallableStatement cs = conn.prepareCall("{call  getEmpName(?,?) }");

        cs.setInt(1, 10);
        cs.registerOutParameter(2, java.sql.Types.VARCHAR);
        cs.execute();
        final String name = cs.getString(2);

        System.out.println("Name of the person " + name);
        cs.close();

    }

}
