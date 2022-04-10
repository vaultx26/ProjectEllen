package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    NORTHEAST(1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHWEST(-1, 1),
    NONE(0,0);
    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public static Direction fromAngle(float angle) {
        if (angle == 0) return NORTH;
        if (angle == 45) return NORTHWEST;
        if (angle == 180) return SOUTH;
        if (angle == 90) return WEST;
        if (angle == 135) return SOUTHWEST;
        if (angle == 225) return SOUTHEAST;
        if (angle == 270) return EAST;
        return NORTHEAST;
    }
    public float getAngle() {
        if(this.dx == 0) {
            return this.getNull();
        }
        if(this.dx == 1) {
            return this.getPlus();
        }
        if(this.dx == -1) {
            return this.getMinus();
        }
        return 0;
    }
    public float getPlus() {
        if(this.dy == 0) {
            return 270;
        }
        if(this.dy == 1) {
            return 315;
        }
        if(this.dy == -1) {
            return 225;
        }
        return 0;
    }
    public float getMinus() {
        if(this.dy == 0) {
            return 90;
        }
        if(this.dy == -1) {
            return 135;
        }
        if(this.dy == 1) {
            return 45;
        }
        return 0;
    }
    public int getDx(){
        return dx;
    }
    public int getDy(){
        return dy;
    }

    public float getNull() {
        if(this.dy == -1) {
            return 180;
        } else return 0;
    }
    public Direction combine(Direction other) {
        if(this == other) {
            return this;
        }
        int newX , newY ;
        Direction direction = NONE;
        newX = getDx() == other.getDx() ? getDx() : getDx() + other.getDx();
        newY = getDy() == other.getDy() ? getDy() : getDy() + other.getDy();
        for(Direction i : Direction.values()) {
            if(i.getDx() == newX && i.getDy() == newY) {
                direction = i;
            }
        }
        return direction;
    }
    public Direction negative(int y) {
        if(y == -1) {
            return Direction.SOUTHWEST;
        }
        if(y == 0) {
            return Direction.WEST;
        }
        if(y == 1) {
            return Direction.NORTHWEST;
        }
        return Direction.NONE;
    }
    public Direction nula(int y) {
        if(y == -1) {
            return Direction.SOUTH;
        }
        if(y == 1) {
            return Direction.NORTH;
        }
        return Direction.NONE;
    }
    public Direction positive(int y) {
        if(y == -1) {
            return Direction.SOUTHEAST;
        }
        if(y == 0) {
            return Direction.EAST;
        }
        if(y == 1) {
            return Direction.NORTHEAST;
        }
        return Direction.NONE;
    }
}
