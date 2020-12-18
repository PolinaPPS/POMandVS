package ru.startandroid.myapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.startandroid.myapplication.model.Response;

public interface FlickrAPI {
    @GET("services/rest/?method=flickr.photos.getRecent&api_key=e5de63e83f6d27ab8b4b582edf14e0a9&extras=url_s&per_page=20&page=1&format=json&nojsoncallback=1")
    Call<Response> getRecent();
    @GET("services/rest/?method=flickr.photos.search&api_key=e5de63e83f6d27ab8b4b582edf14e0a9&extras=url_s&per_page=20&page=1&format=json&nojsoncallback=1")
    Call<Response> getSearchPhotos(@Query("text") String keyWord);

}
