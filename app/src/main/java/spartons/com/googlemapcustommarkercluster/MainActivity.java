package spartons.com.googlemapcustommarkercluster;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import java.util.Arrays;
import java.util.List;

import spartons.com.googlemapcustommarkercluster.clusterRenderer.MarkerClusterRenderer;
import spartons.com.googlemapcustommarkercluster.data.User;
import spartons.com.googlemapcustommarkercluster.util.GoogleMapHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_main);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(googleMap -> {
            GoogleMapHelper.defaultMapSettings(googleMap);
            setUpClusterManager(googleMap);
        });
    }

    private void setUpClusterManager(GoogleMap googleMap) {
        ClusterManager<User> clusterManager = new ClusterManager<>(this, googleMap);

        MarkerClusterRenderer markerClusterRenderer = new MarkerClusterRenderer(this, googleMap, clusterManager);

        clusterManager.setRenderer(markerClusterRenderer);

        List<User> items = getItems();

        clusterManager.addItems(items);

        clusterManager.cluster();
    }

    private List<User> getItems() {
        return Arrays.asList(
                new User("AhsenSaeed1", new LatLng(-31.563910, 147.154312), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed2", new LatLng(-33.718234, 150.363181), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed3", new LatLng(-33.727111, 150.371124), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed4", new LatLng(-33.848588, 151.209834), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed5", new LatLng(-33.851702, 151.216968), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed6", new LatLng(-34.671264, 150.863657), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed7", new LatLng(-35.304724, 148.662905), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed8", new LatLng(-36.817685, 175.699196), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed9", new LatLng(-36.828611, 175.790222), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed10", new LatLng(-37.750000, 145.116667), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed11", new LatLng(-37.759859, 145.128708), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed12", new LatLng(-37.765015, 145.133858), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed13", new LatLng(-37.770104, 145.143299), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed14", new LatLng(-37.773700, 145.145187), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed15", new LatLng(-37.774785, 145.137978), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed16", new LatLng(-37.819616, 144.968119), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed17", new LatLng(-38.330766, 144.695692), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed18", new LatLng(-39.927193, 175.053218), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed19", new LatLng(-41.330162, 174.865694), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed20", new LatLng(-42.734358, 147.439506), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed21", new LatLng(-42.734358, 147.501315), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed22", new LatLng(-42.735258, 147.438000), "https://www.ahsensaeed.com"),
                new User("AhsenSaeed23", new LatLng(-43.999792, 170.463352), "https://www.ahsensaeed.com")
        );
    }
}
