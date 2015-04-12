package rem.proxie.map_util;   // Change rem.proxie to your package name

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import rem.proxie.activities.Map;
import rem.proxie.R;

public class LocationServer implements android.location.LocationListener {

    private Map activity = null;
    private Context context = null;
    private LocationManager locMgr = null;
    private GoogleMap map = null;

    public LocationServer(Map activity) {
        this.activity = activity;
        context = activity.getApplicationContext();

        locMgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        setMap();

        Toast.makeText(context, "LocationServer", Toast.LENGTH_SHORT).show();
    }

    private void setMap() {
        MapFragment mapFragment = (MapFragment) activity.getFragmentManager().findFragmentById(R.id.googleMapFragment);

        if (mapFragment != null) {
            map = mapFragment.getMap();
            if (map != null) {
                map.setMyLocationEnabled(true);
            }
        }
    }

    public void start() {
        locMgr.requestLocationUpdates(5000, 0, new Criteria(), this, null);
    }

    public void stop() {
        locMgr.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(context, "Location Update", Toast.LENGTH_LONG).show();

        LatLng cameraLocation = new LatLng(location.getLatitude(), location.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLng(cameraLocation));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
