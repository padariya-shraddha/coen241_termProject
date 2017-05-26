package coen241TermProject;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer extends Thread{
	
	int portNumber;
	String hostId;
	String ipAddr;
	
	public MyServer(String hostName,String ipAddr,int portNumber){
		//it will have finger table, successor, predecessor as arguments
		this.portNumber = portNumber;
		this.hostId = hostName;
		this.ipAddr = ipAddr;
	}
	
	public void run(){
		Socket s=null;
		ServerSocket ss=null;
		
		try{
			ss = new ServerSocket(portNumber);

			while(true){
				s= ss.accept();
				
				ServerThread st=new ServerThread(s,portNumber,hostId,ipAddr);
				st.start();
			}

		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("Server error");

		}finally{
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class ServerThread extends Thread{ 
	int portNumber;
	String hostId;
	String ipAddr;
	Socket s=null;
	public ServerThread(Socket s,int portNumber,String hostId,String ipAddr){
		this.s = s;
		this.portNumber = portNumber;
		this.hostId = hostId;
		this.ipAddr = ipAddr;
	}

	public void run() {
		//process request
	}
}
