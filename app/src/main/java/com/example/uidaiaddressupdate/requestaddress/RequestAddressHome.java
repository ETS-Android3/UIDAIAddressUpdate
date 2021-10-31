package com.example.uidaiaddressupdate.requestaddress;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.database.RequesterTransactions;
import com.example.uidaiaddressupdate.database.RequesterTransactionsDao;
import com.example.uidaiaddressupdate.database.TransactionDatabase;
import com.example.uidaiaddressupdate.requestaddress.oldrequests.NavigateToRequestDetails;
import com.example.uidaiaddressupdate.requestaddress.oldrequests.RequestsAdapter;
import com.example.uidaiaddressupdate.requestaddress.oldrequests.SingleRequest;

import java.util.ArrayList;
import java.util.List;


public class RequestAddressHome extends Fragment implements NavigateToRequestDetails {


    private AppCompatButton createNewRequestButton;
    private RecyclerView OldRequestsRecyclerView;
    private List<SingleRequest> oldRequestsList;
    private RequestsAdapter requestsAdapter;
    private View view;
    private TransactionDatabase transactionDatabase;
    private RequesterTransactionsDao requesterTransactionsDao;

    public RequestAddressHome() {
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
        view =  inflater.inflate(R.layout.fragment_request_address_home, container, false);

        transactionDatabase = TransactionDatabase.getInstance(getContext());
        requesterTransactionsDao = transactionDatabase.requesterTransactionsDao();
        List<RequesterTransactions> requesterTransactionsList = requesterTransactionsDao.getTransactionList();
        createNewRequestButton = (AppCompatButton)view.findViewById(R.id.requestNewAddress);
        createNewRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_requestAddressHome_to_enterShareCode);
            }
        });

        OldRequestsRecyclerView = (RecyclerView) view.findViewById(R.id.old_request_recycler_view);
        FetchOldRequests();
        requestsAdapter = new RequestsAdapter(requesterTransactionsList,this);
        OldRequestsRecyclerView.setAdapter(requestsAdapter);
        OldRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        OldRequestsRecyclerView.setHasFixedSize(true);

        return view;
    }

    private void FetchOldRequests(){
        oldRequestsList = new ArrayList<>();
        oldRequestsList.add(new SingleRequest("ABCD",1,"1234567890"));
        oldRequestsList.add(new SingleRequest("EFGH",2,"1234567890"));
        oldRequestsList.add(new SingleRequest("IJKL",3,"1234567890"));
        oldRequestsList.add(new SingleRequest("MNOP",4,"1234567890"));
    }

    @Override
    public void MoveToRequestDetails(String transactionId) {
        //Move to
        Log.d("Mohan","Inside Interface Method");
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_TRANSACTION_ID,transactionId);
        Navigation.findNavController(view).navigate(R.id.action_requestAddressHome_to_requestDetails,bundle);
    }

//    private void AddDummyDataToDatabase(){
//        requesterTransactionsDao.insertTransaction(new requesterTransactions("ZMXNH", Constants.STATUS_INIT,"this is a dummy data","ShareCode"));
//        requesterTransactionsDao.insertTransaction(new requesterTransactions("ZHXNH",Constants.STATUS_ABORTED,"this is a dummy data","ShareCode"));
//        requesterTransactionsDao.insertTransaction(new requesterTransactions("ZLXNH1",Constants.STATUS_COMMITED,"this is a dummy data","ShareCode"));
//        requesterTransactionsDao.insertTransaction(new requesterTransactions("ZLXNH2",Constants.STATUS_ACCEPTED,"this is a dummy data","ShareCode"));
//        requesterTransactionsDao.insertTransaction(new requesterTransactions("ZLXNH3",Constants.STATUS_REJECTED,"this is a dummy data","ShareCode"));
//        requesterTransactionsDao.insertTransaction(new requesterTransactions("ZLXNH4",Constants.STATUS_SHARED,"this is a dummy data","ShareCode"));
//    }
}