package com.openclassrooms.netapp.Injection;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.openclassrooms.netapp.Utils.GithubRepository;
import com.openclassrooms.netapp.Utils.GithubViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final GithubRepository mGithubRepository;

    public ViewModelFactory(GithubRepository githubRepository) {
        this.mGithubRepository = githubRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GithubViewModel.class)) {
            return (T) new GithubViewModel(mGithubRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
