package com.gaoyu.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.gaoyu.entity.Article;
import com.gaoyu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gaoyu.entity.OperLog;
import com.gaoyu.repository.OperLogRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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

	public Page<OperLog> getOperLogByDate(int pageNum, int pageSize, LocalDateTime date1, LocalDateTime date2){

		Pageable pageable= PageRequest.of(pageNum,pageSize,Sort.by(Sort.Direction.DESC,"createTime"));
		Page<OperLog> operLogs=operLogRepository.findByCreateTimeBetween(date1,date2,pageable);
		return operLogs;

	}

}
