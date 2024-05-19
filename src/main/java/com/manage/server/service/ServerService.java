package com.manage.server.service;

import java.io.IOException;
import java.util.Collection;

import com.manage.server.model.Server;

public interface ServerService {
	
	Server saveServerData(Server server);
	Server pingServer(String ipAddress) throws IOException;
	Collection<Server> getListOfServers(int limit);
	Server getServer(Long id);
	Server updateServerData(Server server);
	Boolean deleteServerData(Long id);
}
