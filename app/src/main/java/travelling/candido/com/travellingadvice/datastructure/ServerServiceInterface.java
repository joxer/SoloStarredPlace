package travelling.candido.com.travellingadvice.datastructure;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import travelling.candido.com.travellingadvice.datastructure.objects.ApplicationMarker;

/**
 * Created by joxer on 30/11/15.
 */
public interface ServerServiceInterface {
    @POST("/save/marker")
    Call<ApplicationMarker> saveMarker(@Field("title") String first, @Field("description") String last);
}
