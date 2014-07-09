package com.example.mysnake.network;

import java.io.OutputStream;

public class Writer {
	private OutputStream mOutputStream;
	
	public Writer(OutputStream os) {
		mOutputStream = os;
	}

	public OutputStream getmOutputStream() {
		return mOutputStream;
	}

	public void setmOutputStream(OutputStream mOutputStream) {
		this.mOutputStream = mOutputStream;
	}
	
	
}
