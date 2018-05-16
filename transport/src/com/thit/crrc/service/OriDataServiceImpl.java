package com.thit.crrc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thit.crrc.bean.OriDataBean;
import com.thit.crrc.dao.OriDataDao;

/**
 *@filename : OriDataServiceImpl.java
 *@description:
 *
 *@author : sf
 *@time:2017年9月4日 下午2:31:57
 */


@Service("oriDataService")
@Transactional
public class OriDataServiceImpl implements OriDataService {
	
	@Autowired
	private OriDataDao oriDataDao;

/*	@Override
	public void saveOriDataBean(String msgType,String content,String msgFrom,String status) throws Exception {
		
		Connection conn= JDBCUtils.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into BOM_RT_LATEST_FILE_ORI(msgType,msgContent,msgFrom,msgStatus,storagetime) values(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
		
		
		pst.setString(1, msgType);
		pst.setString(2, content);
		pst.setString(3, msgFrom);
		pst.setString(4, status);
		pst.setTimestamp(5,new Timestamp(new Date().getTime()));
	
		pst.executeUpdate();	
		JDBCUtils.closeAll(conn, pst);
	}
*/
	@Override
	public void saveOriDataBean(OriDataBean oriDataBean) throws Exception {
		
		oriDataDao.saveOriDataBean(oriDataBean);
	}

	@Override
	public List<OriDataBean> queryList() throws Exception {
		
		return oriDataDao.queryList();
		
	}

	@Override
	public void updateMsgStatus(OriDataBean oriDataBean) throws Exception {
		
		oriDataDao.updateMsgStatus(oriDataBean);
		
	}
}
