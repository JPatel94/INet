import java.io.*; // import I/O functions
import java.net.*; // import networking libraries

class Worker extends Thread  // subclass of thread uses thread’s component
 Socket sock; 
 Worker (Socket s) {sock = s;} // local variable s assigned to sock
 
public void run() //perform action for a thread
  {
    
 PrintStream out = null; // for formatting in output stream
 BufferedReader in = null; // read text from input stream 

try 
{
 in = new BufferedReader(new InputStreamReader(sock.getInputStream())); // get the connection and read input
 out = new PrintStream(sock.getOutputStream()); // read output

 
try {
 String name;
 name = in.readLine ();
 System.out.println("Looking up " + name);
 printRemoteAddress(name, out);
 } 

// catch exception when problem with specified part of the socket
catch (IOException x)
 {
 System.out.println("Server read error");
 x.printStackTrace ();
 }
 sock.close(); // close soc connection only
 } 

catch (IOException ioe) 
{
System.out.println(ioe); // print exception 
}
 }
 
// print host name and IP to the socket and return it to client
 static void printRemoteAddress (String name, PrintStream out) {
 
try {
 out.println("Looking up " + name + "...");
 InetAddress machine = InetAddress.getByName (name);
 out.println("Host name : " + machine.getHostName ());
 out.println("Host IP : " + toText (machine.getAddress ()));
 } 

catch(UnknownHostException ex) // catch exception if server unable to lookup host name or IP
 {
 out.println ("Failed in atempt to look up " + name);
 }
 }
 
 // method for formatting IP address to string
 static String toText (byte ip[])
 { 
 StringBuffer result = new StringBuffer ();
 for (int i = 0; i < ip.length; ++ i) 
{
 if (i > 0) result.append (".");
 result.append (0xff & ip[i]);
 }
 return result.toString ();
 }
}


public class InetServer {
 
 public static void main(String a[]) throws IOException {
 int q_len = 6; 
 int port = 1565;
 Socket sock;
 
 ServerSocket servsock = new ServerSocket(port, q_len);
 
 System.out.println
 ("Clark Elliott's Inet server 1.8 starting up, listening at port 1565.\n");
 

while (true) 
{
 sock = servsock.accept();  // server which loop around listening for socket connection
 new Worker(sock).start();  // after getting connection, pass it to worker and start worker thread
}
 }
}
