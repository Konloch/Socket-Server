package com.konloch.socket;

import java.io.ByteArrayOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * @author Konloch
 * @since 2/28/2023
 */
public class SocketClient
{
	private final ByteArrayOutputStream inputBuffer = new ByteArrayOutputStream();
	
	//TODO this should be swapped to a ByteArrayOutputStream, however it needs to be able to shrink, so maybe a new type is needed
	private final LinkedList<Byte> outputBuffer = new LinkedList<>();
	
	private final long uid;
	private final Socket socket;
	private long lastNetworkActivity;
	private boolean inputRead;
	private boolean outputWrite;
	private int state;
	
	public SocketClient(long uid, Socket socket)
	{
		this.uid = uid;
		this.socket = socket;
		this.lastNetworkActivity = System.currentTimeMillis();
	}
	
	/**
	 * Write to a byte array the output buffer
	 *
	 * @param bytes any byte array
	 */
	public void write(byte[] bytes)
	{
		//write in reverse order
		for(int i = bytes.length-1; i >= 0; i--)
			getOutputBuffer().push(bytes[i]);
		
		setOutputWrite(true);
	}
	
	/**
	 * Reset the last network activity
	 */
	protected void resetLastNetworkActivity()
	{
		lastNetworkActivity = System.currentTimeMillis();
	}
	
	/**
	 * Return the unique user id for this specific connection
	 *
	 * @return the unique user id for this specific connection
	 */
	public long getUID()
	{
		return uid;
	}
	
	public boolean isInputRead()
	{
		return inputRead;
	}
	
	public void setInputRead(boolean inputRead)
	{
		this.inputRead = inputRead;
	}
	
	public boolean isOutputWrite()
	{
		return outputWrite;
	}
	
	public void setOutputWrite(boolean outputWrite)
	{
		this.outputWrite = outputWrite;
	}
	
	public int getState()
	{
		return state;
	}
	
	public void setState(int state)
	{
		this.state = state;
	}
	
	public Socket getSocket()
	{
		return socket;
	}
	
	public ByteArrayOutputStream getInputBuffer()
	{
		return inputBuffer;
	}
	
	public LinkedList<Byte> getOutputBuffer()
	{
		return outputBuffer;
	}
	
	public long getLastNetworkActivity()
	{
		return lastNetworkActivity;
	}
}
