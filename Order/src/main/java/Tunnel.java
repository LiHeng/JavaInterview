/**
 * A class to represent tunnels in the map
 */
public class Tunnel {

    /** serial identifiers of the tunnel */
    private String serialIdentifiers;

    /** length of tunnel */
    private double length;
    /** ceiling height */
    private double ceilingHeight;
    /** floor color */
    private Color floorColor;
    /** wall color */
    private Color wallColor;
    /** ceiling color */
    private Color ceilingColor;

    /** start intersection */
    private Intersection start;
    /** end intersection */
    private Intersection end;

    public Tunnel(String robotName,String serialIdentifiers,double length, double ceilingHeight, Color floorColor, Color wallColor, Color ceilingColor) {
        this.length = length;
        this.ceilingHeight = ceilingHeight;
        this.floorColor = floorColor;
        this.wallColor = wallColor;
        this.ceilingColor = ceilingColor;
        // set the serial identifiers
        String ids[] = serialIdentifiers.split("-");
        this.serialIdentifiers = robotName+ids[0]+"-"+robotName+ids[1];
    }

    public double getLength() {
        return length;
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

    public void setLength(double length) {
        this.length = length;
    }

    public void setCeilingHeight(double ceilingHeight) {
        this.ceilingHeight = ceilingHeight;
    }

    public Color getCeilingColor(){
        return ceilingColor;
    }

    public Intersection getStart() {
        return start;
    }

    public void setStart(Intersection start) {
        this.start = start;
    }

    public Intersection getEnd() {
        return end;
    }

    public void setEnd(Intersection end) {
        this.end = end;
    }

    public String getSerialIdentifiers() {
        return serialIdentifiers;
    }

    public void setSerialIdentifiers(String serialIdentifiers) {
        this.serialIdentifiers = serialIdentifiers;
    }

    @Override
    public String toString() {
        String s = "t,";
        s+=serialIdentifiers+","+length+","+ceilingHeight+","+floorColor.getValue()+","+
                wallColor.getValue()+","+ceilingColor.getValue();
        return s;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tunnel)){
            return false;
        }
        Tunnel other = (Tunnel) obj;
        return Math.abs(length-other.length)<length%0.01&&Math.abs(ceilingHeight-other.ceilingHeight)<0.001
                &&floorColor==other.floorColor&&wallColor==other.wallColor&&ceilingColor==other.ceilingColor;
    }
}
