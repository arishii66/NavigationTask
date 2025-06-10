import java.util.ArrayList;
import java.util.List;

public class City {

    private String name;
    private double latitude;
    private double longitude;
    private List<Connection> connections;

    public City (String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.connections = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + latitude + " " + longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void addConnection(City cityToConnect) {
        //check if the connection aint the same city
        if (this == cityToConnect) {
            System.err.println("Eine Stadt kann nicht mit sich selbst verknüpft werden.");
            return;
        }
        Connection conn = new Connection(this, cityToConnect);
        Connection reverse = new Connection(cityToConnect, this);
        this.connections.add(conn);
        cityToConnect.connections.add(reverse);
    }

    public List<Connection> getConnections() {
        return connections;
    }

    //FIXME

    public String getRouteTo(City destination){
        Route shortest = Route.getShortestRoute(this, destination);
        return shortest.toString();
    }
}
