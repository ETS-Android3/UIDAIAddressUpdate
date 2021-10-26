package com.example.uidaiaddressupdate.auth.ui.main;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.uidaiaddressupdate.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginFragment extends Fragment {

    private authViewModel mViewModel;
    private EditText aadhar;
    private Button sendOtp;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.login_fragment, container, false);
        aadhar = (EditText)view.findViewById(R.id.login_et_aadhar);
        sendOtp = (Button) view.findViewById(R.id.login_send_otp);

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aadharNumber = aadhar.getText().toString();
                if(verifyAadhar(aadharNumber)){
                    //send OTP to user
                    //move to OTP page
//                    Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_otpFragment,null);
//                    Navigation.findNavController().navigate();
                    GoToOTPPage();
                    Toast.makeText(getActivity(), "Correct format of aadhar", Toast.LENGTH_SHORT).show();
                }else{
                    aadhar.setError("Invalid Aadhar");
                    aadhar.requestFocus();
                    Toast.makeText(getActivity(), "Wrong Format", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(authViewModel.class);
        // TODO: Use the ViewModel
    }

    private Boolean verifyAadhar(String aadhar_number){
        if(aadhar_number.length() == 12){
            try{
                Double.parseDouble(aadhar_number);
                return true;
            }catch(NumberFormatException e){
                return false;
            }
        }else{
            return false;
        }
    }

    private void GoToOTPPage(){
        Fragment fragment = new OtpFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}