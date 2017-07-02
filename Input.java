package lanserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Input 
{
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;
    
    public Input(Socket sock) throws IOException
    {
        is = sock.getInputStream();
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
    }
    
   public int receiveInt() throws IOException
   {
       return br.read();
   }
   public String receiveString() throws IOException
   {
       return br.readLine(); 
   }
   public void end() throws IOException
   {
       br.close();
   }
}
