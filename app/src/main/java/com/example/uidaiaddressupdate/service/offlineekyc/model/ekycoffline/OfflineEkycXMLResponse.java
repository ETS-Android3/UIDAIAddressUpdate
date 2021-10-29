package com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline;


import java.time.LocalDate;

public class OfflineEkycXMLResponse{

    private String eKycXML;
    private String fileName;
    private String status;
    private String requestDate;
    private String uidNumber;

    public String geteKycXML() {
        return eKycXML;
    }

    public void seteKycXML(String eKycXML) {
        this.eKycXML = eKycXML;
    }
}
