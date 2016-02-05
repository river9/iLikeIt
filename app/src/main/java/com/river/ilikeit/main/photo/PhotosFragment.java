package com.river.ilikeit.main.photo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.river.ilikeit.R;
import com.river.ilikeit.Utility;
import com.river.ilikeit.main.dummy.DummyContent;

import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PhotosFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private PhotosFragmentAdapter mAdapter;


    public static PhotosFragment newInstance() {
        return new PhotosFragment();
    }

    public PhotosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Utility.requestPin();
                } catch (URISyntaxException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        mAdapter = new ArrayAdapter<>(getActivity(), R.layout.photo_item, R.id.tvContent, DummyContent.ITEMS);
        mAdapter = new PhotosFragmentAdapter(getActivity().getApplicationContext(), DummyContent.ITEMS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos_recycler, container, false);

        // Set the adapter
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvPhotos);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(2, 1);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
//        new getDataTask().execute();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

    private class getDataTask extends AsyncTask<Void, Void, List<PhotoInfo>> {

        @Override
        protected List<PhotoInfo> doInBackground(Void... params) {
            try {
                return Utility.getPhotos();
            } catch (IOException | JSONException | URISyntaxException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<PhotoInfo> photoInfos) {
            super.onPostExecute(photoInfos);
            mAdapter = new PhotosFragmentAdapter(getActivity().getApplicationContext(), photoInfos);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
