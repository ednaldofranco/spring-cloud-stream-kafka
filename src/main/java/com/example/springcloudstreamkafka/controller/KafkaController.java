package com.example.springcloudstreamkafka.controller;

import com.example.springcloudstreamkafka.engine.Producer;
import io.confluent.developer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class KafkaController {
	private final Producer producer;

	@Autowired
	public KafkaController(Producer producer) {
		this.producer = producer;
	}

	@GetMapping("/publish/{name}/name/{age}/age")
	public void sendeMessageToKafkaTopic(@PathVariable("name") String name,
										 @PathVariable("age") Integer age) {
		User user = User.newBuilder()
				.setAge(34)
				.setName("Ednaldo Franco")
				.build();

		this.producer.sendMessage(user);
	}
}
