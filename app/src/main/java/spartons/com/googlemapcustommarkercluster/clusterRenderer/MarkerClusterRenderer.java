package spartons.com.googlemapcustommarkercluster.clusterRenderer;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import spartons.com.googlemapcustommarkercluster.R;
import spartons.com.googlemapcustommarkercluster.data.User;

public class MarkerClusterRenderer extends DefaultClusterRenderer<User> {

    private static final int MARKER_DIMENSION = 48;

    private final IconGenerator iconGenerator;
    private final ImageView markerImageView;

    public MarkerClusterRenderer(Context context, GoogleMap map, ClusterManager<User> clusterManager) {
        super(context, map, clusterManager);
        iconGenerator = new IconGenerator(context);
        markerImageView = new ImageView(context);
        markerImageView.setLayoutParams(new ViewGroup.LayoutParams(MARKER_DIMENSION, MARKER_DIMENSION));
        iconGenerator.setContentView(markerImageView);
    }

    @Override
    protected void onBeforeClusterItemRendered(User item, MarkerOptions markerOptions) {
        markerImageView.setImageResource(R.drawable.location_vector_icon);
        Bitmap icon = iconGenerator.makeIcon();
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        markerOptions.title(item.getTitle());
    }
}
