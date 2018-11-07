package com.jola.shengfan.skills.socket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by lenovo on 2018/11/5.
 */

public class SocketProtocol {

    private static Thread mScoketThread;
    private static Socket mSocket;
    private static InetSocketAddress mInetSocketAddress;

    public static void connectBySocket(final String address, final int port){

        mScoketThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mSocket = new Socket();
                    mInetSocketAddress = new InetSocketAddress(address, port);

                    mSocket.connect(mInetSocketAddress,10*1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }




}
