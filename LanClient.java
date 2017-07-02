package lanclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class LanClient 
{
    public static void main(String[] args) throws IOException 
    {
        int timeout=500;
        int port = 1234;
        int count=0,count2=0;
        String connected[] = new String[1000];
        try {
            String currentIP = InetAddress.getLocalHost().toString();
            String subnet = getSubnet(currentIP);
            System.out.println("subnet: " + subnet);

            for (int i=1;i<=255;i++){

                String host = subnet + i;
                System.out.println("Checking :"  + host);

                if (InetAddress.getByName(host).isReachable(timeout)){
                    //System.out.println(host + " is reachable");
                    count++;
                    connected[count]=host;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("No.of devices connected : "+count);
        //Printing ipv4 of all connected devices 
        for(int j=1;j<connected.length;j++)
        {
            if(connected[j]!=null)
            {
                System.out.println(connected[j]);
            }
        }
        
    InetAddress i = InetAddress.getLocalHost();
    String host = i.getHostAddress();
    FileFinder ff = new FileFinder();
    Socket sock = null;
    for(int ij=0;ij<connected.length;ij++)
    {
        try
        {
            sock = new Socket(connected[ij],25000);
            if(sock.isConnected())
            {
                break;
            }
        }
        catch(ConnectException ce)
        {
        }
    }
    Input receive = new Input(sock);
    String received1 = receive.receiveString();
    System.out.println("Message recieved: "+received1);
    ff.setSearchKey(received1);
    ArrayList<String> found = ff.processSearch();
    System.out.println(found.size());
    Output send = new Output(sock);
    send.sendString(host+"\n");
    send.refresh();
    send.sendInt(found.size());
    send.refresh();
    for(int j=0;j<found.size();j++)
    {
        System.out.println(found.get(j));
        send.sendString("//"+host+found.get(j));
        send.refresh();
    }
    send.refresh();
    sock.close();
    send.end();
    }
    public static String getSubnet(String currentIP) 
    {
        int firstSeparator = currentIP.lastIndexOf("/");
        int lastSeparator = currentIP.lastIndexOf(".");
        return currentIP.substring(firstSeparator+1, lastSeparator+1);
    }
}



/*-----------------------------------------------------------------------------
    String host = "localhost";
    InetAddress i = InetAddress.getByName(host);
    FileFinder ff = new FileFinder();
    Socket sock = new Socket(i,25000);
    Input receive = new Input(sock);
    System.out.println("Connection Established");
    System.out.println("Pt 1");
    String received = receive.receiveString();
    System.out.println("Pt 2");
    System.out.println("Message recieved: "+received);
    ff.setSearchKey("S");
    ArrayList<String> found = ff.processSearch();
    System.out.println(found.size());
    Output send = new Output(sock);
    send.sendInt(found.size());
    send.refresh();
    for(int j=0;j<found.size();j++)
    {
        System.out.println(found.get(j));
        send.sendString(found.get(j)+"\n");
        send.refresh();
    }
    System.out.println("end");
    send.sendString("end");
    send.refresh();
    sock.close();
    send.end();
*/