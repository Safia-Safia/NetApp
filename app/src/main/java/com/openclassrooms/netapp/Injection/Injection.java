package com.openclassrooms.netapp.Injection;

import android.content.Context;
import com.openclassrooms.netapp.Utils.GithubRepository;

public class Injection {

    public static GithubRepository provideProjectDataSource(Context context) {
        //NetApDatabase database = NetAppDatabase.getInstance(context);
        return new GithubRepository();
    }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        GithubRepository githubSourceItem = provideProjectDataSource(context);
        return new ViewModelFactory(githubSourceItem);
    }
}