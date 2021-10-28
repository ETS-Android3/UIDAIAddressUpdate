package com.example.uidaiaddressupdate.auth.ui.main;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.uidaiaddressupdate.R;
import com.example.uidaiaddressupdate.service.auth.AuthApiEndpointInterface;
import com.example.uidaiaddressupdate.service.auth.model.Authotprequest;
import com.example.uidaiaddressupdate.service.auth.model.Authuidrequest;
import com.example.uidaiaddressupdate.service.auth.model.Authuidresponse;
import com.example.uidaiaddressupdate.service.otpservice.OtpAPIService;
import com.example.uidaiaddressupdate.service.otpservice.model.OtpRes;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableEntryException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;
import java.util.UUID;

import javax.crypto.Cipher;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                String aadharNumber = aadhar.getText().toString();
                Log.d("Mohan","Request has been sent");
                if(verifyAadhar(aadharNumber)){
                    Toast.makeText(getActivity(), "Correct format of aadhar", Toast.LENGTH_SHORT).show();

                    AuthApiEndpointInterface apiServie = AuthapiService.getApiInstance();

                    Authuidrequest authuidrequest = new Authuidrequest();
                    authuidrequest.setUid(aadharNumber);
                    apiServie.sendOtp(authuidrequest).enqueue(new Callback<Authuidresponse>() {
                        @Override
                        public void onResponse(Call<Authuidresponse> call, Response<Authuidresponse> response) {
                            String transactionId = response.body().getTransactionNo();
                            GoToOTPPage(transactionId);
                            Log.d("Mohan","OTP request is correct");
                        }

                        @Override
                        public void onFailure(Call<Authuidresponse> call, Throwable t) {
                            Log.d("Mohan","OTP Reqeust is failed : " + t.getMessage().toString());
                        }
                    });


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

    private void GoToOTPPage(String transactionID){
        Bundle bundle = new Bundle();
        bundle.putString("transactionId",transactionID);
        Fragment fragment = new OtpFragment();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

//    private void callSendOTPService(String uid) throws Exception {
//        OtpAPIService otpAPIService = new OtpAPIService();
//        otpAPIService.readProperties();
//        String txnId = UUID.randomUUID().toString();
//        Log.d("OTP Service:","Printing txnId: " + txnId);
//        OtpRes otpRes = otpAPIService.getOtpRes(uid,txnId);
//        Log.d("OTP Service:","Result : " + otpRes.getRet().value() + ", err: " + otpRes.getErr());

//    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void intializeApp() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA,"AndroidKeyStore");
        KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec
                .Builder("aadharkeys", KeyProperties.PURPOSE_ENCRYPT|KeyProperties.PURPOSE_DECRYPT)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
                .setDigests(KeyProperties.DIGEST_SHA1)
                .build();
        kpg.initialize(keyGenParameterSpec);
//        kpg.initialize(1024);

        KeyPair kp = kpg.generateKeyPair();
        Log.d("KeyTest",kp.getPrivate().toString());
        Log.d("KeyTest",kp.getPublic().toString());
        String publicKeyString = Base64.encodeToString(kp.getPublic().getEncoded(), Base64.DEFAULT);
//        Log.d("KeyTest", publicKeyString);


        //Generation of Key from String
        byte[] keyBytes = Base64.decode(publicKeyString, Base64.DEFAULT);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pk2 = keyFactory.generatePublic(spec);

        KeyStore ks = KeyStore.getInstance("AndroidKeyStore");
        ks.load(null);
        KeyStore.Entry pke = (KeyStore.Entry)ks.getEntry("aadharkeys",null);
        KeyStore.PrivateKeyEntry prk = ((KeyStore.PrivateKeyEntry)pke);

        //Encryption
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPwithSHA-1andMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pk2);
        String plain = "test";
        byte[] encryptedBytes = cipher.doFinal(plain.getBytes());
        Log.d("KeyTest", String.valueOf(encryptedBytes.length));

        String encryptedText = Base64.encodeToString(encryptedBytes, Base64.DEFAULT);

        //Decryption
        Cipher cipher1 = Cipher.getInstance("RSA/ECB/OAEPwithSHA-1andMGF1Padding");
        cipher1.init(Cipher.DECRYPT_MODE, prk.getPrivateKey());
        byte[] decryptedBytes = cipher1.doFinal(Base64.decode(encryptedText,Base64.DEFAULT));
        String decryptedText = new String(decryptedBytes);
        Log.d("KeyTest",decryptedText);
    }

}