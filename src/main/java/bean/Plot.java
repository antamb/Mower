package bean;


import javafx.geometry.Point2D;

public class Plot {
    private Point2D surface;

    public Plot(final double  L, final double l) {
        this.surface = new Point2D(L,l);
    }

    /**
     *
     * Check if a position is out of bounds
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isOutOfBounds(final double x, final double y) {
        return x < 0 || x > surface.getX() || y < 0 || y > surface.getY() ;
    }
}
