package com.gaoyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.Log;
import com.gaoyu.repository.LogRepository;

@Service
public class LogService {
	
	@Autowired
	private LogRepository logRepository;
	
	public Log addLog(Log log) {
		return logRepository.save(log);
	}
	
	public List<Log> findAllLogs(){
		return logRepository.findAll();
	}

	public List<Log> findByUserIs(int userId){
		return logRepository.findByUserIs(userId);
	}
}
