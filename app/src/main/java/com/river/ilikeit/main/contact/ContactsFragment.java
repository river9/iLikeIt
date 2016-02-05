package com.river.ilikeit.main.contact;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.river.ilikeit.R;
import com.river.ilikeit.chat.ChatServiceManager;

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
        getContactList();
        mAdapter = new ContactsFragmentAdapter(contactList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        // Set the adapter
        ListView listView = (ListView) view.findViewById(R.id.lvContacts);
        listView.setAdapter(mAdapter);
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

    public List<ContactInfo> getContactList() {
        Log.i(TAG, "Connecting to service");

        try {
            ChatServiceManager.getInstance(this.getActivity()).getRemoteService().getRosters();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        contactList = createContactList();
        return contactList;
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
        public void onContactFragmentInteraction(Uri uri);
    }

    private List<ContactInfo> createContactList() {
        List<ContactInfo> list = new ArrayList<>();
        list.add(new ContactInfo("Test 1", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 2", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 3", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 4", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 5", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 6", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 7", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 1", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 2", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 3", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 4", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 5", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 6", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 7", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 1", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 2", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 3", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 4", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 5", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 6", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 7", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 1", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 2", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 3", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 4", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 5", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 6", "eeeeeeeeeee", "ddddddddddddd"));
        list.add(new ContactInfo("Test 7", "eeeeeeeeeee", "ddddddddddddd"));
        return list;
    }
}
