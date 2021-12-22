package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Greeting;
@Service
public interface IGreetingService {
	Greeting greetingMessage();
	String greetingMessageByName(UserDto userDto);
}
