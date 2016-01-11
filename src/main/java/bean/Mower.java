package bean;

import exception.IllegalMoveException;
import javafx.geometry.Point3D;

import java.util.List;

public class Mower {

    public enum DIRECTION {
        N(0,1,0),
        E(1,0,90),
        S(0,-1,180),
        W(-1,0,270),
        UNKNOWN(-1,-1,-1);

        private double x,y,radius;
        DIRECTION(final double x, final double y, final double radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        public double getX(){
            return x;
        }

        public double getY(){
            return y;
        }

        public double getRadius(){
            return radius;
        }
    }

    public enum MOVE {
        A(0),
        D(90),
        G(-90);

        private double radius;
        MOVE(final double radius) {
            this.radius = radius;
        }

        public double getRadius() {
            return radius;
        }
    }

    private String name;
    private Point3D position;
    private List<MOVE> instructions;

    public Point3D getPosition() {
        return position;
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MOVE> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<MOVE> instructions) {
        this.instructions = instructions;
    }

    public Mower(final double x, final double y, final DIRECTION direction) {
        this.position = new Point3D(x, y, direction.getRadius());
    }

    /**
     *
     * Get the direction enum from radius
     *
     * @param radius
     * @return
     */
    public static DIRECTION getDirection(final int radius) {
        switch(radius) {
            case 0:
                return DIRECTION.N;
            case 90:
                return DIRECTION.E;
            case 180:
                return DIRECTION.S;
            case 270:
                return DIRECTION.W;
            default:
                return DIRECTION.UNKNOWN;
        }
    }

    /**
     *
     * Browse the surface of the plot following the instructions
     *
     * @param plot
     * @return
     * @throws IllegalMoveException
     */
    public Point3D browse(final Plot plot) throws IllegalMoveException {
        instructions.stream().forEach(instruction -> {
            switch (instruction) {
                case G:
                    position = position.add(0,0, position.getZ() == 0.0 ? DIRECTION.W.getRadius() : MOVE.G.getRadius());
                    break;
                case D:
                    position = position.add(0,0, position.getZ() == DIRECTION.W.getRadius() ? -position.getZ() : MOVE.D.getRadius());
                    break;
                case A:
                    DIRECTION direction = null;
                    direction = getDirection((int)position.getZ());
                    if (!plot.isOutOfBounds(position.getX() + direction.getX(), position.getY() + direction.getY())) {
                        position = position.add(direction.getX(), direction.getY(), 0.0);
                    }
                    break;
                default:
                    break;
            }
        });

        return position;
    }

    @Override
    public String toString() {
        return "[Position][" + (int)position.getX() + " " + (int)position.getY() + " " + getDirection((int)position.getZ()) + "]";
    }
}
