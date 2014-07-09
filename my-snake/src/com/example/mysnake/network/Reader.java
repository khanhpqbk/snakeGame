package com.example.mysnake.network;

import java.io.InputStream;

public class Reader extends Thread{
	
	InputStream mInputStream;
	public Reader(InputStream is) {
		mInputStream = is;
	}
 	@Override
 	public void run() {
 		super.run();
 		
 	}
	public InputStream getmInputStream() {
		return mInputStream;
	}
	public void setmInputStream(InputStream mInputStream) {
		this.mInputStream = mInputStream;
	}
 	
 	
}
