/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;

/**
 *
 * @author Lester
 */

public class Products_form extends javax.swing.JFrame {
private int selectedProductId = -1;
    /**
     * Creates new form Products_form
     */
    public Products_form() {
        initComponents();
        this.setBackground(new java.awt.Color(0, 0, 0, 0));
        
        loadProductsFromDB();
    }

    
public void loadProductsFromDB() {
    try {
        config.DBcon cc = new config.DBcon();
        // ORDER BY is mandatory for the grouping logic to work
        java.sql.ResultSet rs = cc.getData("SELECT * FROM tbl_products ORDER BY category ASC");
        
        // UI Arrays
        javax.swing.JLabel[] nameLabels = {pname1, pname2, pname3, pname4, pname5, pname6};
        javax.swing.JLabel[] priceLabels = {pri1, pri2, pri4, pri5, pri6, pri7}; 
        javax.swing.JLabel[] descLabels = {des, des1, des2, des3, des4, des5};   
        javax.swing.JLabel[] imageLabels = {p1, p2, p3, p4, p5, p6};
        javax.swing.JPanel[] productPanels = {jPanel9, jPanel11, jPanel6, jPanel8, jPanel5, jPanel7};
        javax.swing.JLabel[] catHeaders = {cat1, cat2, cat3}; 

        java.awt.Color goldColor = new java.awt.Color(233, 214, 107);

        String lastCategory = "";
        int categoryIndex = -1; 
        int productInCatCount = 0; 
        int i = 0; 

        while (rs.next() && i < 6) {
            String currentCategory = rs.getString("category");

            // 1. Grouping Logic (3 Categories max)
            if (!currentCategory.equals(lastCategory)) {
                categoryIndex++;
                productInCatCount = 0; 
                lastCategory = currentCategory;
                if (categoryIndex >= 3) break;
                if (catHeaders[categoryIndex] != null) {
                    catHeaders[categoryIndex].setText(currentCategory);
                }
            }

            // 2. Limit Logic (2 Products per Category max)
            if (productInCatCount < 2) {
                final int productId = rs.getInt("p_id");
                final int index = i;

                // Set Text Data
                nameLabels[i].setText(rs.getString("p_name"));
                priceLabels[i].setText("₱" + rs.getString("price"));
                descLabels[i].setText(rs.getString("p_desc"));

                // 3. Reset Border (Ensures no border appears on load)
                productPanels[i].setBorder(null);

                // 4. Image Loading
                String imageName = rs.getString("p_image");
                if (imageName != null && !imageName.isEmpty()) {
                    String fileNameOnly = new java.io.File(imageName).getName();
                    java.net.URL imgURL = getClass().getResource("/Images/" + fileNameOnly);
                    if (imgURL != null) {
                        javax.swing.ImageIcon icon = new javax.swing.ImageIcon(imgURL);
                        java.awt.Image scaled = icon.getImage().getScaledInstance(110, 110, java.awt.Image.SCALE_SMOOTH);
                        imageLabels[i].setIcon(new javax.swing.ImageIcon(scaled));
                    }
                }

                // 5. Click Listener (Selection Only)
                // Remove old listeners to prevent double-firing
                for(java.awt.event.MouseListener ml : productPanels[i].getMouseListeners()){
                    productPanels[i].removeMouseListener(ml);
                }
                
                productPanels[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        selectedProductId = productId;

                        // Clear borders from ALL panels
                        for (javax.swing.JPanel panel : productPanels) { 
                            panel.setBorder(null); 
                        }

                        // Apply "Inside" border to THIS panel only
                        // We use a LineBorder with a thickness of 4
                        // To make it look "Inside", we use a CompoundBorder with a NEGATIVE margin 
                        // if the layout is tight, or just a simple line if the panel is large enough.
                        productPanels[index].setBorder(javax.swing.BorderFactory.createLineBorder(goldColor, 4));
                        
                        System.out.println("Selected ID: " + selectedProductId);
                    }
                });

                i++; 
                productInCatCount++; 
            }
        }
        
        this.revalidate();
        this.repaint();
        
    } catch (Exception e) {
        System.out.println("DATABASE ERROR: " + e.getMessage());
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = jPanel5 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                int shadowSize = 10;
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
        jPanel5.setOpaque(false);
        p5 = new javax.swing.JLabel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                // Create the circular clip
                int diameter = Math.min(getWidth(), getHeight());
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;

                java.awt.geom.Ellipse2D.Double circle = new java.awt.geom.Ellipse2D.Double(x, y, diameter, diameter);
                g2.setClip(circle);

                // Paint the image inside the circle
                super.paintComponent(g2);

                // Optional: Add a white border around the circle
                g2.setClip(null); // Remove clip to draw border on top
                g2.setColor(java.awt.Color.LIGHT_GRAY);
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.draw(circle);

                g2.dispose();
            }
        };
        des4 = new javax.swing.JLabel();
        pname5 = new javax.swing.JLabel();
        pri6 = new javax.swing.JLabel();
        jPanel8 = jPanel8 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                int shadowSize = 10;
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
        jPanel8.setOpaque(false);
        p4 = new javax.swing.JLabel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                // Create the circular clip
                int diameter = Math.min(getWidth(), getHeight());
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;

                java.awt.geom.Ellipse2D.Double circle = new java.awt.geom.Ellipse2D.Double(x, y, diameter, diameter);
                g2.setClip(circle);

                // Paint the image inside the circle
                super.paintComponent(g2);

                // Optional: Add a white border around the circle
                g2.setClip(null); // Remove clip to draw border on top
                g2.setColor(java.awt.Color.LIGHT_GRAY);
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.draw(circle);

                g2.dispose();
            }
        };
        des3 = new javax.swing.JLabel();
        pname4 = new javax.swing.JLabel();
        pri5 = new javax.swing.JLabel();
        jPanel6 = jPanel6 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                int shadowSize = 10;
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
        jPanel6.setOpaque(false);
        p3 = new javax.swing.JLabel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                // Create the circular clip
                int diameter = Math.min(getWidth(), getHeight());
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;

                java.awt.geom.Ellipse2D.Double circle = new java.awt.geom.Ellipse2D.Double(x, y, diameter, diameter);
                g2.setClip(circle);

                // Paint the image inside the circle
                super.paintComponent(g2);

                // Optional: Add a white border around the circle
                g2.setClip(null); // Remove clip to draw border on top
                g2.setColor(java.awt.Color.LIGHT_GRAY);
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.draw(circle);

                g2.dispose();
            }
        };
        des2 = new javax.swing.JLabel();
        pname3 = new javax.swing.JLabel();
        pri4 = new javax.swing.JLabel();
        jPanel9 = jPanel9 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                int shadowSize = 10;
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
        jPanel9.setOpaque(false);
        p1 = new javax.swing.JLabel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                // Create the circular clip
                int diameter = Math.min(getWidth(), getHeight());
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;

                java.awt.geom.Ellipse2D.Double circle = new java.awt.geom.Ellipse2D.Double(x, y, diameter, diameter);
                g2.setClip(circle);

                // Paint the image inside the circle
                super.paintComponent(g2);

                // Optional: Add a white border around the circle
                g2.setClip(null); // Remove clip to draw border on top
                g2.setColor(java.awt.Color.LIGHT_GRAY);
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.draw(circle);

                g2.dispose();
            }
        };
        pname1 = new javax.swing.JLabel();
        des = new javax.swing.JLabel();
        pri1 = new javax.swing.JLabel();
        jPanel11 = jPanel11 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                int shadowSize = 10;
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
        jPanel11.setOpaque(false);
        p2 = new javax.swing.JLabel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                // Create the circular clip
                int diameter = Math.min(getWidth(), getHeight());
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;

                java.awt.geom.Ellipse2D.Double circle = new java.awt.geom.Ellipse2D.Double(x, y, diameter, diameter);
                g2.setClip(circle);

                // Paint the image inside the circle
                super.paintComponent(g2);

                // Optional: Add a white border around the circle
                g2.setClip(null); // Remove clip to draw border on top
                g2.setColor(java.awt.Color.LIGHT_GRAY);
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.draw(circle);

                g2.dispose();
            }
        };
        des1 = new javax.swing.JLabel();
        pname2 = new javax.swing.JLabel();
        pri2 = new javax.swing.JLabel();
        jPanel7 = jPanel7 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                int shadowSize = 10;
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
        jPanel7.setOpaque(false);
        p6 = new javax.swing.JLabel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                // Create the circular clip
                int diameter = Math.min(getWidth(), getHeight());
                int x = (getWidth() - diameter) / 2;
                int y = (getHeight() - diameter) / 2;

                java.awt.geom.Ellipse2D.Double circle = new java.awt.geom.Ellipse2D.Double(x, y, diameter, diameter);
                g2.setClip(circle);

                // Paint the image inside the circle
                super.paintComponent(g2);

                // Optional: Add a white border around the circle
                g2.setClip(null); // Remove clip to draw border on top
                g2.setColor(java.awt.Color.LIGHT_GRAY);
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.draw(circle);

                g2.dispose();
            }
        };
        des5 = new javax.swing.JLabel();
        pname6 = new javax.swing.JLabel();
        pri7 = new javax.swing.JLabel();
        cat3 = new javax.swing.JLabel();
        cat1 = new javax.swing.JLabel();
        cat2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        addto_cart = addto_cart = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();

                // This makes the rounded edges smooth
                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

                // This tells the code: "Use whatever color is already set for this panel"
                g2.setColor(getBackground());

                // This only paints the rounded area
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                g2.dispose();
            }
        };
        // Crucial: Add this in your constructor to hide the "square" background
        addto_cart.setOpaque(false);
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(253, 245, 230));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(166, 123, 91));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/A67B5B.png"))); // NOI18N
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 200, 30));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/coffe.png"))); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 60, 50));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 50));

        jPanel5.setBackground(new java.awt.Color(62, 39, 35));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(p5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 90, 70));

        des4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        des4.setForeground(new java.awt.Color(210, 180, 140));
        des4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(des4, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 110, 220, 10));

        pname5.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        pname5.setForeground(new java.awt.Color(255, 253, 208));
        pname5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(pname5, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 90, 190, 20));

        pri6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        pri6.setForeground(new java.awt.Color(233, 214, 107));
        pri6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(pri6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 150, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 230, 180));

        jPanel8.setBackground(new java.awt.Color(62, 39, 35));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel8.add(p4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 90, 70));

        des3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        des3.setForeground(new java.awt.Color(210, 180, 140));
        des3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel8.add(des3, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 110, 220, 10));

        pname4.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        pname4.setForeground(new java.awt.Color(255, 253, 208));
        pname4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel8.add(pname4, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 90, 190, 20));

        pri5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        pri5.setForeground(new java.awt.Color(233, 214, 107));
        pri5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel8.add(pri5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 150, -1));

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 230, 180));

        jPanel6.setBackground(new java.awt.Color(62, 39, 35));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(p3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 90, 70));

        des2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        des2.setForeground(new java.awt.Color(210, 180, 140));
        des2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(des2, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 110, 200, 10));

        pname3.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        pname3.setForeground(new java.awt.Color(255, 253, 208));
        pname3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(pname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 90, 190, 20));

        pri4.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        pri4.setForeground(new java.awt.Color(233, 214, 107));
        pri4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel6.add(pri4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 150, -1));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 230, 180));

        jPanel9.setBackground(new java.awt.Color(62, 39, 35));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel9.add(p1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 90, 70));

        pname1.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        pname1.setForeground(new java.awt.Color(255, 253, 208));
        pname1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel9.add(pname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 90, 190, 20));

        des.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        des.setForeground(new java.awt.Color(210, 180, 140));
        des.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel9.add(des, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 110, 220, 10));

        pri1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        pri1.setForeground(new java.awt.Color(233, 214, 107));
        pri1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel9.add(pri1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 150, -1));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 230, 180));

        jPanel11.setBackground(new java.awt.Color(62, 39, 35));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(p2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 90, 70));

        des1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        des1.setForeground(new java.awt.Color(210, 180, 140));
        des1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(des1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 190, 10));

        pname2.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        pname2.setForeground(new java.awt.Color(255, 253, 208));
        pname2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(pname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 90, 170, 20));

        pri2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        pri2.setForeground(new java.awt.Color(233, 214, 107));
        pri2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(pri2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 150, -1));

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 230, 180));

        jPanel7.setBackground(new java.awt.Color(62, 39, 35));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        p6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(p6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 90, 70));

        des5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        des5.setForeground(new java.awt.Color(210, 180, 140));
        des5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(des5, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 110, 220, 10));

        pname6.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        pname6.setForeground(new java.awt.Color(255, 253, 208));
        pname6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(pname6, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 90, 190, 20));

        pri7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        pri7.setForeground(new java.awt.Color(233, 214, 107));
        pri7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel7.add(pri7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 150, -1));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 270, 230, 180));

        cat3.setFont(new java.awt.Font("SansSerif", 1, 19)); // NOI18N
        cat3.setForeground(new java.awt.Color(60, 42, 33));
        cat3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(cat3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 220, 30));

        cat1.setFont(new java.awt.Font("SansSerif", 1, 19)); // NOI18N
        cat1.setForeground(new java.awt.Color(60, 42, 33));
        cat1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(cat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 230, 30));

        cat2.setFont(new java.awt.Font("SansSerif", 1, 19)); // NOI18N
        cat2.setForeground(new java.awt.Color(60, 42, 33));
        cat2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(cat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 230, 30));

        jLabel1.setBackground(new java.awt.Color(62, 39, 35));
        jLabel1.setFont(new java.awt.Font("Rockwell", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(92, 50, 22));
        jLabel1.setText("PRODUCT CATALOG");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, 30));

        addto_cart.setBackground(new java.awt.Color(92, 50, 22));
        addto_cart.setToolTipText("");
        addto_cart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addto_cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addto_cartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addto_cartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addto_cartMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addto_cartMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addto_cartMouseReleased(evt);
            }
        });
        addto_cart.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ADD TO CART");
        addto_cart.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 130, 30));

        jPanel3.add(addto_cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, 170, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addto_cartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addto_cartMouseClicked
   if (selectedProductId == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Please select a product first!");
    } else {
        // Logic to add selectedProductId to your database cart table
        javax.swing.JOptionPane.showMessageDialog(this, "Product " + selectedProductId + " added to cart!");
        
        // Example: cc.insertData("INSERT INTO tbl_cart (u_id, p_id) VALUES (1, " + selectedProductId + ")");
    }
    }//GEN-LAST:event_addto_cartMouseClicked

    private void addto_cartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addto_cartMouseEntered
        addto_cart.setBackground(new java.awt.Color(122, 70, 42));
    }//GEN-LAST:event_addto_cartMouseEntered

    private void addto_cartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addto_cartMouseExited
       addto_cart.setBackground(new java.awt.Color(92, 50, 22));
    }//GEN-LAST:event_addto_cartMouseExited

    private void addto_cartMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addto_cartMousePressed
        addto_cart.setBackground(new Color(170, 110, 50));
    }//GEN-LAST:event_addto_cartMousePressed

    private void addto_cartMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addto_cartMouseReleased
        addto_cart.setBackground(new java.awt.Color(122, 70, 42));
    }//GEN-LAST:event_addto_cartMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Products_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Products_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Products_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Products_form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Products_form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addto_cart;
    private javax.swing.JLabel cat1;
    private javax.swing.JLabel cat2;
    private javax.swing.JLabel cat3;
    private javax.swing.JLabel des;
    private javax.swing.JLabel des1;
    private javax.swing.JLabel des2;
    private javax.swing.JLabel des3;
    private javax.swing.JLabel des4;
    private javax.swing.JLabel des5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    private javax.swing.JLabel p4;
    private javax.swing.JLabel p5;
    private javax.swing.JLabel p6;
    private javax.swing.JLabel pname1;
    private javax.swing.JLabel pname2;
    private javax.swing.JLabel pname3;
    private javax.swing.JLabel pname4;
    private javax.swing.JLabel pname5;
    private javax.swing.JLabel pname6;
    private javax.swing.JLabel pri1;
    private javax.swing.JLabel pri2;
    private javax.swing.JLabel pri4;
    private javax.swing.JLabel pri5;
    private javax.swing.JLabel pri6;
    private javax.swing.JLabel pri7;
    // End of variables declaration//GEN-END:variables
}
