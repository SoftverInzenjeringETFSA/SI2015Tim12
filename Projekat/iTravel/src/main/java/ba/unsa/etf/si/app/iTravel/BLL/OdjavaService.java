package ba.unsa.etf.si.app.iTravel.BLL;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork.UserContext;

public class OdjavaService {
	
	public void OdjaviKorisnika() {
		UserContext.Username = null;
		UserContext.RolaID = 0;
		UserContext.Identitfication = 0;
	}

}
