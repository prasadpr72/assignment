package com.biddingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biddingsystem.dto.BidDto;
import com.biddingsystem.dto.ResponseDto;
import com.biddingsystem.dto.User;
import com.biddingsystem.exception.BidSystemException;
import com.biddingsystem.exception.UnAuthorizedException;
import com.biddingsystem.security.MyUserPrincipal;
import com.biddingsystem.service.impl.BidServiceImpl;

@RestController
@RequestMapping("v1")
public class BidSystemController {

	@Autowired
	private BidServiceImpl bidServiceImpl;

	@PostMapping("addBid")
	public ResponseEntity<ResponseDto> addBid(@RequestBody BidDto bidDto,
			Authentication principal) {
		User user = ((MyUserPrincipal) principal.getPrincipal()).getUser();
		if(bidServiceImpl.fetchBid(bidDto.getItemCode())!=null){
			throw new BidSystemException("E0005", "Duplicate Bid");
		}
		if (user.getRole().equals("admin")) {
			bidDto.setLastUpdatedBy(user.getUsername());
			return ResponseEntity.status(HttpStatus.CREATED).body(
					bidServiceImpl.quoteBid(bidDto));
		} else
			throw new UnAuthorizedException("U0001", "UnAuthorized");
	}

	@GetMapping("fetchBid/{itemCode}")
	public ResponseEntity<ResponseDto> fetchBid(@PathVariable String itemCode,
			Authentication principal) {
		return ResponseEntity.status(HttpStatus.OK).body(
				bidServiceImpl.fetchBid(itemCode));
	}

	@GetMapping("fetchBid/start/end")
	public ResponseEntity<ResponseDto> fetchBidByStatus(
			@RequestParam String status,
			@PathVariable(required = true, name = "start") Integer start,
			@PathVariable(required = true, name = "end") Integer end) {
		Pageable page = PageRequest.of(start, end);
		return ResponseEntity.status(HttpStatus.OK).body(
				bidServiceImpl.fetchBidsByStatus(status, page));
	}

	@GetMapping("fetchBid")
	public ResponseEntity<ResponseDto> fetchBidByStatus(
			@RequestParam String status) {
		Pageable page = PageRequest.of(0, 10);
		return ResponseEntity.status(HttpStatus.OK).body(
				bidServiceImpl.fetchBidsByStatus(status, page));
	}

	@PutMapping("updatebid/{id}")
	public ResponseEntity<ResponseDto> updateBid(@PathVariable Long id,
			@RequestBody BidDto bidDto, Authentication principal) {
		bidDto.setBidId(id);
		User user = ((MyUserPrincipal) principal.getPrincipal()).getUser();
		bidDto.setLastUpdatedBy(user.getUsername());
		if (user.getRole().equals("admin")) {
			throw new UnAuthorizedException("U0001",
					"Admin cannot update the value");
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				bidServiceImpl.updateBid(bidDto));
	}

	@PutMapping("closebid/{id}")
	public ResponseEntity<ResponseDto> closeBid(@PathVariable Long id,
			@RequestBody BidDto bidDto, Authentication principal) {
		bidDto.setBidId(id);
		bidDto.setBidStatus("OVER");
		User user = ((MyUserPrincipal) principal.getPrincipal()).getUser();
		bidDto.setLastUpdatedBy(user.getUsername());
		if (user.getRole().equals("user")) {
			throw new UnAuthorizedException("U0001",
					"user cannot close the bid");
		}
		return ResponseEntity.status(HttpStatus.OK).body(
				bidServiceImpl.closeBid(bidDto));
	}
}
