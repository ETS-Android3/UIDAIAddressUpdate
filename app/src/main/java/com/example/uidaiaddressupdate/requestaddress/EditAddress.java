package com.example.uidaiaddressupdate.requestaddress;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.Location.Coordinates;
import com.example.uidaiaddressupdate.Location.LocationInterface;
import com.example.uidaiaddressupdate.Location.MyLocationListener;
import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.SharedPrefHelper;
import com.example.uidaiaddressupdate.service.server.ServerApiService;
import com.example.uidaiaddressupdate.service.server.model.updateAddress.UpdateAddressRequest;
import com.example.uidaiaddressupdate.service.server.model.updateAddress.UpdateAddressResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditAddress extends Fragment {


    private AddressModel landLordAddressModel;
    private EditText co, house, street, landmark, locality, village_town, subdist, dist, state, country, pincode, postoffice;
    private AppCompatButton save;
    private Coordinates coordinates;

    public EditAddress() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        landLordAddressModel = new Gson().fromJson(getArguments().getString("addressModel"),AddressModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_address, container, false);
        String transactionID = getArguments().getString(Constants.KEY_TRANSACTION_ID);
        //MyLocationListener locationListener = new MyLocationListener(this);
        coordinates = SharedPrefHelper.getCoordinates(getContext());
        co = (EditText) view.findViewById(R.id.addresss_co);
        house = (EditText) view.findViewById(R.id.address_house);
        street = (EditText) view.findViewById(R.id.address_street);
        landmark = (EditText) view.findViewById(R.id.addresss_landmark);
        locality = (EditText) view.findViewById(R.id.address_locality);
        village_town = (EditText) view.findViewById(R.id.address_villege_or_town);
        subdist = (EditText) view.findViewById(R.id.address_subdistrict);
        dist = (EditText) view.findViewById(R.id.address_district);
        state = (EditText) view.findViewById(R.id.address_state);
        country = (EditText) view.findViewById(R.id.address_country);
        pincode = (EditText) view.findViewById(R.id.address_pin_code);
        postoffice = (EditText) view.findViewById(R.id.address_post_office);
        save = (AppCompatButton)view.findViewById(R.id.address_save);

        co.setText(landLordAddressModel.getCo());
        house.setText(landLordAddressModel.getHouse());
        street.setText(landLordAddressModel.getStreet());
        landmark.setText(landLordAddressModel.getLm());
        locality.setText(landLordAddressModel.getLoc());
        village_town.setText(landLordAddressModel.getVtc());
        subdist.setText(landLordAddressModel.getSubdist());
        dist.setText(landLordAddressModel.getDist());
        state.setText(landLordAddressModel.getState());
        country.setText(landLordAddressModel.getCountry());
        pincode.setText(landLordAddressModel.getPc());
        postoffice.setText(landLordAddressModel.getPo());


        AddressModel updatedAddress = new AddressModel();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedAddress.setCo(co.getText().toString());
                updatedAddress.setHouse(house.getText().toString());
                updatedAddress.setStreet(street.getText().toString());
                updatedAddress.setLm(landmark.getText().toString());
                updatedAddress.setLoc(locality.getText().toString());
                updatedAddress.setVtc(village_town.getText().toString());
                updatedAddress.setSubdist(subdist.getText().toString());
                updatedAddress.setDist(dist.getText().toString());
                updatedAddress.setState(state.getText().toString());
                updatedAddress.setCountry(country.getText().toString());
                updatedAddress.setPc(pincode.getText().toString());
                updatedAddress.setPo(postoffice.getText().toString());

                Log.d("Address",String.valueOf(coordinates.getLatitude()));
                Log.d("Address",String.valueOf(coordinates.getLongitude()));
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Updating you address..");
                progressDialog.show();

                float[] coordinate = {Float.parseFloat(coordinates.getLatitude().toString()),Float.parseFloat(coordinates.getLongitude().toString())};
                UpdateAddressRequest updateAddressRequest = new UpdateAddressRequest(SharedPrefHelper.getUidToken(getContext()),SharedPrefHelper.getAuthToken(getContext()),transactionID,landLordAddressModel,updatedAddress,coordinate,SharedPrefHelper.getAadharNumber(getContext()));

                ServerApiService.getApiInstance().updateAddress(updateAddressRequest).enqueue(new Callback<UpdateAddressResponse>() {
                    @Override
                    public void onResponse(Call<UpdateAddressResponse> call, Response<UpdateAddressResponse> response) {
                        progressDialog.dismiss();
                        switch (response.code()){
                            case 200:
                                Log.d("Address",new Gson().toJson(updateAddressRequest));
                                Log.d("Address",response.message());
                                getActivity().finish();
                                Toast.makeText(getContext(),"Address Updated Successfully!!",Toast.LENGTH_SHORT).show();
                                break;

                            case 400:
                                Toast.makeText(getActivity(),"Invalid request parameters",Toast.LENGTH_SHORT).show();
                                break;

                            case 401:
                                Toast.makeText(getActivity(),"Addresses are too far",Toast.LENGTH_SHORT).show();
                                break;

                            case 403:
                                Toast.makeText(getActivity(),"Invalid addresses or transaction ID",Toast.LENGTH_SHORT).show();
                                break;

                            case 409:
                                Toast.makeText(getActivity(),"Address already updated",Toast.LENGTH_SHORT).show();
                                break;

                            default:
                                Toast.makeText(getActivity(),"Error code: "+String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }

                    @Override
                    public void onFailure(Call<UpdateAddressResponse> call, Throwable t) {
                        t.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),"Error!!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }


}