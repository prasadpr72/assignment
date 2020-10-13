package com.biddingsystem.service;

import org.springframework.data.domain.Pageable;

import com.biddingsystem.dto.BidDto;
import com.biddingsystem.dto.ResponseDto;

public interface BidService {

	/**
	 * Fetches bid by item code
	 * 
	 * @param itemCode
	 * @return
	 */
	ResponseDto fetchBid(String itemCode);
	
	/**
	 * 
	 * @param bidDto
	 * @return
	 */
	
	ResponseDto updateBid(BidDto bidDto);


	/**
	 * Creates and Updates the bid value
	 * 
	 * @param bidDto
	 * @return
	 */

	ResponseDto quoteBid(BidDto bidDto);

	/**
	 * fetches bids by status
	 * 
	 * @param status
	 * @return
	 */
	ResponseDto fetchBidsByStatus(String status,Pageable pageable);
	
	/**
	 * 
	 * @param bidDto
	 * @return
	 */
	ResponseDto closeBid(BidDto bidDto);

}
