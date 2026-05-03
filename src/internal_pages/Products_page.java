/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internal_pages;

import config.DBcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import main.Productform;



/**
 *
 * @author Angie
 */
public class Products_page extends javax.swing.JInternalFrame {

    /**
     * Creates new form Products_pager
     */
    public Products_page() {
        initComponents();
        
        displayData();
        
                 this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
    BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
    bi.setNorthPane(null);
    
products_table.setRowHeight(35); // Slightly taller for that premium feel
products_table.setShowGrid(false);
products_table.setIntercellSpacing(new java.awt.Dimension(0, 0));
products_table.setBackground(new Color(255, 252, 245)); // Very light cream base
products_table.setBorder(null);

// 2. Custom Header Styling
products_table.getTableHeader().setOpaque(false);
products_table.getTableHeader().setPreferredSize(new java.awt.Dimension(100, 40));

products_table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // The warm tan color from the image header
        setBackground(new Color(235, 215, 185)); 
        setForeground(new Color(90, 60, 40)); // Dark brown text
        setFont(new Font("Segoe UI", Font.BOLD, 15));
        
        setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15));
        return this;
    }
});


products_table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        c.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        if (isSelected) {
            // Highlighting with a slightly darker tan/orange from the image buttons
            c.setBackground(new Color(225, 190, 150)); 
            c.setForeground(new Color(60, 30, 10)); 
        } else {
            // Zebra striping: Alternates between white and the light beige in the image
            c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(250, 243, 230));
            c.setForeground(new Color(60, 40, 30)); // Deep brown text
        }

        ((JLabel)c).setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15));
        return c;
    }
});


javax.swing.border.Border line = javax.swing.BorderFactory.createLineBorder(new Color(180, 160, 140), 1);
jScrollPane1.setBorder(line);
jScrollPane1.getViewport().setBackground(Color.WHITE);
    }

public void displayData() {
    config.DBcon cc = new config.DBcon();

    String query = "SELECT p_id as 'ID', p_name as 'Product Name', "
                 + "category as 'Category', price as 'Price', "
                 + "p_image as 'Image', p_desc as 'Description', p_status as 'Status' " // Added p_image here
                 + "FROM tbl_products";
    
    cc.displayData(query, products_table);
}
 
 public void searchData(String text) {
    config.DBcon cc = new config.DBcon();
    String query = "SELECT p_id as 'ID', p_name as 'Product Name', "
                 + "category as 'Category', price as 'Price', p_status as 'Status' "
                 + "FROM tbl_products WHERE p_name LIKE '%" + text + "%' "
                 + "OR category LIKE '%" + text + "%'";
    cc.displayData(query, products_table);
}
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        products_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        p_add = // Inside initComponents
        p_add = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g); 
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                int shadowSize = 5;
                int borderRadius = 10;
                int width = Math.max(0, getWidth() - shadowSize * 2);
                int height = Math.max(0, getHeight() - shadowSize * 2);

                // Draw Shadow
                for (int i = 0; i < shadowSize; i++) {
                    g2.setColor(new java.awt.Color(0, 0, 0, (shadowSize - i) * 5)); 
                    g2.drawRoundRect(shadowSize - i, shadowSize - i, width + i * 2, height + i * 2, borderRadius, borderRadius);
                }

                // Fill Panel
                g2.setColor(getBackground());
                g2.fillRoundRect(shadowSize, shadowSize, width, height, borderRadius, borderRadius);
                g2.dispose();
            }
        };
        p_add.setOpaque(false);
        jLabel2 = new javax.swing.JLabel();
        edit =  new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                int shadowSize = 5;
                int borderRadius = 10;
                int width = getWidth() - shadowSize * 2;
                int height = getHeight() - shadowSize * 2;

                // 1. Draw the Shadow
                for (int i = 0; i < shadowSize; i++) {
                    // Gradually fade the black color to create a soft blur
                    g2.setColor(new java.awt.Color(0, 0, 0, (shadowSize - i) * 5)); 
                    g2.drawRoundRect(shadowSize - i, shadowSize - i, width + i * 2, height + i * 2, borderRadius, borderRadius);
                }

                // 2. Fill the Main Panel (White Card)
                g2.setColor(getBackground()); // Uses the color from the Design tab
                g2.fillRoundRect(shadowSize, shadowSize, width, height, borderRadius, borderRadius);

                g2.dispose();
            }
        };
        // This makes the area outside the rounded card transparent
        edit.setOpaque(false);
        ;
        jLabel3 = new javax.swing.JLabel();
        delete = delete = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                int shadowSize = 5;
                int borderRadius = 10;
                int width = getWidth() - shadowSize * 2;
                int height = getHeight() - shadowSize * 2;

                // 1. Draw the Shadow
                for (int i = 0; i < shadowSize; i++) {
                    // Gradually fade the black color to create a soft blur
                    g2.setColor(new java.awt.Color(0, 0, 0, (shadowSize - i) * 5)); 
                    g2.drawRoundRect(shadowSize - i, shadowSize - i, width + i * 2, height + i * 2, borderRadius, borderRadius);
                }

                // 2. Fill the Main Panel (White Card)
                g2.setColor(getBackground()); // Uses the color from the Design tab
                g2.fillRoundRect(shadowSize, shadowSize, width, height, borderRadius, borderRadius);

                g2.dispose();
            }
        };
        // This makes the area outside the rounded card transparent
        delete.setOpaque(false);
        ;
        jLabel4 = new javax.swing.JLabel();
        searchData = new javax.swing.JTextField();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(253, 245, 230));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        products_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(products_table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 610, 270));

        jPanel2.setBackground(new java.awt.Color(166, 123, 91));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coffe.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 60, 70));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/A67B5B.png"))); // NOI18N
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 200, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 60));

        jLabel1.setBackground(new java.awt.Color(62, 39, 35));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(92, 50, 22));
        jLabel1.setText("PRODUCT MANAGEMENT");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        p_add.setBackground(new java.awt.Color(220, 210, 190));
        p_add.setEnabled(false);
        p_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p_addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                p_addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                p_addMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                p_addMousePressed(evt);
            }
        });
        p_add.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(92, 50, 22));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Add Products");
        p_add.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 0, -1, 30));

        jPanel1.add(p_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 30));

        edit.setBackground(new java.awt.Color(235, 215, 185));
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                editMousePressed(evt);
            }
        });
        edit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(92, 50, 22));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Update Products");
        edit.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 30));

        jPanel1.add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 150, 30));

        delete.setBackground(new java.awt.Color(220, 210, 190));
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteMouseExited(evt);
            }
        });
        delete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(92, 50, 22));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Remove  Products");
        delete.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 170, 30));

        jPanel1.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 190, 30));

        searchData.setBackground(new java.awt.Color(253, 245, 230));
        searchData.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, java.awt.Color.gray, null, new java.awt.Color(153, 153, 153)));
        searchData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDataActionPerformed(evt);
            }
        });
        searchData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchDataKeyReleased(evt);
            }
        });
        jPanel1.add(searchData, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 140, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchDataActionPerformed

    private void editMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMousePressed
        edit.setBackground(new Color(210, 188, 155));
    }//GEN-LAST:event_editMousePressed

    private void editMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseExited
        edit.setBackground(new Color(235, 215, 185));
    }//GEN-LAST:event_editMouseExited

    private void editMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseEntered
        edit.setBackground(new Color(225, 205, 175));
    }//GEN-LAST:event_editMouseEntered

    private void editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseClicked
        int rowIndex = products_table.getSelectedRow();

    if (rowIndex < 0) {
        JOptionPane.showMessageDialog(null, "Please Select an Item!");
    } else {
        TableModel model = products_table.getModel();
        // Ensure you are calling Productform (the one with the image logic)
        Productform stf = new Productform();
        
        // Get the ID from the table
        String id = model.getValueAt(rowIndex, 0).toString();
        
        // Since the table only shows ID, Name, Category, Price, 
        // we should fetch the FULL data (including image path) from the DB
        config.DBcon dbc = new config.DBcon();
     // Inside Products_page.java -> editMouseClicked
try {
    java.sql.ResultSet rs = dbc.getData("SELECT * FROM tbl_products WHERE p_id = '" + id + "'");
    if (rs.next()) {
        stf.id.setText(rs.getString("p_id"));
        stf.pname.setText(rs.getString("p_name"));
        stf.category.setText(rs.getString("category"));
        stf.price.setText(rs.getString("price"));
        stf.desc.setText(rs.getString("p_desc"));
        
        // Handle the Image Path
        stf.oldpath = rs.getString("p_image");
        
        // SET THE COMBO BOX VALUE HERE
        // 'stats' is the name of your JComboBox in Productform
        stf.stats.setSelectedItem(rs.getString("p_status")); 
    }
} catch (java.sql.SQLException e) {
    System.out.println("Error: " + e.getMessage());
}

        stf.setVisible(true);
        stf.action = "Update";
        stf.st_label.setText("Update Product");
        
        // Refresh the image on the form immediately
        stf.displayImageIcon(); 
        
        stf.pack();
        stf.setLocationRelativeTo(null);
    }
    }//GEN-LAST:event_editMouseClicked

    private void p_addMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_addMousePressed
        p_add.setBackground(new Color(230, 220, 200));
    }//GEN-LAST:event_p_addMousePressed

    private void p_addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_addMouseExited
        p_add.setBackground(new Color(252, 248, 240));
    }//GEN-LAST:event_p_addMouseExited

    private void p_addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_addMouseEntered
        p_add.setBackground(new Color(245, 230, 210));
    }//GEN-LAST:event_p_addMouseEntered

    private void p_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_addMouseClicked
   try {
        Productform stf = new Productform();
        stf.setVisible(true); // Call this first
        stf.action = "Add";
        stf.st_label.setText("SAVE PRODUCT"); // This updates the button text inside the form
        
        stf.pack();
        stf.setLocationRelativeTo(null);

        // Close current page
        Component comp = (Component) evt.getSource();
        javax.swing.JFrame ancestor = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(comp);
        if (ancestor != null) {
            ancestor.dispose();
        }
    } catch (Exception e) {
        // This will print the error in the NetBeans Output window if it fails
        e.printStackTrace(); 
        javax.swing.JOptionPane.showMessageDialog(null, "Error opening form: " + e.getMessage());
    }
    }//GEN-LAST:event_p_addMouseClicked

    private void deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseExited
        edit.setBackground(new Color(252, 248, 240));
    }//GEN-LAST:event_deleteMouseExited

    private void deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseEntered
        edit.setBackground(new Color(245, 230, 210));
    }//GEN-LAST:event_deleteMouseEntered

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        int rowIndex = products_table.getSelectedRow();
        if(rowIndex < 0){
            JOptionPane.showMessageDialog(null, "Please select data first from the table!");
        }else{
            TableModel model = products_table.getModel();
            Object value = model.getValueAt(rowIndex, 0);
            String id = value.toString();
            int a = JOptionPane.showConfirmDialog(null, "Are you sure to delete ID: "+id);
            if(a == JOptionPane.YES_OPTION){
                DBcon dbc = new DBcon();
                int u_id = Integer.parseInt(id);
                dbc.deleteData(u_id, "tbl_products", "p_id");
                displayData();
            }
        }
    }//GEN-LAST:event_deleteMouseClicked

    private void searchDataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDataKeyReleased
   searchData(searchData.getText());
    }//GEN-LAST:event_searchDataKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel delete;
    private javax.swing.JPanel edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel p_add;
    private javax.swing.JTable products_table;
    private javax.swing.JTextField searchData;
    // End of variables declaration//GEN-END:variables
}
