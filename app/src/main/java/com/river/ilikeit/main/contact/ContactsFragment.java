package com.river.ilikeit.main.contact;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.river.ilikeit.AppPreferences;
import com.river.ilikeit.Constants;
import com.river.ilikeit.R;
import com.river.ilikeit.main.BroadcastHelper;

import org.jivesoftware.smack.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();

    private List<ContactInfo> contactList = new ArrayList<>();
    private ContactsFragmentAdapter mAdapter;
    private OnFragmentInteractionListener mListener;

    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    public ContactsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ContactsFragmentAdapter(contactList);
        BroadcastHelper.getInstance().registerReceiver(this.getActivity(),
                receiverRosterLoaded, Constants.BRC_ROSTER_LOADED, null);
        new TaskRefreshContactList().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        // Set the adapter
        ListView listView = (ListView) view.findViewById(R.id.lvContacts);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onContactFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BroadcastHelper.getInstance().unregisterReceiver(this.getActivity(),
                receiverRosterLoaded);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onContactFragmentInteraction(Uri uri);
    }
    
    private BroadcastReceiver receiverRosterLoaded = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            new TaskRefreshContactList().execute();
        }
    };

    private class TaskRefreshContactList extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String strContacts = AppPreferences.getInstance(ContactsFragment.this.getActivity()).getContacts();
            Log.d(TAG, "TaskRefreshContactList: " + strContacts);
            if (StringUtils.isNotEmpty(strContacts)) {
                try {
                    JSONArray array = new JSONArray(strContacts);
                    contactList.clear();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = (JSONObject) array.get(i);
                        ContactInfo contact = new ContactInfo(obj.getString(Constants.JID), obj.getString(Constants.NAME));
                        contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Log.d(TAG, "TaskRefreshContactList contactList.size: " + contactList.size());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mAdapter.notifyDataSetChanged();
        }
    }
}
