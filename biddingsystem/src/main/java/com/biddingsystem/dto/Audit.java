package com.biddingsystem.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long auditId;

	private String request;

	private String diffentiator;

	@JsonInclude(value = Include.NON_NULL)
	private String code;

	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getDiffentiator() {
		return diffentiator;
	}

	public void setDiffentiator(String diffentiator) {
		this.diffentiator = diffentiator;
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Audit [auditId=" + auditId + ", request=" + request
				+ ", diffentiator=" + diffentiator + ", code=" + code + "]";
	}

}
