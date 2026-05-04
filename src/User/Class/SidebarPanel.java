package User.Class;

import User.Userdashboard;
import internal_pages.Customers_page;
import internal_pages.Orders_page;
import internal_pages.Products_page;
import internal_pages.dashBoard_page;
import internal_pages.payment_page;

import java.awt.*;
import javax.swing.*;
import javax.swing.JDesktopPane;
import main.Products_form;

public class SidebarPanel extends JPanel {

    private boolean visible = false;
    private JDesktopPane contentPanel; 

    public SidebarPanel(JDesktopPane contentPanel) {
        this.contentPanel = contentPanel;

        setPreferredSize(new Dimension(220, 0));
        setBackground(new Color(62, 33, 35)); 
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMaximumSize(new Dimension(220, Integer.MAX_VALUE));
        this.setMinimumSize(new Dimension(220, 0));
        this.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(40, 25, 20)), 
            BorderFactory.createEmptyBorder(30, 15, 30, 15) 
        ));
        
        add(menuTitle("MENU"));
        add(Box.createVerticalStrut(20));

        add(menuButton("Dashboard", () -> openDashboard()));
        add(menuButton("Orders", () -> openOrders()));
        add(menuButton("Menu", () -> openMenu())); 
        add(menuButton("Customers", () -> openCustomers()));           
        add(menuButton("payments", () -> openPayments()));
        add(Box.createVerticalGlue());
        add(menuButton("Logout", () -> logout()));
    }

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
        if (contentPanel.getAllFrames().length > 0) {
            repositionFrame(contentPanel.getAllFrames()[0]);
        }
    }

    private void showInternalFrame(JInternalFrame newFrame) {
        for (JInternalFrame frame : contentPanel.getAllFrames()) {
            frame.dispose();
        }

        contentPanel.add(newFrame);
        newFrame.setBorder(null);
        
        javax.swing.plaf.basic.BasicInternalFrameUI ui = 
            (javax.swing.plaf.basic.BasicInternalFrameUI)newFrame.getUI();
        ui.setNorthPane(null);

        repositionFrame(newFrame);

        newFrame.setVisible(true);
        
        try {
            newFrame.setSelected(true); 
        } catch (java.beans.PropertyVetoException e) {}

        contentPanel.revalidate();
        contentPanel.repaint();
    }

private void repositionFrame(JInternalFrame frame) {
    // Using a Timer to ensure the parent components have finished layout out
    Timer timer = new Timer(100, (e) -> {
        // 1. Get the actual visible width of the content area
        int availableWidth = contentPanel.getWidth();
        int availableHeight = contentPanel.getHeight();

        // 2. Determine the preferred size of your catalog
        Dimension frameSize = frame.getPreferredSize();
        
        // 3. FORCE SIZE: If the frame is too big for the panel, 
        // we must shrink the frame, otherwise it will always be cut off.
        int finalWidth = Math.min(frameSize.width, availableWidth);
        int finalHeight = Math.min(frameSize.height, availableHeight);
        frame.setSize(finalWidth, finalHeight);

        // 4. CALCULATE FAR RIGHT POSITION
        // We take the total width and subtract the frame's width.
        // This pins the right edge of the catalog to the right edge of the screen.
        int x = availableWidth - finalWidth;
        
        // 5. SET LOCATION (Top-Right)
        // We use 0 for Y to move it to the very top as requested.
        frame.setLocation(Math.max(0, x), 0);
        
        contentPanel.revalidate();
        contentPanel.repaint();
    });
    timer.setRepeats(false);
    timer.start();
}
    private void openDashboard() {
        showInternalFrame(new dashBoard_page());
    }
    
    private void openOrders() {
       showInternalFrame(new Orders_page());
    }

    private void openMenu() {
        Products_form pf = new Products_form();
        JInternalFrame wrapper = new JInternalFrame();
        wrapper.setContentPane(pf.getContentPane());
        wrapper.setPreferredSize(pf.getPreferredSize());
        showInternalFrame(wrapper);
    }

    private void openCustomers() {
       showInternalFrame(new Customers_page());
    }

    private void openPayments() {
       showInternalFrame(new payment_page());
    }

    private void logout() {
        JOptionPane.showMessageDialog(this, "Logging out...");
        SwingUtilities.getWindowAncestor(this).dispose();
    }
}