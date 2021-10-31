# Aadhaar Hackathon 2021
Team Name: codesociety   
Team Leader: [Pranav Gupta](https://www.github.com/pranavg000)  
Team Members:
[Aman Kumar Singh](https://www.github.com/amankrsingh1812) |
[Mohan Kumar](https://www.github.com/mohan2106) |
[Pooja Gajendra Bhagat](https://www.github.com/PoojaB01) |
[Sourav Goel](https://www.github.com/coolgoel)

## Theme
Address Update

## Problem Statement
Address Update Challenge in Urban Areas  
* As per the current policy, Aadhaar requires a supporting Proof of Address (PoA) document or an Introducer who can lend his address to update the aadhaar. 
* You are required to provide an innovative solution using technology that will help in overcoming your challenge to update the aadhaar. 
* For the solution, you will interface with your landlord in an online manner using a smartphone or online portal to request his address (known as Introducer and performing the role of the donor).

## Solution Approach

## Running the Code

#### Testing Already Deployed App
The server is already deployed in one of the AWS India Data Centers. 
1. The apk for the android app can be downloaded from 
2. After Installation, user needs to enter his/her Aadhar Number. An OTP will be sent to mobile number registed with Aadhar.
3. After sucessfull otp authentication, the user can make/approve address update requests as shown in the User Interface section of this Readme file.

#### Testing Locally
1.The code for server program can be found in the `server` directory. To start the server:
```
git clone https://github.com/mohan2106/UIDAIAddressUpdate
cd server
python manage.py migrate
python manage.py runserver
```

## User Interface

