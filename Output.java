package lanclient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Output 
{
    private OutputStream os;
    private OutputStreamWriter osr;
    private BufferedWriter bw;
    
    public Output(Socket sock) throws IOException
    {
        os = sock.getOutputStream();
        osr = new OutputStreamWriter(os);
        bw = new BufferedWriter(osr);
    }
    
    public void sendInt(int num) throws IOException
    {
        bw.write(num);
    }
    public void sendString(String message) throws IOException
    {
        bw.write(message);
    }
    public void refresh() throws IOException
    {
        bw.flush();
    }
    public void end() throws IOException
    {
       bw.close();
    }
}
