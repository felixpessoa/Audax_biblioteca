package com.audax.biblioteca.api.controller.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String msg;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime timeStamp;

	public StandardError(Integer status, String msg, LocalDateTime timeStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

}
