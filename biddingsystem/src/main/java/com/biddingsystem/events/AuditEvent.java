package com.biddingsystem.events;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

//@Component
public class AuditEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4610375718965699644L;

	public AuditEvent(Object source) {
		super(source);
	}

}
