package com.example.uidaiaddressupdate;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.uidaiaddressupdate.requestaddress.AddressModel;

import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.LocalFileHeader;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLUtils {
    public static String getAddressFromeKYC(String eKYCXml){
        return "";
    }

    public static NewAddressRequestMessage createAddressRequestMessageFromKYC(String eKYCXml) throws Exception {
        Document eKYCdoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(eKYCXml)));

        NamedNodeMap poiAttributes = eKYCdoc.getElementsByTagName("Poi").item(0).getAttributes();
        NewAddressRequestMessage addressRequestMessage = new NewAddressRequestMessage(poiAttributes.getNamedItem("name").getNodeValue(),poiAttributes.getNamedItem("phone").getNodeValue());
        return addressRequestMessage;
    }

    public static AddressModel getAddressFromEKYCxml(String eKYCXml) throws Exception{
        Document eKYCdoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(eKYCXml)));
        NamedNodeMap poaAttributes = eKYCdoc.getElementsByTagName("Poa").item(0).getAttributes();
        AddressModel addressModel = new AddressModel();

        if(poaAttributes.getNamedItem("co") != null)
            addressModel.setCo(poaAttributes.getNamedItem("co").getNodeValue());

        if(poaAttributes.getNamedItem("house") != null)
            addressModel.setHouse(poaAttributes.getNamedItem("house").getNodeValue());

        if(poaAttributes.getNamedItem("street") != null)
            addressModel.setStreet(poaAttributes.getNamedItem("street").getNodeValue());

        if(poaAttributes.getNamedItem("lm") != null)
            addressModel.setLm(poaAttributes.getNamedItem("lm").getNodeValue());

        if(poaAttributes.getNamedItem("vtc") != null)
            addressModel.setVtc(poaAttributes.getNamedItem("vtc").getNodeValue());

        if(poaAttributes.getNamedItem("subdist") != null)
            addressModel.setSubdist(poaAttributes.getNamedItem("subdist").getNodeValue());

        if(poaAttributes.getNamedItem("dcoist") != null)
            addressModel.setDist(poaAttributes.getNamedItem("dist").getNodeValue());

        if(poaAttributes.getNamedItem("state") != null)
            addressModel.setState(poaAttributes.getNamedItem("state").getNodeValue());

        if(poaAttributes.getNamedItem("country") != null)
            addressModel.setCountry(poaAttributes.getNamedItem("country").getNodeValue());

        if(poaAttributes.getNamedItem("pc") != null)
            addressModel.setPc(poaAttributes.getNamedItem("pc").getNodeValue());

        if(poaAttributes.getNamedItem("po") != null)
            addressModel.setPo(poaAttributes.getNamedItem("po").getNodeValue());

        return addressModel;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String getKYCxmlFromZip(String zipBase64, String password) throws IOException {

        String eKYCxml = "";

        byte[] decoder = Base64.getDecoder().decode(zipBase64);

        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(decoder), password.toCharArray());

        LocalFileHeader localFileHeader;
        byte[] readBuffer = new byte[4096];
        int readLen;

        while ((localFileHeader = zipInputStream.getNextEntry()) != null) {
            Log.d("eKYC",localFileHeader.getFileName());
            while ((readLen = zipInputStream.read(readBuffer)) != -1) {
                eKYCxml += new String(readBuffer, StandardCharsets.UTF_8);
            }
        }

        return eKYCxml;
    }
}
