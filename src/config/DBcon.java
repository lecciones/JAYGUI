            package config;

            import static config.DBcon.connectDB;
            import java.sql.Connection;
            import java.sql.DriverManager;
            import java.sql.PreparedStatement;
            import java.sql.ResultSet;
            import java.sql.SQLException;
            import java.sql.Statement;
            import javax.swing.JOptionPane;
            import net.proteanit.sql.DbUtils;

            public class DBcon {
                 
                  public Connection connect;

               public static Connection connectDB() {
                    Connection con = null;
                    try {
                        Class.forName("org.sqlite.JDBC"); // Load the SQLite JDBC driver
                        con = DriverManager.getConnection("jdbc:sqlite:users.db"); // Establish connection
                        System.out.println("Connection Successful");
                    } catch (Exception e) {
                        System.out.println("Connection Failed: " + e);
                    }
                    return con;
                }

               public ResultSet getData(String sql) throws SQLException{
                        Statement stmt = connect.createStatement();
                        ResultSet rst = stmt.executeQuery(sql);
                        return rst;
                    }

                    //Function to save data
                    public int insertData(String sql){
                        int result;
                        try{
                            PreparedStatement pst = connect.prepareStatement(sql);
                            pst.executeUpdate();
                            System.out.println("Inserted Successfully!");
                            pst.close();
                            result =1;
                        }catch(SQLException ex){
                            System.out.println("Connection Error: "+ex);
                            result =0;
                        }
                        return result;
                    }

                    //Function to update data
                    public void updateData(String sql){
                        try{
                            PreparedStatement pst = connect.prepareStatement(sql);
                                int rowsUpdated = pst.executeUpdate();
                                    if(rowsUpdated > 0){
                                        JOptionPane.showMessageDialog(null, "Data Updated Successfully!");
                                    }else{
                                        System.out.println("Data Update Failed!");
                                    }
                                    pst.close();
                        }catch(SQLException ex){
                            System.out.println("Connection Error: "+ex);
                        }

                    }

                    //Function to delete data
                    public void deleteData(int id, String table, String table_id){
                        try{
                            PreparedStatement pst = connect.prepareStatement("DELETE FROM "+table+" WHERE "+table_id+" = ?");
                            pst.setInt(1, id);
                            int rowsDeleted = pst.executeUpdate();
                                if(rowsDeleted > 0){
                                    JOptionPane.showMessageDialog(null, "Deleted Successfully!");
                                }else{
                                    System.out.println("Deletion Failed!");
                                }
                                pst.close();
                        }catch(SQLException ex){
                            JOptionPane.showMessageDialog(null, "Data cannot be deleted\nContact the administrator.");
                        }
                    }
                public String authenticate(String sql, Object... values) {
                try (Connection conn = connectDB();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    for (int i = 0; i < values.length; i++) {
                        pstmt.setObject(i + 1, values[i]);
                    }

                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            return rs.getString("type");
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Login Error: " + e.getMessage());
                }
                return null;
            }
                 public void displayData(String sql, javax.swing.JTable table) {
    try (Connection conn = connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        
        // This line automatically maps the Resultset to your JTable
        table.setModel(DbUtils.resultSetToTableModel(rs));
        
    } catch (SQLException e) {
        System.out.println("Error displaying data: " + e.getMessage());
    }
}
                    }

