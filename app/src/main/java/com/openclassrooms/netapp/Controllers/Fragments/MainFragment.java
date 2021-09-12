package com.openclassrooms.netapp.Controllers.Fragments;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.netapp.Injection.Injection;
import com.openclassrooms.netapp.Injection.ViewModelFactory;
import com.openclassrooms.netapp.Models.GithubUser;
import com.openclassrooms.netapp.R;
import com.openclassrooms.netapp.Utils.GithubRepository;
import com.openclassrooms.netapp.Utils.GithubViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    // FOR DESIGN
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.fragment_main_textview) TextView textView;
    String username = "JakeWharton";
    public MainFragment() { }
    private ArrayList<GithubUser> githubUserList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        configureViewModel();

        return view;
    }

    // -----------------
    // ACTIONS
    // -----------------

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.fragment_main_button)
    public void submit(View view) {
        this.executeHttpRequestWithRetrofit();
        getUserList();
    }

    // ------------------------------
    //  HTTP REQUEST (Retrofit Way)
    // ------------------------------

    private GithubViewModel viewModel;
    public void configureViewModel() {
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getActivity());
        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(GithubViewModel.class);
    }

    private void executeHttpRequestWithRetrofit(){
        this.updateUIWhenStartingHTTPRequest();
    }

    private void getUserList(){
        this.viewModel.fetchUserFollowing(username).observe(this, new Observer<List<GithubUser>>() {
            @Override
            public void onChanged(List<GithubUser> githubUsers) {
               updateUIWithListOfUsers(githubUsers);
            }
        });
    }

    // ------------------
    //  UPDATE UI
    // ------------------

    @SuppressLint("SetTextI18n")
    private void updateUIWhenStartingHTTPRequest(){
        this.textView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        this.textView.setText(response);
    }

    private void updateUIWithListOfUsers(List<GithubUser> users){
        StringBuilder stringBuilder = new StringBuilder();
        for (GithubUser user : users){
            stringBuilder.append("-"+user.getLogin()+"\n");
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());
    }

}
