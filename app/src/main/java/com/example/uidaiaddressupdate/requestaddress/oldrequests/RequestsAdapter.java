package com.example.uidaiaddressupdate.requestaddress.oldrequests;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.database.RequesterTransactions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {

    private List<RequesterTransactions> requestsList;
    private NavigateToRequestDetails context;

    public RequestsAdapter(List<RequesterTransactions> requestsList, NavigateToRequestDetails context) {
        this.requestsList = requestsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.old_request_layout,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequesterTransactions singleRequest = requestsList.get(position);
        holder.shareCode.setText(singleRequest.getShareCode());
        holder.status.setText(singleRequest.getTransactionStatus());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Mohan","Layout is Clicked");
                (context).MoveToRequestDetails(singleRequest.getTransactionID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView shareCode,status;
        ConstraintLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shareCode = (TextView) itemView.findViewById(R.id.old_request_single_share_code);
            status = (TextView) itemView.findViewById(R.id.old_request_single_status);
            layout = (ConstraintLayout) itemView.findViewById(R.id.old_request_constraint_layout);
        }
    }

    private String getStatus(int status){
        return "Status is " + status;
    }

}
