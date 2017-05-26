
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * Created by nidhi on 5/26/17.
 */
public class P3
{
    private static ServerSocket serversocket;
    private static boolean nodeDeleted = false;
    private static int local_host_key;
    private static String local_ip;
    private static int local_port;
    private static serverDetails myServer;
  
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

    public static String getIP() {
        try {
            InetAddress ipAddr = InetAddress.getLocalHost();
            String[] serverName = ipAddr.toString().split("/");
            return serverName[1];

        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            return "Invalid";
        }

    }


    public static void main(String args[]) throws IOException {
        if (args.length != 1) {
            System.out.println("Please enter host information format : host_key");
            System.out.println("linda>");
            return;
        }

        local_host_key = Integer.parseInt(args[0]); // assign unique indentifier to host

        serversocket = new ServerSocket(0);
        local_ip = getIP();
        local_port = serversocket.getLocalPort();


        myServer = new serverDetails(local_host_key,local_ip, local_port);
        FingerTable fingertable = new FingerTable();
        System.out.println(local_ip + " at port number: " + local_port);
        System.out.print("linda> ");

        /*Client Thread started to read request from command propmp and to communicate with other hosts*/
        myClient cmdClient = null;
        try {
            cmdClient = new myClient();
            cmdClient.start();
        }
        catch (Exception e){
            System.out.println("Main method : myClient Error");
            e.printStackTrace();
        }

        try {
            while (true) {
                ServerListener w;
                try {
                    w = new ServerListener(serversocket.accept(),serversocket); //Start client accepter thread
                    Thread t = new Thread(w);
                    t.start();
                } catch (Exception e) {
                    if(!nodeDeleted) {
                        System.out.println("Server Listener Error in connection");
                    }
                }
            }
        } finally {
            if(serversocket!=null) {
                serversocket.close();
            }
        }

        // if command prompt thread is not closed then close it
        //System.out.println("cmdClient != null"+(cmdClient != null)+ "nodeDeleted "+nodeDeleted);
        if (cmdClient != null && nodeDeleted) {
            cmdClient.shutdown();
        }
        System.out.println("Exiting the entire system");
        System.exit(0);

    }
}
