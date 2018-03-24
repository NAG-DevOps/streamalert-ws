/**
 * XMLParserServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xml.utils;

public class XMLParserServiceLocator extends org.apache.axis.client.Service implements xml.utils.XMLParserService {

    public XMLParserServiceLocator() {
    }


    public XMLParserServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public XMLParserServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for XMLParser
    private java.lang.String XMLParser_address = "http://localhost:9999/PM1_Service/services/XMLParser";

    public java.lang.String getXMLParserAddress() {
        return XMLParser_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String XMLParserWSDDServiceName = "XMLParser";

    public java.lang.String getXMLParserWSDDServiceName() {
        return XMLParserWSDDServiceName;
    }

    public void setXMLParserWSDDServiceName(java.lang.String name) {
        XMLParserWSDDServiceName = name;
    }

    public xml.utils.XMLParser getXMLParser() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(XMLParser_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getXMLParser(endpoint);
    }

    public xml.utils.XMLParser getXMLParser(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            xml.utils.XMLParserSoapBindingStub _stub = new xml.utils.XMLParserSoapBindingStub(portAddress, this);
            _stub.setPortName(getXMLParserWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setXMLParserEndpointAddress(java.lang.String address) {
        XMLParser_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (xml.utils.XMLParser.class.isAssignableFrom(serviceEndpointInterface)) {
                xml.utils.XMLParserSoapBindingStub _stub = new xml.utils.XMLParserSoapBindingStub(new java.net.URL(XMLParser_address), this);
                _stub.setPortName(getXMLParserWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("XMLParser".equals(inputPortName)) {
            return getXMLParser();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://utils.xml", "XMLParserService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://utils.xml", "XMLParser"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("XMLParser".equals(portName)) {
            setXMLParserEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
