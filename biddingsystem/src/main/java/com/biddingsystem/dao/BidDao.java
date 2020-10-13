package com.biddingsystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biddingsystem.dto.BidDto;

@Repository
public interface BidDao extends JpaRepository<BidDto, Long> {

	public BidDto findByItemCode(String itemCode);

	public List<BidDto> findByBidStatus(String bidStatus);

	@Modifying
	@Query("update BidDto u set u.bidStatus = :status where u.itemCode = :itemCode")
	public int updateBiddtoSetBidStatusForItemCode(@Param("status") String status,
			@Param("itemCode") String itemCode);

}
