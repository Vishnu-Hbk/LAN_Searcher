package lanclient;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class FileFinder 
{
    String find;
    String address;
    ArrayList<String> localDir = new ArrayList<>();
    
    public FileFinder()
    {
        address = new String("/");
        localDir.add("D:");
        localDir.add("E:");
        localDir.add("F:");
    }
    
    public void setSearchKey(String find)
    {
        this.find = find;
    }
    //Returns search result from one level
    public ArrayList<String> processSearch()
    {
        ArrayList<String> foundInstances = new ArrayList<String>();
        for(int j=0;j<3;j++)
        {
            File dir = new File(address+localDir.get(j));
            FilenameFilter filter = new FilenameFilter() 
            {
                public boolean accept(File dir,String name) 
                {
                return name.equalsIgnoreCase(find);
                }
            };
            String[] children = dir.list(filter);
            if (children == null) 
            {
                System.out.println("Either directory does not exist or is not a directory");
            } 
            else 
            {
                System.out.println("No.of found instances : "+children.length);
                for(int i=0;i<children.length; i++)
                {
                
                    if(children[i].toString()!=null)
                    {
                        String filename = children[i];
                        //System.out.println(filename);
                        foundInstances.add(address+localDir.get(j)+":/"+filename+"\n");
                
                    }
                }
            }
        }
        return foundInstances;
    }
}
