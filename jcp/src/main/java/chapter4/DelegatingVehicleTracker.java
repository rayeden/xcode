package chapter4;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Zetrov on 2016-12-25.
 */

/**
 * 没有任何显示同步，所有对状态的访问都由ConcurrentHashMap管理，且所有Map的键值都是不可变的
 */
public class DelegatingVehicleTracker {

    private final ConcurrentHashMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points){
        locations = new ConcurrentHashMap<String, Point>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations(){
        return unmodifiableMap;
    }

    public Point getLocation(String id){
        return locations.get(id);
    }

    public void setLocations(String id, int x, int y){
        if(locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException("invalid vehical name: " + id);
    }

}
