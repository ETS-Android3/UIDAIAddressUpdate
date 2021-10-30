package com.example.uidaiaddressupdate.lender;

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

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.database.LenderTransactions;
import com.example.uidaiaddressupdate.database.LenderTransactionsDao;
import com.example.uidaiaddressupdate.database.TransactionDatabase;

import java.util.ArrayList;
import java.util.List;

public class LenderRequestsList extends Fragment implements LenderRequests {


    private View view;
    private RecyclerView lenderRequestsRecyclerView;
    private List<SingleLenderRequestModel> singleLenderRequestModelList;
    private LenderRequestListAdapter adapter;
    private TransactionDatabase db;
    private LenderTransactionsDao lenderTransactionsDao;

    public LenderRequestsList() {
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
        view =  inflater.inflate(R.layout.fragment_lender_requests_list, container, false);
        lenderRequestsRecyclerView = (RecyclerView) view.findViewById(R.id.lender_request_list);
        singleLenderRequestModelList = new ArrayList<>();
        loadData();

        db = TransactionDatabase.getInstance(getContext());
        lenderTransactionsDao = db.lenderTransactionsDao();

        AddDummyDataToDatabase();

        List<LenderTransactions> lenderTransactionsList = lenderTransactionsDao.getTransactionList();
        Toast.makeText(getContext(), "list size is " + lenderTransactionsList.size(), Toast.LENGTH_SHORT).show();

        adapter = new LenderRequestListAdapter(lenderTransactionsList,this);

        lenderRequestsRecyclerView.setHasFixedSize(true);
        lenderRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        lenderRequestsRecyclerView.setAdapter(adapter);
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
        Navigation.findNavController(view).navigate(R.id.action_lenderRequestsList_to_lenderSingleRequests,bundle);
    }

    @Override
    public void HandleRequestDeclined(String transactionid, String receiverShareCode) {
        //Request has declined
        Toast.makeText(getContext(), "Request has declined", Toast.LENGTH_SHORT).show();
    }

    private void loadData(){
        singleLenderRequestModelList.add(new SingleLenderRequestModel("Mohan","ABCD","9876543210","null","ABCD"));
        singleLenderRequestModelList.add(new SingleLenderRequestModel("Aman","ABCD","9876543210","null","ABCD"));
        singleLenderRequestModelList.add(new SingleLenderRequestModel("Pranav","ABCD","9876543210","null","ABCD"));
        singleLenderRequestModelList.add(new SingleLenderRequestModel("Sourav","ABCD","9876543210","null","ABCD"));
        singleLenderRequestModelList.add(new SingleLenderRequestModel("Pooja","ABCD","9876543210","null","ABCD"));
    }

    private void AddDummyDataToDatabase(){
        lenderTransactionsDao.insertTransaction(new LenderTransactions("ABCD","Mohan Aman","9876543210","success","I am a good person","Share Code"));
        lenderTransactionsDao.insertTransaction(new LenderTransactions("EFGH","Aman PRanav","9876543210","success","I am a good person","Share Code"));
    }

}