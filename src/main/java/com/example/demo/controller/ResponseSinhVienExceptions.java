package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.ctrl.exception.MesseageEntity;
import com.example.demo.service.exception.SinhVienException;

@RestControllerAdvice // cấu hình tự động gửi dữ liệu về client
@RestController
public class ResponseSinhVienExceptions extends ResponseEntityExceptionHandler {
	// hàm sẽ được chạy khi có bất kỳ hàm nào bắn ra SinhVienException
	@ExceptionHandler(SinhVienException.class)
	public ResponseEntity<MesseageEntity> exception() {
		MesseageEntity entity = new MesseageEntity();
		entity.setIt(100);
		entity.setContent("khong thay sinh vien dau ca");
		return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
	}
}
