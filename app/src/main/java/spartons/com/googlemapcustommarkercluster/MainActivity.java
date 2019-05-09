package spartons.com.googlemapcustommarkercluster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import java.util.Arrays;
import java.util.List;

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
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                GoogleMapHelper.defaultMapSettings(googleMap);
                setUpClusterManager(googleMap);
            }
        });
    }

    private void setUpClusterManager(GoogleMap googleMap) {
        ClusterManager<User> clusterManager = new ClusterManager<>(this, googleMap);
        googleMap.setOnCameraIdleListener(clusterManager);
        List<User> items = getItems();
        clusterManager.addItems(items);
        clusterManager.cluster();
    }

    private List<User> getItems() {
        return Arrays.asList(
                new User("CodingInfinite1", new LatLng(-31.563910, 147.154312)),
                new User("CodingInfinite2", new LatLng(-33.718234, 150.363181)),
                new User("CodingInfinite3", new LatLng(-33.727111, 150.371124)),
                new User("CodingInfinite4", new LatLng(-33.848588, 151.209834)),
                new User("CodingInfinite5", new LatLng(-33.851702, 151.216968)),
                new User("CodingInfinite6", new LatLng(-34.671264, 150.863657)),
                new User("CodingInfinite7", new LatLng(-35.304724, 148.662905)),
                new User("CodingInfinite8", new LatLng(-36.817685, 175.699196)),
                new User("CodingInfinite9", new LatLng(-36.828611, 175.790222)),
                new User("CodingInfinite10", new LatLng(-37.750000, 145.116667)),
                new User("CodingInfinite11", new LatLng(-37.759859, 145.128708)),
                new User("CodingInfinite12", new LatLng(-37.765015, 145.133858)),
                new User("CodingInfinite13", new LatLng(-37.770104, 145.143299)),
                new User("CodingInfinite14", new LatLng(-37.773700, 145.145187)),
                new User("CodingInfinite15", new LatLng(-37.774785, 145.137978)),
                new User("CodingInfinite16", new LatLng(-37.819616, 144.968119)),
                new User("CodingInfinite17", new LatLng(-38.330766, 144.695692)),
                new User("CodingInfinite18", new LatLng(-39.927193, 175.053218)),
                new User("CodingInfinite19", new LatLng(-41.330162, 174.865694)),
                new User("CodingInfinite20", new LatLng(-42.734358, 147.439506)),
                new User("CodingInfinite21", new LatLng(-42.734358, 147.501315)),
                new User("CodingInfinite22", new LatLng(-42.735258, 147.438000)),
                new User("CodingInfinite23", new LatLng(-43.999792, 170.463352))
        );
    }
}
