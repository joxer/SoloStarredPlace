package travelling.candido.com.travellingadvice.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.materialcontentoverflow.MaterialContentOverflow;

import travelling.candido.com.travellingadvice.PlaceholderFragment;
import travelling.candido.com.travellingadvice.R;
import travelling.candido.com.travellingadvice.datastructure.ApplicationMarkerCollection;
import travelling.candido.com.travellingadvice.datastructure.objects.ApplicationMarker;

public class InformationMap extends PlaceholderFragment implements GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, View.OnClickListener {

    MapView mapView;

    GoogleMap googleMap;
    private Marker justCreatedMarker;
    private int x,y;
    private int xMarker,yMarker;
    private View parentView;
    private ApplicationMarker localMarkerTmp;
    private ApplicationMarkerCollection applicationMarkers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        this.parentView = inflater.inflate(R.layout.fragment_information_map, container, false);

        mapView = (MapView) parentView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        MapsInitializer.initialize(getActivity().getApplicationContext());
        if(mapView!=null)
        {
            googleMap = mapView.getMap();
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(false);
            googleMap.setOnMapLongClickListener(this);
            googleMap.setOnMapClickListener(this);

        }

        attachMenuAction();

        return parentView;
    }

    private void attachMenuAction() {

        Button btnSave = (Button) parentView.findViewById(R.id.buttonSaveContentOverflow);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onResume()
    {
        mapView.onResume();

        super.onResume();
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();

        mapView.onDestroy();
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();

        mapView.onLowMemory();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    @Override
    public void onMapLongClick(LatLng latLng) {
        if(justCreatedMarker != null) {
            justCreatedMarker.remove();
        }

        justCreatedMarker = googleMap.addMarker(new MarkerOptions().position(latLng).draggable(true).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if(marker.equals(justCreatedMarker)){
            RelativeLayout ll = (RelativeLayout) parentView.findViewById(R.id.bottom_info_map);
            ll.setVisibility(View.VISIBLE);

            localMarkerTmp = new ApplicationMarker();

        }
        else{

        }
        return true;
    }


    @Override
    public void onMapClick(LatLng latLng) {
        if(justCreatedMarker != null) {
            justCreatedMarker.remove();
            RelativeLayout ll = (RelativeLayout) parentView.findViewById(R.id.bottom_info_map);
            ll.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        MaterialContentOverflow mco = (MaterialContentOverflow) parentView.findViewById(R.id.contentOverflow);

        EditText nameEdit = ((EditText)mco.getContentFrame().findViewById(R.id.editTextName));
        EditText descriptionEdit = ((EditText)mco.getContentFrame().findViewById(R.id.editTextDescription));
        String title = nameEdit.getText().toString();
        String description = descriptionEdit.getText().toString();

        localMarkerTmp.title = title;
        localMarkerTmp.description = description;

        justCreatedMarker = null;

        localMarkerTmp.save();
        applicationMarkers.addLocalMarker(localMarkerTmp);

    }

}
