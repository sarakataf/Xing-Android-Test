package sara.kataf.myapplication;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {

    @GET("users")
    Call<List<User>> getUsers();

    @Headers("Accept: application/vnd.github.v3.text-match+json")
    @GET("search/users")
    Call<SearchResults> searchForUser(@Query("q") String searchCriteria,
                                      @Query("sort") String sort,
                                      @Query("order") String order);



    //@GET("users")
    //Call<List<Post>> getPosts();

    //@GET("posts/{id}/comments")
    //Call<List<Comment>> getComments(@Path("id") int postId);

    /*@GET("users")
    Call<List<Post>> getPosts(
            @Query("id") Integer id,
            @Query("_sort") String sort,
            @Query("_order") String order
            );*/
}
