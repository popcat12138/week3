package com.gaoyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.OperLog;
import com.gaoyu.repository.OperLogRepository;

@Service
public class OperLogService {
	
	@Autowired
	private OperLogRepository operLogRepository;
	
	public OperLog addOperLog(OperLog operLog) {
		return operLogRepository.save(operLog);
	}
	
	public List<OperLog> findAllOperLogs() {
		return operLogRepository.findAll();
	}
	
	public List<OperLog> findOperLogsByUserId(int userId){
		return operLogRepository.findByUserIs(userId);
	}
	
	public List<OperLog> findLastOperLogs(){
		return operLogRepository.findTop30ByOrderByCreateTime();
	}
	

}
