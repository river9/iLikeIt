package com.river.ilikeit.main.photo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.river.ilikeit.Constants;
import com.river.ilikeit.R;
import com.river.ilikeit.main.dummy.DummyContent;
import com.river.ilikeit.photo.PhotoApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PhotosFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();

    private RecyclerView mRecyclerView;
    private PhotosFragmentAdapter mAdapter;
    private List<PhotoInfo> pins = new ArrayList<PhotoInfo>();

    public static PhotosFragment newInstance() {
        return new PhotosFragment();
    }

    public PhotosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Utility.requestPin();
//                } catch (URISyntaxException | IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        getPins();



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
//
//    private class getDataTask extends AsyncTask<Void, Void, List<PhotoInfo>> {
//
//        @Override
//        protected List<PhotoInfo> doInBackground(Void... params) {
//            try {
//                return Utility.getPhotos();
//            } catch (IOException | JSONException | URISyntaxException e) {
//                e.printStackTrace();
//            }
//            return new ArrayList<>();
//        }
//
//        @Override
//        protected void onPostExecute(List<PhotoInfo> photoInfos) {
//            super.onPostExecute(photoInfos);
//            mAdapter = new PhotosFragmentAdapter(getActivity().getApplicationContext(), photoInfos);
//            mRecyclerView.setAdapter(mAdapter);
//        }
//    }

    private void getPins(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this.getContext(),"Fetching Data","Please wait...",false,false);

        //Creating a rest adapter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_BOARD)
                .build();

        //Creating an object of our api interface
        PhotoApi api = retrofit.create(PhotoApi.class);
        Call<List<PhotoInfo>> call = api.loadPins();

        //Defining the method
        call.enqueue(new Callback<List<PhotoInfo>>() {
            @Override
            public void onResponse(Call<List<PhotoInfo>> call, Response<List<PhotoInfo>> response) {
                Log.d(TAG, "response: " + response.message());
            }

            @Override
            public void onFailure(Call<List<PhotoInfo>> call, Throwable t) {
                Log.d(TAG, "Throwable: " + t.getMessage());
            }
        });
    }

    private void showList() {

    }
}
