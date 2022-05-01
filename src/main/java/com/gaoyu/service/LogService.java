package com.gaoyu.service;

import java.util.List;

import com.gaoyu.entity.OperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.Log;
import com.gaoyu.repository.LogRepository;

@Service
public class LogService {
	
	@Autowired
	private LogRepository logRepository;
	
	public Log addLog(Log log) {
		System.out.println("slslsl");
		return logRepository.save(log);
	}
	
	public List<Log> findAllLogs(){
		return logRepository.findAll();
	}

	public List<Log> findByUserIs(int userId){
		return logRepository.findByUserIs(userId);
	}

	public Page<Log> getLogList(int pageNum, int pageSize){

		Pageable pageable= PageRequest.of(pageNum,pageSize, Sort.by(Sort.Direction.DESC,"createTime"));
		Page<Log> logs=logRepository.findAll(pageable);
		return logs;

	}
}
