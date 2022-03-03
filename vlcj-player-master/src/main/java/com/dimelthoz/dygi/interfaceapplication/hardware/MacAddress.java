package com.dimelthoz.dygi.interfaceapplication.hardware;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

public class MacAddress {

    private static String MAC = "";

    public MacAddress() {
        try {
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface iface : Collections.list(interfaceEnumeration)) {
                String mac = formatMacAddress(iface);
                if(isMacValid(mac)){
                    MAC = mac;
                    break;
                }
            }
        } catch (SocketException e) {
            System.out.println(e);
        }
    }

    private static String formatMacAddress(NetworkInterface netInt) throws SocketException {
        byte[] mac = netInt.getHardwareAddress();
        if (mac != null) {
            StringBuilder sbMac = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sbMac.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
            }
            return sbMac.toString();
        }
        return "";
    }

    private static boolean isMacValid(String mac){
        return mac.length() == 17;
    }

    public static String getMac() {
        return MAC;
    }
}
