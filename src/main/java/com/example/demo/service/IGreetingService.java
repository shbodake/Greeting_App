package com.example.demo.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Greeting;

@Service
public interface IGreetingService {
	Greeting greetingMessage();
	String greetingMessageByName(UserDto userDto);
	Greeting findById(long messageId);
	List<Greeting> getAllGreetings();
}
