package com.xianyue;

import java.net.InetAddress;

/**
 * @author XianYue
 */
public class NetWorkProc {

    public static void main(String[] args) throws Exception {
        for (InetAddress address : InetAddress.getAllByName("langle-desktop")) {
            System.out.println(ipv4OrIpv6(address));
        }
        System.out.println("-------InetAddress.getLocalHost()");
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println("HostName := " + addr.getHostName());
        System.out.println("HostAddress := " + addr.getHostAddress());
        System.out.println("-------InetAddress.getByName(\"micmiu.com\")");
        InetAddress addr2 = InetAddress.getByName("micmiu.com");
        System.out.println("HostName := " + addr2.getHostName());
        System.out.println("HostAddress := " + addr2.getHostAddress());
    }


    private static String ipv4OrIpv6(InetAddress ita) {
        String[] itn = ita.toString().split("/");
        String str = itn[1];
        if (str.length() > 16) {
            return "IPv7\t" + ita.toString();
        }
        return "IPv4\t" + ita.toString();
    }
}
