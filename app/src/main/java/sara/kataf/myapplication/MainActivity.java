package sara.kataf.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //getUser();

        searchForUser();
    }

    /*private void getUser() {

        Call<List<Users>> call = jsonPlaceHolderApi.searchForUser("tom");//parameters for input

        //cant use execute because its already automatically excuted from Retrofit
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code" + response.code());
                    return;
                }

                List<User> Users = response.body();

                for (User users : Users) {
                    String content = "";

                    content += "Avatar:" + users.getAvatar_url() + "\n";
                    content += "Login Name:" + users.getLogin() + "\n";
                    content += "Number of Repos:" + users.getPublic_repos() + "\n";
                    content += "Joined Date:" + users.getCreated_at() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });
    }*/

    private void searchForUser() {
            //the correct api is q=location:hamburg+language:java
        Call<SearchResults> call = jsonPlaceHolderApi.searchForUser("location:hamburg","id","desc");//parameters for input


        //cant use execute because its already automatically excuted from Retrofit
        call.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code" + response.code());
                    return;
                }

                List<User> users = response.body().getItems();

                for (User user : users) {
                    String content = "";

                    content += "Avatar:" + user.getAvatar_url() + "\n";
                    content += "Login Name:" + user.getLogin() + "\n";
                    content += "Number of Repos:" + user.getPublic_repos() + "\n";
                    content += "Joined Date:" + user.getCreated_at() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {

                textViewResult.setText(t.getMessage());
            }
        });
    }
}
