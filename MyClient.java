package coen241TermProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class MyClient extends Thread{
	
	public MyClient(){
		//it will have finger table, successor, predecessor as arguments
		//con
	}

	public void run(){
		try {
			BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("P3 > ");
			String line = br.readLine();
			line = line.trim();
			
			while(!line.equals("quit")){
				
				//parse user command
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
