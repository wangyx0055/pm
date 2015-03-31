package com.icker.pm.dao;

import java.util.List;

import com.icker.pm.vo.EmailTimerTaskVO;

public interface TaskDao {
	public List<EmailTimerTaskVO> findEmailVO() throws Exception;
}
