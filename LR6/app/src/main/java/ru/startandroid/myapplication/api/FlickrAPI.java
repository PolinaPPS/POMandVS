package ru.startandroid.myapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.startandroid.myapplication.model.Response;

public interface FlickrAPI {
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=aa9bc423877ea7b87ecd283cc1e907d7&extras=url_s&per_page=20&page=1&format=json&nojsoncallback=1")
    Call<Response> getRecent();
    @GET("services/rest/?method=flickr.photos.search&api_key=eb33ee1959aca46f8dbc2fa8a56f9f24&extras=url_s&per_page=10&page=1&format=json&nojsoncallback=1")
    Call<Response> getSearchPhotos(@Query("text") String keyWord);

}
