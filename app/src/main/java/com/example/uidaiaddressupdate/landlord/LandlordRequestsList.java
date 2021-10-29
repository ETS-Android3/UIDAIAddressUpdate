package com.example.uidaiaddressupdate.landlord;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        loadData();

        db = TransactionDatabase.getInstance(getContext());
        landlordTransactionsDao = db.landlordTransactionsDao();

        AddDummyDataToDatabase();

        List<LandlordTransactions> landlordTransactionsList = landlordTransactionsDao.getTransactionList();
        Toast.makeText(getContext(), "list size is " + landlordTransactionsList.size(), Toast.LENGTH_SHORT).show();

        adapter = new LandlordRequestListAdapter(landlordTransactionsList,this);

        landlordRequestsRecyclerView.setHasFixedSize(true);
        landlordRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        landlordRequestsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void GotToCaptchaPage(String transactionId) {
        //Move to Captcha Page
        Log.d("Mohan","Request Has approved");
        Toast.makeText(getContext(), "Request has approved", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.action_landlordRequestsList_to_landlordSingleRequests);
    }

    @Override
    public void HandleRequestDeclined(String transactionid) {
        //Request has declined
        Toast.makeText(getContext(), "Request has declined", Toast.LENGTH_SHORT).show();
    }

    private void loadData(){
        singleLandlordRequestModelList.add(new SingleLandlordRequestModel("Mohan","ABCD","9876543210","null","ABCD"));
        singleLandlordRequestModelList.add(new SingleLandlordRequestModel("Aman","ABCD","9876543210","null","ABCD"));
        singleLandlordRequestModelList.add(new SingleLandlordRequestModel("Pranav","ABCD","9876543210","null","ABCD"));
        singleLandlordRequestModelList.add(new SingleLandlordRequestModel("Sourav","ABCD","9876543210","null","ABCD"));
        singleLandlordRequestModelList.add(new SingleLandlordRequestModel("Pooja","ABCD","9876543210","null","ABCD"));
    }

    private void AddDummyDataToDatabase(){
        landlordTransactionsDao.insertTransaction(new LandlordTransactions("ABCD","Mohan Aman","9876543210","success","I am a good person","Share Code"));
        landlordTransactionsDao.insertTransaction(new LandlordTransactions("EFGH","Aman PRanav","9876543210","success","I am a good person","Share Code"));
    }

}