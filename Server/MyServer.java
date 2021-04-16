import com.distinct.rpc.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class MyServer extends LOGINPROGServer
{
	private Boolean loggedIn = false;
        String user = "";
 	File file = new File("ServerFiles/UserFiles");
    	String[] directories = file.list((current, name) -> new File(current, name).isDirectory());
    	Boolean exists = false;
	
	public static void main(String[] args)
	{
		try 
		{	new MyServer(); // creates an instance of the Server stub
 	   } 
    	catch (RPCError e) 
		{	System.out.println(e.getMessage()); 
    	} 
	}
 
	public MyServer() throws RPCError 
	{	super(); 
	}

    public Boolean findFile(String name,File file)
    {
        name = name+".txt";
        Boolean check = false;
        File[] list = file.listFiles();
        if(list!=null)
            //return "not null";
        for (File fil : list)
            {
                if (fil.isDirectory())
                {
                    findFile(name,fil);                }
                else if (name.equalsIgnoreCase(fil.getName()))
                {
                    check = true;
                }
            }
        return check;
    }

	public String login_1(String a)
	{
               String[] clientMessage = a.split("\\;");
               System.out.println("message received: "+ clientMessage);
               try{
                   if(loggedIn == true){
                       return("104: user already logged in");
                   }
                   for(int i=0;i<directories.length;i++) {
                       if (directories[i].trim().equals(clientMessage[0])) {
                           exists=true;
                           user=directories[i].trim();
                       }
                   }
                   String path = "ServerFiles/UserFiles/"+user;
                   if(exists==true && findFile(clientMessage[1],new File(path)) == true){
                       loggedIn = true;
                       return("101: user logged in " + findFile(clientMessage[1],new File(path)) );
                   }
                   else{
                       Path userPath = Paths.get("ServerFiles/UserFiles/"+clientMessage[0]);
                       Files.createDirectory(userPath);
                       Path filePath = Paths.get("ServerFiles/UserFiles/"+clientMessage[0]+"/"+clientMessage[1]+".txt");
                       Files.createFile(filePath);
                       loggedIn = true;
                       return("103: account created");
                   }
                   //else if()
               }
               catch(Exception e){
               return("102: "+ e);
               }
             
	}
}
