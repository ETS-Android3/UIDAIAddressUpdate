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
import android.widget.TextView;
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
    private TextView no_address_request_text;

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
        view = inflater.inflate(R.layout.fragment_lender_requests_list, container, false);
        lenderRequestsRecyclerView = (RecyclerView) view.findViewById(R.id.lender_request_list);
        singleLenderRequestModelList = new ArrayList<>();

        no_address_request_text = (TextView) view.findViewById(R.id.no_address_request_text);
        no_address_request_text.setVisibility(View.GONE);

        db = TransactionDatabase.getInstance(getContext());
        lenderTransactionsDao = db.lenderTransactionsDao();

        List<LenderTransactions> lenderTransactionsList = lenderTransactionsDao.getTransactionList();

        if (lenderTransactionsList.size() == 0) {
            no_address_request_text.setVisibility(View.VISIBLE);
        } else {
            no_address_request_text.setVisibility(View.GONE);
        }
        adapter = new LenderRequestListAdapter(lenderTransactionsList, this);

        lenderRequestsRecyclerView.setHasFixedSize(true);
        lenderRequestsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        lenderRequestsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    /**
     * Redirects to Captcha page for offline eKYC when Lender approves the request
     *
     * @param transactionId
     * @param receiverShareCode
     */
    @Override
    public void GotToCaptchaPage(String transactionId, String receiverShareCode) {
        // Move to Captcha Page
        Log.d("DebugLogs", "Request Has approved");
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_TRANSACTION_ID, transactionId);
        bundle.putString(Constants.KEY_RECEIVER_SHARECODE_ID, receiverShareCode);
        Navigation.findNavController(view).navigate(R.id.action_lenderRequestsList_to_lenderSingleRequests, bundle);
    }

    /**
     * Deletes the request if Lender declines
     *
     * @param transactionId
     * @param receiverShareCode
     */
    @Override
    public void HandleRequestDeclined(String transactionId, String receiverShareCode) {
        // Request has declined
        Toast.makeText(getContext(), "Request has declined", Toast.LENGTH_SHORT).show();
        lenderTransactionsDao.deleteTransaction(transactionId);
        updateFragment();
    }

    private void updateFragment() {
        List<LenderTransactions> lenderTransactionsList = lenderTransactionsDao.getTransactionList();
        if (lenderTransactionsList.size() == 0) {
            no_address_request_text.setVisibility(View.VISIBLE);
        } else {
            no_address_request_text.setVisibility(View.GONE);
        }
        adapter = new LenderRequestListAdapter(lenderTransactionsList, this);
        lenderRequestsRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}