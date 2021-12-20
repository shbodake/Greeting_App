package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Greeting;

public interface IGreetingService {
	Greeting greetingMessage();
	String greetingMessageByName(UserDto userDto);
}
