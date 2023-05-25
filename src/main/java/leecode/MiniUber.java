package leecode;

import java.util.HashMap;
import java.util.Map;

public class MiniUber {
    private Map<Integer, ObjectPosition> canUseCars = null;
    private Map<Integer, ObjectPosition> needCarRiders = null;
    private Map<Integer, Trip> trips = null;

    public MiniUber() {
        // initialize your data structure here.
        canUseCars = new HashMap<>();
        needCarRiders = new HashMap<>();
        trips = new HashMap<>();
    }

    // @param driver_id an integer
    // @param lat, lng driver's location
    // return matched trip information if there have matched rider or null
    public Trip report(int driver_id, double lat, double lng) {
        // Write your code here
        Trip trip = trips.get(driver_id);
        if (trip != null) {
            return trip;
        }
        if (needCarRiders != null && needCarRiders.size() > 0) {
            double instance = Double.MAX_VALUE;
            int rider_id = 0;
            for (ObjectPosition p : needCarRiders.values()) {
                double d = Helper.get_distance(lat, lng, p.lat, p.lng);
                if (d < instance) {
                    instance = d;
                    rider_id = p.id;
                }
            }
            trip = new Trip(rider_id, lat, lng);
            trip.driver_id = driver_id;
            
            canUseCars.remove(rider_id);
            trips.put(driver_id, trip);
            return trip;
        } else {
            canUseCars.put(driver_id, new ObjectPosition(driver_id, lat, lng));
        }
        return null;
    }

    // @param rider_id an integer
    // @param lat, lng rider's location
    // return a trip
    public Trip request(int rider_id, double lat, double lng) {
        if (canUseCars != null && canUseCars.size() > 0) {
            double instance = Double.MAX_VALUE;
            int driver_id = 0;
            for (ObjectPosition c : canUseCars.values()) {
                double d = Helper.get_distance(lat, lng, c.lat, c.lng);
                if (d < instance) {
                    instance = d;
                    driver_id = c.id;
                }
            }
            Trip trip = new Trip(rider_id, lat, lng);
            trip.driver_id = driver_id;
            
            canUseCars.remove(driver_id);
            trips.put(driver_id, trip);
            return trip;
        } else {
            needCarRiders.put(rider_id, new ObjectPosition(rider_id, lat, lng));
        }
        return null;
    }
}


class ObjectPosition {
    public int id;
    public double lat, lng;

    public ObjectPosition(int id, double lat, double lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }
}


class Trip {
    public int id; // trip's id, primary key
    public int driver_id, rider_id; // foreign key
    public double lat, lng; // pick up location

    public Trip(int rider_id, double lat, double lng) {
        this.rider_id = rider_id;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Trip [id=" + id + ", driver_id=" + driver_id + ", rider_id=" + rider_id + ", lat="
                + lat + ", lng=" + lng + "]";
    }
}


class Helper {
    public static double get_distance(double lat1, double lng1, double lat2, double lng2) {
        // return distance between (lat1, lng1) and (lat2, lng2)
        return Math.sqrt(Math.pow((lat1 - lat2), 2) + Math.pow((lng1 - lng2), 2));
    }
}
