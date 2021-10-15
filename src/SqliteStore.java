import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqliteStore {
    double area;
    double perimeter;
    Connection con;
    public void open() {        
        try {
            tryOpen();
        } catch (SQLException e) {
            System.err.println("Hiba! Az SQLite adatbázis megnyitása sikertelen!");
        } catch (ClassNotFoundException e) {
            System.err.println("Hiba! a Driver nem tölthető be!");
        }
    }
    public void tryOpen() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        this.con = DriverManager.getConnection("jdbc:sqlite:data.sqlite");
    }
    public void close() {
        try {
            tryClose();
        } catch (SQLException e) {
            System.err.println("Hiba! A bezárás sikertelen!");
        }
    }
    public void tryClose() throws SQLException {
        this.con.close();
    }
    public void insert() {
        try {
            tryInsert();
        } catch (SQLException e) {
            System.err.println("Hiba! SQL fájlba beszúrás sikertelen!");
        }
    }
    public void tryInsert() throws SQLException {
        this.open();
        String sql = "insert into meterages" +
        " (area, perimeter) values" +
        " (?, ?)";
        PreparedStatement pstmt = this.con.prepareStatement(sql);
        pstmt.setDouble(1, this.area);        
        pstmt.setDouble(2, this.perimeter);
        pstmt.execute();
        this.close();
    }
    public void setData(double area, double perimeter) {
        this.area = area;
        this.perimeter = perimeter;
    }
    public void list() {}
}
