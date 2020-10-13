package com.biddingsystem.service.impl;

import java.util.Date;

import javax.persistence.NonUniqueResultException;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biddingsystem.dao.AuditDao;
import com.biddingsystem.dao.BidDao;
import com.biddingsystem.dto.Audit;
import com.biddingsystem.dto.BidDto;
import com.biddingsystem.dto.ResponseDto;
import com.biddingsystem.exception.BidSystemException;
import com.biddingsystem.service.BidService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BidServiceImpl implements BidService {

	@Autowired
	private BidDao dao;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private AuditDao auditDao;

	@Override
	@Transactional(value = TxType.SUPPORTS, rollbackOn = Exception.class)
	public ResponseDto fetchBid(String itemCode) {

		BidDto bidDto = dao.findByItemCode(itemCode);
		if (bidDto == null) {
			throw new BidSystemException("E0001",
					"No Bid Found with the given item code");
		}
		return new ResponseDto(bidDto, "", false, "");
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = Exception.class)
	public ResponseDto quoteBid(BidDto bidDto) {
		bidDto.setUpdatedDate(new Date());
		return new ResponseDto(dao.save(bidDto), "", false, "");
	}

	@Override
	@Transactional(value = TxType.SUPPORTS, rollbackOn = Exception.class)
	public ResponseDto fetchBidsByStatus(String status, Pageable pageable) {
		return new ResponseDto(dao.findByBidStatus(status), "", false, "");

	}

	@Transactional(value = TxType.REQUIRES_NEW, rollbackOn = Exception.class)
	@Override
	public ResponseDto updateBid(BidDto bidDto) {
		bidDto.setUpdatedDate(new Date());
		BidDto bidDto2 = dao.findByItemCode(bidDto.getItemCode());
		if (bidDto.getBidPrice() == null) {
			throw new BidSystemException("E0002", "Input cannot be null ");
		} else if (bidDto.getBidPrice() <= bidDto2.getBidPrice()) {
			throw new BidSystemException("E0002",
					"BID Amount cannot be less than or eqaul to the current rate");
		} else if (bidDto.getBidPrice() - bidDto2.getBidPrice() < bidDto2
				.getStepRate()) {
			throw new BidSystemException("E0002",
					"BID Amount should be greater than the current amount by step rate ::"
							+ bidDto2.getStepRate());
		}
		bidDto.setStepRate(bidDto2.getStepRate());
		Audit audit = new Audit();
		audit.setDiffentiator("bid");
		audit.setCode(bidDto.getItemCode());
		try {
			audit.setRequest(objectMapper.writeValueAsString(bidDto));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		auditDao.save(audit);
		try {
			dao.save(bidDto);
		} catch (NonUniqueResultException ex) {
			throw new BidSystemException("E00002",
					"Please fetch the new quote price");
		}
		return new ResponseDto("Your bid is quoted successfully", "", false, "");

	}

	@Transactional(value = TxType.REQUIRED, rollbackOn = Exception.class)
	@Override
	public ResponseDto closeBid(BidDto bidDto) {
		bidDto.setUpdatedDate(new Date());
		dao.updateBiddtoSetBidStatusForItemCode("OVER", bidDto.getItemCode());
		return new ResponseDto("BID Closed", "", false, "");
	}

}
