import java.util.HashMap;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Station {

    private String name; //地铁站名称

    public Station prev; //前一个站

    public Station next; //后一个站

    private Map<Station,LinkedHashSet<Station>> orderSetMap = new HashMap<Station,LinkedHashSet<Station>>();

    public Station (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashSet<Station> getAllPassedStations(Station station) {
        if(orderSetMap.get(station) == null){
            LinkedHashSet<Station> set = new LinkedHashSet<Station>();
            set.add(this);
            orderSetMap.put(station, set);
        }
        return orderSetMap.get(station);
    }

    public Map<Station, LinkedHashSet<Station>> getOrderSetMap() {
        return orderSetMap;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        } else if(obj instanceof Station){
            Station s = (Station) obj;
            if(s.getName().equals(this.getName())){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}