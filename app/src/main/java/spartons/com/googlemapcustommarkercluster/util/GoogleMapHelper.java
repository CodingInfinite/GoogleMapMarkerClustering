package spartons.com.googlemapcustommarkercluster.util;

import com.google.android.gms.maps.GoogleMap;

import org.jetbrains.annotations.NotNull;

public class GoogleMapHelper {

    public static void defaultMapSettings(@NotNull GoogleMap googleMap) {
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(false);
        googleMap.setBuildingsEnabled(true);
    }
}
