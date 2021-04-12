import java.io.*; // import I/O functions
import java.net.*; // import networking libraries



public class InetClient
{
 public static void main (String args[])
 {
 String serverName;
 if (args.length < 1) serverName = "localhost"; // setting server name to localhost if nothing provide to it
 else serverName = args[0];
 
 System.out.println("Clark Elliott's Inet Client, 1.8.\n");
 System.out.println("Using server: " + serverName + ", Port: 1565");
 BufferedReader in = new BufferedReader(new    InputStreamReader(System.in)); // read the input stream

 try 
{
 String name;
 do 
{
 System.out.print
 ("Enter a hostname or an IP address, (quit) to end: ");
 System.out.flush ();
 name = in.readLine ();
 if (name.indexOf("quit") < 0) 
getRemoteAddress(name, serverName); // call this method if given string is not quit
 } 

// when stirng is quit, close client side 
while (name.indexOf("quit") < 0);  
 System.out.println ("Cancelled by user request."); 
 } 

catch (IOException x) 
{
x.printStackTrace ();
}
 }
 
// formatting IP address to string
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
 
// getting remoteaddress of server
static void getRemoteAddress (String name, String serverName)
{
 Socket sock;
 BufferedReader fromServer;
 PrintStream toServer;
 String textFromServer;
 
 Try
{
 
 sock = new Socket(serverName, 1565); // establish connection by serverName and port
  
 // read input from server and return an input stream for the given sock
 fromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
 toServer = new PrintStream(sock.getOutputStream());
 toServer.println(name);  // print the name to server
 toServer.flush(); // send partially filled buffer to server
 

 for (int i = 1; i <=3; i++)
{
 textFromServer = fromServer.readLine(); // read message from server
 if (textFromServer != null)
 System.out.println(textFromServer); // print message if it is not empty
 }
 sock.close();
 } 
catch (IOException x) 
{
 System.out.println ("Socket error.");
 x.printStackTrace ();
 }
 }
}
