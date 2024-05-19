package com.manage.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.server.model.Server;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long>{
	
	Server findByIpAddress(String ipAddress);
}
