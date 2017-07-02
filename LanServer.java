package lanserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class LanServer 
{
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void display(String message)
    {
        System.out.println(message);
    }
    public static void main(String[] args) throws IOException 
    {
        ServerSocket server = new ServerSocket(25000);
        Socket sock = server.accept();
        Output send = new Output(sock);
        String searchKey;
        display("Enter the name of file you want to search");
        searchKey = br.readLine();
        send.sendString(searchKey+"\n");
        send.refresh();
        Input receive = new Input(sock);
        String received;
        String clientip = receive.receiveString();
        display("Connected to : "+clientip);
        int n = receive.receiveInt();
        display("No. of Instances of the "+searchKey+" found is :"+n);
        display("Found On:");
        for(int i=1;i<=n;i++)
        {
            received = receive.receiveString();
            display(received);
        }                       
        server.close();
    }
    
}
