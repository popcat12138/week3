package com.gaoyu.repository;

import java.util.Date;
import java.util.List;

import javax.xml.crypto.Data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaoyu.entity.OperLog;

public interface OperLogRepository extends JpaRepository<OperLog, Integer> {

	//自定义，查询名字为xxx的用户的操作日志
	List<OperLog> findByUserIs(int userId);
	
	//自定义,查询时间段为xxx到xxx的日志
	List<OperLog> findByCreateTimeBetween(Date date1,Date date2);
	
	//自定义，查询最新30条数据
	List<OperLog> findTop30ByOrderByCreateTime();
}
