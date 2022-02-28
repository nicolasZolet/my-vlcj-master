package com.dimelthoz.dygi.interfaceapplication.hardware;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

public class MacAddress {

    private static final String MAC = "1A2B3C4D5E6F";

    public static byte[] getHardwareAddress() {
        try {
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface iface : Collections.list(interfaceEnumeration)) {
                if (!iface.isLoopback() && iface.isUp() && iface.getHardwareAddress() != null) {
                    return iface.getHardwareAddress();
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not discover first network interface hardware address");
        }
        throw new RuntimeException("Could not discover first network interface hardware address");
    }

    public static String getMac () {
        return MAC;
    }
}
