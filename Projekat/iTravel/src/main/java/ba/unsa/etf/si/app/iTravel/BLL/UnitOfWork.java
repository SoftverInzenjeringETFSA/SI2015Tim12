package ba.unsa.etf.si.app.iTravel.BLL;

public class UnitOfWork 
{
	public static class UserContext
	{
		public static String Username;
		public static int Identitfication;
		public static int RolaID;
		
		public UserContext(String username,int ID, int idRole)
		{
			Username = username;
			Identitfication = ID;
			RolaID = idRole;
		}
	}
	
	private PrijavaService prijavaService;
	
	public UnitOfWork()
	{
		prijavaService = new PrijavaService();
	}

	public PrijavaService getPrijavaService() 
	{
		if(prijavaService == null)
			prijavaService = new PrijavaService();
		
		return prijavaService;
	}
	
	

}
