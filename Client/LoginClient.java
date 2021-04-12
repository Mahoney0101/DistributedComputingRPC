import java.net.*;

public class LoginClient
{
	public static void main(String[] argv)
	{
		LOGINPROG client;
		if(argv.length <2)
		{	System.out.println("Usage: java LoginClient host text");
			System.exit(0);
		}		
		try
		{		
		client = new LOGINPROG(InetAddress.getByName(argv[0]),true);
    		String result = client.login_1(argv[1]);
		System.out.println(result);
		}
		catch(Exception e)
		{
		  System.out.println("\n " + e.getMessage());

		}
		System.exit(0);
	 }
}
