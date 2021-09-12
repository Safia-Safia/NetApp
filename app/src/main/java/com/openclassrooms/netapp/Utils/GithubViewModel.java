package com.openclassrooms.netapp.Utils;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.openclassrooms.netapp.Models.GithubUser;

import java.util.List;
import java.util.concurrent.Executor;

public class GithubViewModel extends ViewModel {
    GithubRepository repository;

    // CONSTRUCTOR
    public GithubViewModel(GithubRepository githubRepository) {
        this.repository = githubRepository;
    }

    public LiveData<List<GithubUser>> fetchUserFollowing( String username){
        return repository.getUsers(username);
    }
}
