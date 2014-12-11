package quickutils.core.categories;

import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;

import quickutils.core.QuickUtils;
import quickutils.core.models.LocationModel;

/**
 * Created by nunofeliciano on 12/10/14.
 */
public class location {

    /**
     * Gets the location by Coordinates
     *
     * @param latitude
     * @param longitude
     * @return Location model
     */
    public static LocationModel getLocationByCoordinates(Double latitude, Double longitude ){
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(QuickUtils.getContext());
            addresses = geocoder.getFromLocation(latitude, longitude, 1);

            LocationModel locationModel = new LocationModel();
            locationModel.latitude = latitude;
            locationModel.longitude = longitude;
            try{
                locationModel.address = addresses.get(0).getAddressLine(0);
            }catch(Exception ex){
                QuickUtils.log.e("empty address");
            }
            try{
                locationModel.city = addresses.get(0).getAddressLine(1);
            }catch(Exception ex){
                QuickUtils.log.e("empty city");
            }
            try{
                locationModel.country = addresses.get(0).getAddressLine(2);
            }catch(Exception ex){
                QuickUtils.log.e("empty country");
            }

            return locationModel;
        } catch (IOException e) {
            QuickUtils.log.e("empty location");
            return new LocationModel();
        }
    }

}
