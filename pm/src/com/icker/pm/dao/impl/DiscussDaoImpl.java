package com.icker.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.icker.pm.dao.DiscussDao;
import com.icker.pm.pojo.Discuss;

@Repository
public class DiscussDaoImpl extends BaseDao<Discuss> implements DiscussDao {

	@Override
	public boolean save(Discuss discuss) throws Exception {
		return super.save(discuss);
	}

	@Override
	public boolean update(Discuss discuss) throws Exception {
		return super.update(discuss);
	}

	@Override
	public boolean remove(Discuss discuss) throws Exception {
		return super.delete(discuss);
	}

	@Override
	public Discuss findById(Discuss discuss) throws Exception {
		return (Discuss) super.findById(Discuss.class, discuss.getId());
	}

	@Override
	public Discuss findById(String id) throws Exception {
		return (Discuss) super.findById(Discuss.class, id);
	}

}
