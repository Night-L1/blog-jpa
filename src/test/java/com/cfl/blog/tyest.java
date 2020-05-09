package com.cfl.blog;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class tyest {

    public static void main(String[] args) throws UnknownHostException {

        String hostAddress = InetAddress.getLocalHost().getHostAddress();

        System.out.println(hostAddress);

    }
}
