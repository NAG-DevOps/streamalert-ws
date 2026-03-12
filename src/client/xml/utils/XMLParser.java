/**
 * XMLParser.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xml.utils;

public interface XMLParser extends java.rmi.Remote {
    public void main(java.lang.String[] args) throws java.rmi.RemoteException;
    public java.lang.String parseXML(int markup, java.lang.String uri, int parserType, java.lang.String searchTerm) throws java.rmi.RemoteException;
}
