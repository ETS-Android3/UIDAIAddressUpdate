package com.example.uidaiaddressupdate;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

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
}
