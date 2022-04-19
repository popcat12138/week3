package com.gaoyu.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaoyu.entity.Log;


public interface LogRepository extends JpaRepository<Log, Integer> {
	
	//自定义，查询名字为xxx的用户的操作日志
	List<Log> findByUserIs(int userId);
	
	//自定义,查询时间段为xxx到xxx的日志
	List<Log> findByCreateTimeBetween(Date date1,Date date2);
	
	//自定义，查询最新30条数据
	List<Log> findTop30ByOrderByCreateTime();
	
}
