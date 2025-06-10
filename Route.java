import java.util.ArrayList;

public class Route {
    private ArrayList<City> routeCities;
    private double totalDistance;

    //DISCUSS Abnormal task at 4 (private Cities)
    public Route(){
        this.routeCities = new ArrayList<>();
        this.totalDistance = 0;
    }

    //Copy Constructor
    public Route(Route other){
        this.routeCities = new ArrayList<>(other.routeCities);
        this.totalDistance = other.totalDistance;
    }

    //FIXME getDistance
    public void appendCity(City city, Connection connection){
        routeCities.add(city);
        if (connection != null) totalDistance += connection.getDistance();
    }

    public boolean containsCity(City city){
        return routeCities.contains(city);
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < routeCities.size(); i++) {
            sb.append(routeCities.get(i).getName());
            if (i < routeCities.size() - 1) {
                sb.append(" - ");
            }
        }
        sb.append("; Distanz: ").append((int) totalDistance);
        return sb.toString();
    }

    private static void addAllRoutes(ArrayList<Route> allPossibleRoutes,
                                     Route currentRoute,
                                     City currentCity,
                                     City destination,
                                     Connection connection) {
        currentRoute.appendCity(currentCity, connection);
        // Checks if the destination has been reached, otherwise continue the loop
        if (currentCity.equals(destination)) {
            allPossibleRoutes.add(currentRoute);
            return;
        }

        //DISCUSS add explaination

        for (Connection conn : currentCity.getConnections()) {
            City next = conn.getOtherCity(currentCity);
            if (!currentRoute.containsCity(next)) {
                Route continued = new Route(currentRoute);
                addAllRoutes(allPossibleRoutes, continued, next, destination, conn);
            }
        }
    }


    public static Route getShortestRoute(City origin, City destination) {
        ArrayList<Route> allPossible = new ArrayList<>();
        addAllRoutes(allPossible, new Route(), origin, destination, null);


        // sort by distance
        ArrayList<Route> sorted = new ArrayList<>(allPossible);
        sorted.sort((r1, r2) -> Double.compare(r1.totalDistance, r2.totalDistance));


        // print all routes
        for (Route r : sorted) {
            System.out.println(r);
        }
        return sorted.get(0);
    }
}
