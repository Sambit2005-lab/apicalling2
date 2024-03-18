package com.example.apicalling2;

import static com.example.apicalling2.R.*;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    apiinterface apiinterface;
    RecyclerView recyclerView;
    PostAdapter postadapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiinterface=RetrofitInstance.getRetrofit().create(apiinterface.class);
        recyclerView= findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button buttonApiCall = findViewById(R.id.buttonApiCall);
        buttonApiCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeApiCall();
            }
        });
    }



        private void makeApiCall() {
        apiinterface.getposts().enqueue(new Callback<List<postpojo>>() {
            @Override
            public void onResponse(Call<List<postpojo>> call, Response<List<postpojo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<postpojo> postList = response.body();
                    postadapter = new PostAdapter(postList);
                    recyclerView.setAdapter(postadapter);}
                else {
                    Toast.makeText(MainActivity.this, "list is empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<postpojo>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    }}