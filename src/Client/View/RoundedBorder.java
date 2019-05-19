package Client.View;

import javax.swing.border.Border;
import java.awt.*;

/**
 * This is a class to create a new rounded border (that can be very aesthetic)
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class RoundedBorder implements Border {
    private int radius;

    /**
     * This is a method that initialize the rounded border with an specific radius
     * @param radius
     */
    RoundedBorder(int radius) {
        this.radius = radius;
    }

    /**
     * This is a method that returns the limits of the rounded border
     * @param c The component that we are going to apply the border
     * @return The insets of our border
     */
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    /**
     * Method to know if the border is opaque or not
     * @return We always return true, because for our application is not a necessary method
     */
    public boolean isBorderOpaque() {
        return true;
    }

    /**
     * Method that is the responsible to paint the border in the screen
     * @param c Component that we are going to apply the border
     * @param g Graphics instance that allows us to show the border
     * @param x X position that we are going to show the element with the border
     * @param y Y position that we are going to show the element with the border
     * @param width Width that the border is going to use
     * @param height Height that the border is going to have
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x,y,width-1,height-1,radius,radius);
    }

}
