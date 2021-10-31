package com.example.uidaiaddressupdate.landlord;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.database.LandlordTransactions;
import com.example.uidaiaddressupdate.database.LandlordTransactionsDao;
import com.example.uidaiaddressupdate.database.TransactionDatabase;

import java.util.ArrayList;
import java.util.List;

public class LandlordRequestsList extends Fragment implements LandlordRequests {


    private View view;
    private RecyclerView landlordRequestsRecyclerView;
    private List<SingleLandlordRequestModel> singleLandlordRequestModelList;
    private LandlordRequestListAdapter adapter;
    private TransactionDatabase db;
    private LandlordTransactionsDao landlordTransactionsDao;
    private TextView no_address_request_text;

    public LandlordRequestsList() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_landlord_requests_list, container, false);
        landlordRequestsRecyclerView  = (RecyclerView) view.findViewById(R.id.landlord_request_list);
        singleLandlordRequestModelList = new ArrayList<>();
        //loadData();
        no_address_request_text = (TextView)view.findViewById(R.id.no_address_request_text);
        no_address_request_text.setVisibility(View.GONE);

        db = TransactionDatabase.getInstance(getContext());
        landlordTransactionsDao = db.landlordTransactionsDao();

        //AddDummyDataToDatabase();

        List<LandlordTransactions> landlordTransactionsList = landlordTransactionsDao.getTransactionList();
        //Toast.makeText(getContext(), "list size is " + landlordTransactionsList.size(), Toast.LENGTH_SHORT).show();
        if(landlordTransactionsList.size() == 0){
            no_address_request_text.setVisibility(View.VISIBLE);
        }else{
            no_address_request_text.setVisibility(View.GONE);
        }
        adapter = new LandlordRequestListAdapter(landlordTransactionsList,this);

        landlordRequestsRecyclerView.setHasFixedSize(true);
        landlordRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        landlordRequestsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void GotToCaptchaPage(String transactionId, String receiverShareCode) {
        //Move to Captcha Page
        Log.d("Mohan","Request Has approved");
//        Toast.makeText(getContext(), "Request has approved", Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_TRANSACTION_ID,transactionId);
        bundle.putString(Constants.KEY_RECEIVER_SHARECODE_ID,receiverShareCode);
        Navigation.findNavController(view).navigate(R.id.action_landlordRequestsList_to_landlordSingleRequests,bundle);
    }

    @Override
    public void HandleRequestDeclined(String transactionid, String receiverShareCode) {
        //Request has declined
        Toast.makeText(getContext(), "Request has declined", Toast.LENGTH_SHORT).show();
        landlordTransactionsDao.deleteTransaction(transactionid);
//        adapter.notifyDataSetChanged();
        updateFragment();
    }

    private void updateFragment(){
        List<LandlordTransactions> landlordTransactionsList = landlordTransactionsDao.getTransactionList();
        if(landlordTransactionsList.size() == 0){
            no_address_request_text.setVisibility(View.VISIBLE);
        }else{
            no_address_request_text.setVisibility(View.GONE);
        }

        adapter = new LandlordRequestListAdapter(landlordTransactionsList,this);

//        landlordRequestsRecyclerView.setHasFixedSize(true);
//        landlordRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        landlordRequestsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }




}