package com.manage.server.service.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.manage.server.enumeration.Status;
import com.manage.server.model.Server;
import com.manage.server.repository.ServerRepository;
import com.manage.server.service.ServerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServerServiceImpl implements ServerService{
	
	@Autowired
	private ServerRepository serverRepository;

	@Override
	public Server saveServerData(Server server) {
		log.info("Saving new server: {}", server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepository.save(server);
	}

	@Override
	public Server pingServer(String ipAddress) throws IOException {
		log.info("Pinging server IP: {}", ipAddress);
		Server server = serverRepository.findByIpAddress(ipAddress);
		InetAddress inetAddress = InetAddress.getByName(ipAddress); 
		server.setStatus(inetAddress.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		serverRepository.save(server);
		return server;
	}

	@Override
	public Collection<Server> getListOfServers(int limit) {
		log.info("Fetching servers");
		return serverRepository.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Server getServer(Long id) {
		log.info("Fetching server by ID: {}", id);
		return serverRepository.findById(id).get();
	}

	@Override
	public Server updateServerData(Server server) {
		log.info("Updating server: {}", server.getName());
		return serverRepository.save(server);
	}

	@Override
	public Boolean deleteServerData(Long id) {
		log.info("Deleting server by ID: {}", id);
		serverRepository.deleteById(id);
		return true;
	}
	
	private String setServerImageUrl() {
		String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("server/image/" + 
		imageNames[new Random().nextInt(4)]).toUriString();
	}
}
