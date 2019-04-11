package com.address;

import com.utils.P;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPCharacteristics {
    public static void main(String args[]){
        if(args.length == 0){
            args = new String[]{""};
        }
        try {
            InetAddress address = InetAddress.getByName(args[0]);
            if(address.isAnyLocalAddress()){
                P.pln(address +"is a wildcard address");
            }
            if(address.isLoopbackAddress()){
                P.pln(address +"is a loopback address");
            }
            if(address.isLinkLocalAddress()){
                P.pln(address +"is a link-local address");
            }else if(address.isSiteLocalAddress()){
                P.pln(address +"is a site-local address");
            }else {
                P.pln(address +"is a global address");
            }

            if(address.isMulticastAddress()){
                if(address.isMCGlobal()){
                    P.pln(address +"is a global multicast address");
                }else if(address.isMCOrgLocal()){
                    P.pln(address +"is an organization wide multicast address");
                }else if(address.isMCSiteLocal()){
                    P.pln(address +"is an site wide multicast address");
                }else if(address.isMCLinkLocal()){
                    P.pln(address +"is an subnet wide multicast address");
                }else if(address.isMCNodeLocal()){
                    P.pln(address +"is an interface-local multicast address");
                }else {
                    P.pln(address +"is an unknown wide multicast address");
                }
            }else {
                P.pln(address +"is a unicast address");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            P.pln("Could no resolve "+args[0]);
        }

    }
}
