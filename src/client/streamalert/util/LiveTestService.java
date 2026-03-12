/**
 * LiveTestService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package streamalert.util;

public interface LiveTestService extends javax.xml.rpc.Service {
    public java.lang.String getLiveTestAddress();

    public streamalert.util.LiveTest getLiveTest() throws javax.xml.rpc.ServiceException;

    public streamalert.util.LiveTest getLiveTest(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
