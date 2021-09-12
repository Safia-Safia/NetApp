package com.openclassrooms.netapp.Utils;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.Nullable;
import android.util.Log;

import com.openclassrooms.netapp.Models.GithubUser;


import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubRepository {


    public LiveData<List<GithubUser>> getUsers( String username){
        final MutableLiveData<List<GithubUser>> result = new MutableLiveData<>();
        Log.e("getUsers", "1");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        // Get a Retrofit instance and the related endpoints
        GithubService gitHubService = retrofit.create(GithubService.class);

        // Create the call on Github API
        Call<List<GithubUser>> call = gitHubService.getFollowing(username);
        // Start the call
        call.enqueue(new Callback<List<GithubUser>>() {

            @Override
            public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                result.postValue(response.body());
                Log.e("getUsers", "2");

            }

            @Override
            public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                Log.e("getUsers", "3" + t.getMessage());
            }
        });
        return result;
    }
}