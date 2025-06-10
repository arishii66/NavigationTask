public class Connection {
    private City city1;
    private City city2;
    private final double distance;

    public Connection(City city1 , City city2){
        this.city1 = city1;
        this.city2 = city2;
        this.distance = getDistanceInKm(city1, city2);
    }

    //DISCUSS Explain this funky calculation

    double getDistanceInKm(City city1, City city2) {
        double lat1 = city1.getLatitude();
        double lon1 = city1.getLongitude();
        double lat2 = city2.getLatitude();
        double lon2 = city2.getLongitude();
        double earthRadiusKm = 6371;
        double dLat = degreesToRadians(lat2-lat1);
        double dLon = degreesToRadians(lon2-lon1);
        lat1 = degreesToRadians(lat1);
        lat2 = degreesToRadians(lat2);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) *
                Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadiusKm * c;
    }
    private double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    // FIXME FIXED
    public City getOtherCity(City thisOne) {
        if (thisOne.equals(city1)) {
            return city2;
        } else if (thisOne.equals(city2)) {
            return city1;
        }
        return null;
    }


    public double getDistance() {
        return distance;
    }

}
