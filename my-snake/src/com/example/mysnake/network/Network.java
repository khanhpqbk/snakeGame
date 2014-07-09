package com.example.mysnake.network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class Network extends Thread{
	private Reader mReader;
	private Writer mWriter;
//	private Socket mSocket;
	private java.net.Socket mSocket;
	
	private boolean init = false;
	
	public static final String IP = "192.168.3.157";
	public static final int port = 7575;
	private String ip;
	
	public Network(String ip) {
		this.ip = ip;
//		mReader = new Reader();
//		mWriter = new Writer();
	}
	
	@Override
	public void run() {
//		super.run();
		
//		mSocket = Gdx.net.newClientSocket(Protocol.TCP, ip, port, new SocketHints());
		InputStream is = null;
		OutputStream os = null;
		try {
			mSocket = new java.net.Socket(ip, port);
			is = mSocket.getInputStream();
			os = mSocket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mReader = new Reader(is);
		mWriter = new Writer(os);
		
//		init = true;
		mListener.onInit();
		
	}
	
	public void send(int so) {
		OutputStream os = mWriter.getmOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		try {
			dos.writeInt(so);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isInit() {
		return init;
	}
	
	

	public void setInit(boolean init) {
		this.init = init;
	}
	
	public void setOnInitListener(OnInitListener l) {
		this.mListener = l;
	}
	
	private OnInitListener mListener;
	
	public interface OnInitListener {
		void onInit();
	}
	
}
