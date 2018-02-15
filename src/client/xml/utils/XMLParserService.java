/**
 * XMLParserService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xml.utils;

public interface XMLParserService extends javax.xml.rpc.Service {
    public java.lang.String getXMLParserAddress();

    public xml.utils.XMLParser getXMLParser() throws javax.xml.rpc.ServiceException;

    public xml.utils.XMLParser getXMLParser(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
