package quickutils.core.models;

/**
 * Created by nunofeliciano on 12/10/14.
 */
public class LocationModel {
    public Double latitude;
    public Double longitude;
    public String address;
    public String city;
    public String country;

    @Override
    public String toString() {
        return address + ", " + city + ", " + country;
    }
}
