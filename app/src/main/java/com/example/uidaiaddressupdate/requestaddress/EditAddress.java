package com.example.uidaiaddressupdate.requestaddress;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uidaiaddressupdate.Constants;
import com.example.uidaiaddressupdate.Location.LocationInterface;
import com.example.uidaiaddressupdate.Location.MyLocationListener;
import com.example.uidaiaddressupdate.R;
import com.google.gson.Gson;


public class EditAddress extends Fragment implements LocationInterface {


    private AddressModel landLordAddressModel;
    private EditText co, house, street, landmark, locality, village_town, subdist, dist, state, country, pincode, postoffice;
    private AppCompatButton save;
    private Double longitude,lattitude;

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
        MyLocationListener locationListener = new MyLocationListener(this);

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


            }
        });
        return view;
    }

    @Override
    public void updateLocation(Double longitude, Double lattitude) {
        this.longitude = longitude;
        this.lattitude = lattitude;
        Toast.makeText(getContext(), "Coordinates are : (" + longitude + "," + lattitude + ")", Toast.LENGTH_SHORT).show();
    }
}