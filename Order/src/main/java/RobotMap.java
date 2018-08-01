import java.io.*;
import java.util.Comparator;

/**
 * A class represent the map that a robot has seen
 */
public class RobotMap {

    /** list of intersections in the map */
    private List<Intersection> intersections = new ArrayList<>();
    /** list of tunnels in the map */
    private List<Tunnel> tunnels = new ArrayList<>();

    /** construct a map from input file */
    public RobotMap(String filePath) {
        File file = new File(filePath);
        readInput(file);
    }

    public RobotMap(){}

    /** read the information of intersections and
     * tunnels from input file
     * */
    private void readInput(File file){
        String filename = file.getName();
        String robotName = filename.substring(5,filename.lastIndexOf("."));
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine())!=null){
                String[] tokens = line.split(",");
                if (tokens[0].equals("i")){
                    Intersection intersection = new Intersection(robotName,Integer.valueOf(tokens[1]),Integer.valueOf(tokens[2]),
                            Double.valueOf(tokens[3]),Color.valueOf(tokens[4]),Color.valueOf(tokens[5]),Color.valueOf(tokens[6]));
                    intersections.add(intersection);
                }else {
                    Tunnel tunnel = new Tunnel(robotName,tokens[1], Double.valueOf(tokens[2]),Double.valueOf(tokens[3]),Color.valueOf(tokens[4]),Color.valueOf(tokens[5]),Color.valueOf(tokens[6]));
                    tunnels.add(tunnel);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < tunnels.size(); i++) {
            String serialIdentifiers = tunnels.get(i).getSerialIdentifiers();
            String ids[] = serialIdentifiers.split("-");
            tunnels.get(i).setStart(findBySerialId(ids[0]));
            tunnels.get(i).setEnd(findBySerialId(ids[1]));
        }
        intersections.sort(new Comparator<Intersection>() {
            @Override
            public int compare(Intersection o1, Intersection o2) {
                return o1.getIdentifiers().compareTo(o2.getIdentifiers());
            }
        });

        tunnels.sort(new Comparator<Tunnel>() {
            @Override
            public int compare(Tunnel o1, Tunnel o2) {
                return o1.getSerialIdentifiers().compareTo(o2.getSerialIdentifiers());
            }
        });

    }

    /**
     * find an intersection by serial identifiers
     */
    private Intersection findBySerialId(String id){
        for (int i = 0; i < intersections.size(); i++) {
            if (intersections.get(i).getIdentifiers().equals(id)){
                return intersections.get(i);
            }
        }
        return null;
    }

    /**
     * add an intersection to the map
     */
    public void addIntersection(Intersection intersection){
        intersections.add(intersection);
    }

    /**
     * add a tunnel to the map
     */
    public void addTunnel(Tunnel tunnel){
        tunnels.add(tunnel);
    }

    /**
     * write the map information to file
     */
    public void writeToFile(String name){

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(name));
            for (int i = 0; i < intersections.size() ; i++){
                Intersection intersection = intersections.get(i);
                writer.write(intersection.toString());
                writer.newLine();
            }
            for (int i = 0; i < tunnels.size() ; i++){
                Tunnel tunnel = tunnels.get(i);
                writer.write(tunnel.toString());
                writer.newLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * merge list of intersections to an existing map
     */
    private static void mergeIntersections(RobotMap map, List<Intersection> intersections){
        for (int i = 0; i < intersections.size(); i++) {
            Intersection intersection = intersections.get(i);
            if(!map.intersections.contains(intersection)){
                map.addIntersection(intersection);
            }else {
                Intersection repeat = map.intersections.get(map.intersections.indexOf(intersection));
                double newHeight = 0.0d;
                if (intersection.getIdentifiers().compareTo(repeat.getIdentifiers())<0){
                    newHeight = intersection.getCeilingHeight();
                }else {
                    newHeight = repeat.getCeilingHeight();
                }
                List<String> serialTokens = new ArrayList<>();
                String serial = repeat.getIdentifiers();
                String[] tokens = serial.split(":");
                for(String s:tokens){
                    serialTokens.add(s);
                }
                serialTokens.add(intersection.getIdentifiers());
                serialTokens.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
                String newSerial = "";
                for (int j = 0; j < serialTokens.size(); j++) {
                    newSerial += serialTokens.get(j);
                    if (j<serialTokens.size()-1){
                        newSerial+=":";
                    }
                }
                repeat.setIdentifiers(newSerial);
                intersection.setIdentifiers(newSerial);

                repeat.setCeilingHeight(newHeight);
                intersection.setCeilingHeight(newHeight);
            }
        }
        map.intersections.sort(new Comparator<Intersection>() {
            @Override
            public int compare(Intersection o1, Intersection o2) {
                return o1.getIdentifiers().compareTo(o2.getIdentifiers());
            }
        });
    }

    /**
     * merge list of tunnels to an existing map
     */
    public static void mergeTunnels(RobotMap map, List<Tunnel> tunnels){

        for (int i = 0; i < tunnels.size(); i++) {
            Tunnel tunnel=tunnels.get(i);
            tunnel.setSerialIdentifiers(tunnel.getStart().getIdentifiers()+"-"+tunnel.getEnd().getIdentifiers());
        }

        for (int i = 0; i < tunnels.size(); i++) {
            Tunnel tunnel = tunnels.get(i);
            if(!map.tunnels.contains(tunnel)){
                map.addTunnel(tunnel);
            }else {
                Tunnel repeat = tunnels.get(tunnels.indexOf(tunnel));
                double newHeight;
                if (tunnel.getSerialIdentifiers().compareTo(repeat.getSerialIdentifiers())<0){
                    newHeight = tunnel.getCeilingHeight();
                }else {
                    newHeight = repeat.getCeilingHeight();
                }

                double newLength;
                if (tunnel.getSerialIdentifiers().compareTo(repeat.getSerialIdentifiers())<0){
                    newLength = tunnel.getLength();
                }else {
                    newLength = repeat.getLength();
                }

                repeat.setLength(newLength);
                repeat.setCeilingHeight(newHeight);
                tunnel.setLength(newLength);
                tunnel.setCeilingHeight(newHeight);
            }
        }
        map.tunnels.sort(new Comparator<Tunnel>() {
            @Override
            public int compare(Tunnel o1, Tunnel o2) {
                int val=o1.getSerialIdentifiers().compareTo(o2.getSerialIdentifiers());
                if (val<0){
                    return -1;
                }else if (val==0){
                    val = Double.compare(o1.getLength(),o2.getLength());
                    if (val!=0){
                        return val;
                    }else {
                        val = Double.compare(o1.getCeilingHeight(),o2.getCeilingHeight());
                        if (val!=0) {
                            return val;
                        }else {
                            val = Integer.compare(Color.indexOf(o1.getFloorColor()),Color.indexOf(o2.getFloorColor()));
                            if (val!=0) {
                                return val;
                            }else {
                                val = Integer.compare(Color.indexOf(o1.getWallColor()),Color.indexOf(o2.getWallColor()));
                                if (val!=0) {
                                    return val;
                                }else {
                                    return Integer.compare(Color.indexOf(o1.getCeilingColor()),Color.indexOf(o2.getCeilingColor()));
                                }
                            }
                        }
                    }
                }else {
                    return 1;
                }
            }
        });
    }

    /**
     * merge two maps
     */
    public static RobotMap merge(RobotMap m1,RobotMap m2){
        RobotMap robotMap = new RobotMap();
        mergeIntersections(robotMap,m1.intersections);
        mergeIntersections(robotMap,m2.intersections);


        mergeTunnels(robotMap,m1.tunnels);
        mergeTunnels(robotMap,m2.tunnels);
        return robotMap;
    }
}
