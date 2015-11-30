package travelling.candido.com.travellingadvice.datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import travelling.candido.com.travellingadvice.datastructure.objects.ApplicationMarker;

/**
 * Created by joxer on 30/11/15.
 */
public class ApplicationMarkerCollection {

    public List<ApplicationMarker> serverMarkers;
    public List<ApplicationMarker> localMarkers;


    public ApplicationMarkerCollection(List<ApplicationMarker> server){
        this.serverMarkers = new ArrayList<>(server);
        this.localMarkers = new LinkedList<>();
    }

    public void addLocalMarker(ApplicationMarker localMarkerTmp) {
        this.localMarkers.add(localMarkerTmp);
    }
}
