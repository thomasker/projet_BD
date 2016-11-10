import java.sql.*;

public class AccesBdd {
    public static void main( String[] args) {
        try {
            //Enregistrement DriverManager
            System.out.println("Enregistrement du driver Oracle ...");
            java.sql.DriverManager.registerDriver(
                    new oracle.jdbc.driver.OracleDriver()
                    );
            System.out.println("Done\n" );
            //connection
            System.out.print("Etablissement de la connection ...\n");
            String url = "jdbc:oracle:thin:@ensioracle1.imag.fr:" + "1521:ensioracle1";
            String user = "berthoch";
            String passwd = "berthoch";
            Connection connection = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connecté");
            //Execution requête
            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT 1;");
            dumpResultSet(rset);
            rset.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dumpResultSet(ResultSet rset) throws SQLException {
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        for (int k=1;k<=i;k++)
            System.out.print(rsetmd.getColumnName(k) + "\t");
        System.out.println();
        while (rset.next()) {
            for (int j = 1; j <= i; j++) {
                System.out.print(rset.getString(j) + "\t");
            }
            System.out.println();
        }
    }
}
