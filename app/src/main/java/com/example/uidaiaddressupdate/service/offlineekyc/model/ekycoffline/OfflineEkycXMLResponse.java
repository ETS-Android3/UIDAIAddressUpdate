package com.example.uidaiaddressupdate.service.offlineekyc.model.ekycoffline;

public class OfflineEkycXMLResponse {

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getUidNumber() {
        return uidNumber;
    }

    public void setUidNumber(String uidNumber) {
        this.uidNumber = uidNumber;
    }
}
