package com.icker.pm.dao;

import com.icker.pm.pojo.Discuss;

public interface DiscussDao {
	public boolean save(Discuss discuss) throws Exception;
	public boolean update(Discuss discuss) throws Exception;
	public boolean remove(Discuss discuss) throws Exception;
	public Discuss findById(Discuss discuss) throws Exception;
	public Discuss findById(String id) throws Exception;
}
