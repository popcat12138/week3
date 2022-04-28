package com.gaoyu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public Page<OperLog> getOperLogList(int pageNum,int pageSize){

		Pageable pageable= PageRequest.of(pageNum,pageSize,Sort.by(Sort.Direction.DESC,"createTime"));
		Page<OperLog> operLogs=operLogRepository.findAll(pageable);
		return operLogs;

	}

}
