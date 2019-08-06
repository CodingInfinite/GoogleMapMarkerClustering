package spartons.com.googlemapcustommarkercluster.clusterRenderer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import spartons.com.googlemapcustommarkercluster.R;
import spartons.com.googlemapcustommarkercluster.data.User;

@SuppressLint("InflateParams")
public class MarkerClusterRenderer extends DefaultClusterRenderer<User> implements ClusterManager.OnClusterClickListener<User>, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap googleMap;
    private LayoutInflater layoutInflater;
    private final IconGenerator clusterIconGenerator;
    private final View clusterItemView;

    public MarkerClusterRenderer(@NonNull Context context, GoogleMap map, ClusterManager<User> clusterManager) {
        super(context, map, clusterManager);

        this.googleMap = map;

        layoutInflater = LayoutInflater.from(context);

        clusterItemView = layoutInflater.inflate(R.layout.single_cluster_marker_view, null);

        clusterIconGenerator = new IconGenerator(context);
        Drawable drawable = ContextCompat.getDrawable(context, android.R.color.transparent);
        clusterIconGenerator.setBackground(drawable);
        clusterIconGenerator.setContentView(clusterItemView);

        clusterManager.setOnClusterClickListener(this);

        googleMap.setInfoWindowAdapter(clusterManager.getMarkerManager());

        googleMap.setOnInfoWindowClickListener(this);

        clusterManager.getMarkerCollection().setOnInfoWindowAdapter(new MyCustomClusterItemInfoView());

        googleMap.setOnCameraIdleListener(clusterManager);

        googleMap.setOnMarkerClickListener(clusterManager);
    }

    @Override
    protected void onBeforeClusterItemRendered(User item, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        markerOptions.title(item.getTitle());
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<User> cluster, MarkerOptions markerOptions) {
        TextView singleClusterMarkerSizeTextView = clusterItemView.findViewById(R.id.singleClusterMarkerSizeTextView);
        singleClusterMarkerSizeTextView.setText(String.valueOf(cluster.getSize()));
        Bitmap icon = clusterIconGenerator.makeIcon();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }

    @Override
    protected void onClusterItemRendered(User clusterItem, Marker marker) {
        marker.setTag(clusterItem);
    }

    @Override
    public boolean onClusterClick(Cluster<User> cluster) {
        if (cluster == null) return false;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (User user : cluster.getItems())
            builder.include(user.getPosition());
        LatLngBounds bounds = builder.build();
        try {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Context context = clusterItemView.getContext();
        User user = (User) marker.getTag(); //  handle the clicked marker object
        if (context != null && user != null)
            Toast.makeText(context, user.getTitle(), Toast.LENGTH_SHORT).show();
    }

    private class MyCustomClusterItemInfoView implements GoogleMap.InfoWindowAdapter {

        private final View clusterItemView;

        MyCustomClusterItemInfoView() {
            clusterItemView = layoutInflater.inflate(R.layout.marker_info_window, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            User user = (User) marker.getTag();
            if (user == null) return clusterItemView;
            TextView itemNameTextView = clusterItemView.findViewById(R.id.itemNameTextView);
            TextView itemAddressTextView = clusterItemView.findViewById(R.id.itemAddressTextView);
            itemNameTextView.setText(marker.getTitle());
            itemAddressTextView.setText(user.getAddress());
            return clusterItemView;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }
}
