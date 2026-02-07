package User.Class;
import User.Class.*;
import javax.swing.*;
import java.awt.*;

public class HamburgerButton extends JButton {

    public HamburgerButton() {
        setPreferredSize(new Dimension(40, 40));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(245, 241, 238)); // coffee brown

        int width = getWidth();
        int startX = 10;
        int lineWidth = width - 20;

        // draw 3 lines
        g2.fillRoundRect(startX, 12, lineWidth, 4, 10, 10);
        g2.fillRoundRect(startX, 18, lineWidth, 4, 10, 10);
        g2.fillRoundRect(startX, 24, lineWidth, 4, 10, 10);
    }
}

