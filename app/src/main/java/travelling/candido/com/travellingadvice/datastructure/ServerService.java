package travelling.candido.com.travellingadvice.datastructure;

import retrofit.Retrofit;

/**
 * Created by joxer on 30/11/15.
 */
public class ServerService {
    private Retrofit retrofit;
    private ServerServiceInterface service;
    private static ServerService instance;

    private ServerService(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.candido.io")
                .build();

        service = retrofit.create(ServerServiceInterface.class);
    }

    public synchronized  static ServerService getService() {

        if(instance == null) {
            instance = new ServerService();
        }
        return instance;
    }
}
