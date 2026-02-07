package User.Class;

import User.Class.*;
import User.Userdashboard;
import internal_pages.userTable;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;


public class SidebarPanel extends JPanel {

    private boolean visible = false;
    private JDesktopPane contentPanel;// reference to the main content panel


    // 2. KEEP THIS: Your existing constructor for the actual App
    public SidebarPanel(JDesktopPane contentPanel) {
        this.contentPanel = contentPanel;

        setPreferredSize(new Dimension(220, 0));
        setBackground(new Color(62, 33, 35)); // coffee brown
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMaximumSize(new Dimension(220, Integer.MAX_VALUE));
        this.setMinimumSize(new Dimension(220, 0));
        this.setBorder(BorderFactory.createCompoundBorder(
                
        BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(40, 25, 20)), // Outer right line
        BorderFactory.createEmptyBorder(30, 15, 30, 15) 
        ));
        add(menuTitle("MENU"));
        add(Box.createVerticalStrut(20));

        add(menuButton("Home", () -> openHome()));
        add(menuButton("Orders", () -> openOrders()));
        add(menuButton("Menu", () -> openMenu()));
        add(menuButton("Reports", () -> openReports()));         
        add(menuButton("Users", () -> openUsers()));
        add(menuButton("Settings", () -> openSettings()));
        add(Box.createVerticalGlue());
        add(menuButton("Logout", () -> logout()));
    }

   
    // ===== Menu title =====
    private JLabel menuTitle(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(215, 184, 153));
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    
    private JButton menuButton(String text, Runnable action) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);

        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(78, 52, 46));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addActionListener(e -> action.run());

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(101, 67, 53));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(78, 52, 46));
            }
            
        });

        return btn;
    }


    public void toggle() {
        visible = !visible;
        setVisible(visible);
    }
  
private void showInternalFrame(JInternalFrame newFrame) {
    for (JInternalFrame frame : contentPanel.getAllFrames()) {
        frame.dispose();
    }

    contentPanel.add(newFrame);
    newFrame.setBorder(null);
    ((javax.swing.plaf.basic.BasicInternalFrameUI)newFrame.getUI()).setNorthPane(null);

    newFrame.setSize(800, 500);
    newFrame.setLocation(220, 0);

    newFrame.setVisible(true);
    
    try {
        newFrame.setSelected(true); // Bring focus to the table
    } catch (java.beans.PropertyVetoException e) {}

    contentPanel.revalidate();
    contentPanel.repaint();
}
 private void openHome() {
    
    // showInternalFrame(new HomeInternalFrame());
    System.out.println("Home Linked");
}
private void openOrders() {
    // showInternalFrame(new orderTable());
}
private void openUsers() {
    // Directly linked to your userTable class
    showInternalFrame(new userTable());
}

private void openMenu() {
    // showInternalFrame(new productTable());
}

private void openReports() {
    // showInternalFrame(new reportTable());
}

private void openSettings() {
    // showInternalFrame(new settingsTable());
}
    private void logout() {
        JOptionPane.showMessageDialog(this, "Logging out...");
       
        SwingUtilities.getWindowAncestor(this).dispose();
    }
}
