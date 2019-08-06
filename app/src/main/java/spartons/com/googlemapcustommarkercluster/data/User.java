package spartons.com.googlemapcustommarkercluster.data;


import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class User implements ClusterItem {

    private final String username;
    private final String address;
    private final LatLng latLng;

    public User(String username, LatLng latLng, String address) {
        this.username = username;
        this.latLng = latLng;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public LatLng getPosition() {
        return latLng;
    }

    @Override
    public String getTitle() {
        return username;
    }

    @Override
    public String getSnippet() {
        return "";
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", latLng=" + latLng +
                '}';
    }
}
