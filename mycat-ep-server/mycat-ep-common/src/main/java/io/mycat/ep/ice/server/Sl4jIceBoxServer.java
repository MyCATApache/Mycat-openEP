package io.mycat.ep.ice.server;

import IceBox.Server;
//

public class Sl4jIceBoxServer {

	public static void main(String[] args)
	{

		  Ice.InitializationData initData = new Ice.InitializationData();
	        initData.properties = Ice.Util.createProperties();
	        initData.properties.setProperty("Ice.Admin.DelayCreation", "1");
	        initData.logger=new Sl4jLogerI("system");

	        Server server = new Server();
	        System.exit(server.main("IceBox.Server", args, initData));
	}
}
