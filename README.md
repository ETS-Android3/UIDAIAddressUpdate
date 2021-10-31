# Aadhaar Hackathon 2021
Team Name: codesociety   
Team Leader: [Pranav Gupta](https://www.linkedin.com/in/pranavg000/)  
Team Members:
[Aman Kumar Singh](https://www.linkedin.com/in/aman2018/) |
[Mohan Kumar](https://www.linkedin.com/in/mohanjnv1/) |
[Pooja Gajendra Bhagat](https://www.linkedin.com/in/poojabhagat/) |
[Sourav Goel](https://www.linkedin.com/in/sourav-goel6174/)

## Theme
Address Update

## Problem Statement
Address Update Challenge in Urban Areas  
* As per the current policy, Aadhaar requires a supporting Proof of Address (PoA) document or an Introducer who can lend his address to update the aadhaar. 
* You are required to provide an innovative solution using technology that will help in overcoming your challenge to update the aadhaar. 
* For the solution, you will interface with your landlord in an online manner using a smartphone or online portal to request his address (known as Introducer and performing the role of the donor).

## Solution Approach
We have added 3 functionalities to solve the above problem
### Authentication using Aadhar number
* User need to login to the application using their aadhar number and otp will be sent to the mobile number added to that aadhar number. 
* After Login user will receive their sharable code the he/she can share with anyone. (For security purpose we are not allowing them to share aadhar number)
### Request Address from requester side to lender
* In this flow user need the sharable code of lender.
* Using sharable code of lender, requester can request him/her for their address.
* After the approval of address from lender, requester will get notification about the approval, thereafter he/she can do minor modification in the address provided by lender and set it as his/her address. 
### Accept and Approve Address update from lender side
* When requester make an Address request to the lender, lender will get notification about the request.
* In the request he will be able to see name and contact number of requester for verification. 
* He can accept or reject the request.
* After rejection, a notification will be sent to requester about the rejection he received. 
* After clicking on approve, lender will have to fill the captcha and thereafter he/she also need to fill the otp he/she will receive on his/her mobile number added in the aadhar. 
* After verification through otp and ofline ekyc of lender will be shared with the requester in the encrypted form. 
* Requester will download the offline ekyc, decrypt it and use the address of lender. 
### View sharable code.
* User can access their Sharable Code in the app.  
## NOTE:
* All the data sharing are happening in the encrypted format.
* We are not storing the personal data anywhere

## Running the Code

#### Testing Already Deployed App
The server is already deployed in one of the AWS India Data Centers. 
1. The apk for the android app can be downloaded from 
   <br>https://drive.google.com/file/d/1lziJ5DC_h7IPzWpa3g_98pZ0BCNfTnLj/view?usp=sharing
3. After Installation, user needs to enter his/her Aadhar Number. An OTP will be sent to mobile number registed with Aadhar.
4. After sucessfull otp authentication, the user can make/approve address update requests as shown in the User Interface section of this Readme file.

#### Testing Locally
1. The code for server program can be found in the `Server` directory. To start the server:
```
git clone https://github.com/mohan2106/UIDAIAddressUpdate
cd Server
python manage.py makemigrations
python manage.py migrate
python manage.py runserver
```

2. Update `SERVER_ADDRESS` in [Constants.java](/app/src/main/java/com/example/uidaiaddressupdate/Constants.java)
``` 
public Static String SERVER_ADDRESS = '<your_server_address>'
```

3. To build and run the application, follow the guide: https://developer.android.com/studio/run

## User Interface

