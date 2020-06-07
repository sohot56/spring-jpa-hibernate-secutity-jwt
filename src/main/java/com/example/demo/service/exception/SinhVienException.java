package com.example.demo.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// có thể extend Exception nhưng sẽ phải cấu hình throw excepttion
// extend runtimeException sẽ không phải cấu hình
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SinhVienException extends RuntimeException {

	public SinhVienException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SinhVienException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
