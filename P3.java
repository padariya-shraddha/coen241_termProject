package chord;


import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class P3 {
	public static final int M = 6;
	private Map<Integer,String> dataKeys = new HashMap<Integer,String>();
	private Finger finger[];
	private List<Finger> fingerTable;
	private int nodeId;
	private Node successorNode;
	private Node predecessorNode;
	
	
	
	P3(ServerSocket socket, String ip, int portNo) throws IOException {
		initialise(ip, portNo);
		fingerTable = new ArrayList<Finger>();
		MyServer server = new MyServer(nodeId, ip,portNo,fingerTable);
		Client client = new Client(fingerTable);
	}
	
	private void initialise (String ip, int portNo) throws UnknownHostException {
		successorNode = new Node(nodeId,ip,portNo);
		predecessorNode = new Node(nodeId,ip,portNo);
		for(int i = 0; i < M; i++) {
			int gap = (int) (nodeId+ Math.pow(2,i));
			finger[i] = new Finger(gap,nodeId+gap, nodeId, ip ,portNo );
			fingerTable.add(finger[i]);
		}
	}
	
}
