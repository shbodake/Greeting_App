package com.example.demo.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.model.Greeting;
import com.example.demo.repository.IGreetingRepository;
import com.example.demo.service.IGreetingService;


/**
 * @RestController
 * @RequestMapping: mapping web requests onto methods in request-handling
 * classes with flexible method signatures.
 * @GetMapping: mapping HTTP GET requests onto specific handler methods
 * @RequestParam: method parameter should be bound to a web request parameter.
 * @PathVariable: method parameter should be bound to a URI template variable.
 * Supported for RequestMapping annotated handler methods.
 */

@RestController
@RequestMapping("/greeting")
public class GreetingController {
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /*---___
     *curl localhost:8080/greeting
     @return={id =1 , content="hello world!"}
     * curl localhost:8080/greeting?name=sayali
     * @return= { id=2, content="hello sayali!" }
     */
    
    @GetMapping(value={"","/","/home"})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    /*
    *curl localhost:8080/greeting/sayali
    @return={id =1 , content="hello sayali!}
     */
    
    /**
	 * Call post method to post details through JSON
	 * @param name
	 * @return
	 */
	@PostMapping("/postDetails")
	public Greeting greetings(@RequestParam(value="name", defaultValue="World") String name) {
		return new Greeting (counter.incrementAndGet(),String.format(template, name));
	}
	
	/**
	 * Call put method to update details of JSON file
	 * @param name
	 * @return
	 */
	@PutMapping("/update")
	public Greeting greet(@RequestParam(value="name", defaultValue="World") String name) {
		return new Greeting (counter.incrementAndGet(),String.format(template, name));
	}
	
	/**
	 * Use service layer 
	 */
    
    @Autowired
    private IGreetingService greetingService;
    
    @GetMapping("/service")
	public Greeting greeting() {
	     return greetingService.greetingMessage();
	}
	
	@PostMapping("/greet")
	public String greetingMessageByName(@RequestBody UserDto userDto) {
		return greetingService.greetingMessageByName(userDto);
	}
	
	@GetMapping("/service/{messageId}")
	public Greeting findById(@PathVariable String messageId) {
		return this.greetingService.findById(Long.parseLong(messageId));
	}
	
	/**
	 * Call method to list all the messages
	 */
	@GetMapping("/greetinglist")
	public List<Greeting> getAllGreetings() {
		return greetingService.getAllGreetings();
	}
	
	/**
	 * Call method to edit message
	 */
	@PutMapping("/editmessage")
	public Greeting editMessage(Greeting greeting) {
		return this.greetingService.editMessage(greeting);
	}
}
