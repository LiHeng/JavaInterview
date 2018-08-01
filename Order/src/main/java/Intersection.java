/**
 * a class represent the intersection in the map
 */
public class Intersection {
    /** serial identifier in the input file */
    private String identifiers;
    /** number of tunnels */
    private int numberOfTunnels;
    /** ceiling height */
    private double ceilingHeight;

    public void setIdentifiers(String identifiers) {
        this.identifiers = identifiers;
    }

    /** floor color */
    private Color floorColor;
    /** wall color */
    private Color wallColor;
    /** ceiling color */
    private Color ceilingColor;

    public Intersection(String robot,int identifiers,int numberOfTunnels, double ceilingHeight, Color floorColor, Color wallColor, Color ceilingColor) {
        this.numberOfTunnels = numberOfTunnels;
        this.ceilingHeight = ceilingHeight;
        this.floorColor = floorColor;
        this.wallColor = wallColor;
        this.ceilingColor = ceilingColor;
        this.identifiers = robot+identifiers;
    }

    public int getNumberOfTunnels() {
        return numberOfTunnels;
    }

    public double getCeilingHeight() {
        return ceilingHeight;
    }

    public Color getFloorColor() {
        return floorColor;
    }

    public Color getWallColor() {
        return wallColor;
    }

    public Color getCeilingColor() {
        return ceilingColor;
    }

    public void setCeilingHeight(double ceilingHeight) {
        this.ceilingHeight = ceilingHeight;
    }

    public String getIdentifiers() {
        return identifiers;
    }

    @Override
    public String toString() {
        String s = "i,";
        s+=identifiers+","+numberOfTunnels+","+ceilingHeight+","+floorColor.getValue()
                +","+wallColor.getValue()+","+ceilingColor.getValue();
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Intersection)){
            return false;
        }
        Intersection other = (Intersection)obj;
        return numberOfTunnels==other.numberOfTunnels&&Math.abs(ceilingHeight-other.ceilingHeight)<0.001
                &&floorColor==other.floorColor&&wallColor==other.wallColor&&ceilingColor==other.ceilingColor;
    }
}
