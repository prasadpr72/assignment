package com.biddingsystem.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import com.biddingsystem.dao.AuditDao;
import com.biddingsystem.dto.Audit;
import com.biddingsystem.dto.BidDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuditEventListener implements ApplicationListener<AuditEvent> {

	@Autowired
	private AuditDao auditDao;

	@Override
	public void onApplicationEvent(AuditEvent event) {
	if(event.getSource() instanceof BidDto){
		BidDto bidDto  = (BidDto) event.getSource(); 
		ObjectMapper mapper = new ObjectMapper();
		Audit audit = new Audit();
		audit.setDiffentiator("bid");
		audit.setCode(bidDto.getItemCode());
		try {
			audit.setRequest(mapper.writeValueAsString(bidDto));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auditDao.save(audit);
		
	}
	}

}
