package com.colobu.spring_kafka_demo;

import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

public class Producer {
	private static final String CONFIG = "/context.xml";
	private static Random rand = new Random();

	public static void main(String[] args) {
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, Producer.class);
		ctx.start();

		final MessageChannel channel = ctx.getBean("inputToKafka", MessageChannel.class);

		for (int i = 0; i < 100; i++) {
			channel.send(MessageBuilder.withPayload("Message-" + rand.nextInt()).setHeader("messageKey", String.valueOf(i)).setHeader("topic", "test").build());
		}

		// // sending 5,000 messages to kafka server for topic test2
		// for (int i = 0; i < 50; i++) {
		// channel.send(MessageBuilder.withPayload("hello Fom ob adapter test2 - "
		// + i).setHeader("messageKey", String.valueOf(i)).setHeader("topic",
		// "test2")
		// .build());
		// }
		//
		// // Send some messages to multiple topics matching regex.
		// for (int i = 0; i < 10; i++) {
		// channel.send(MessageBuilder.withPayload("hello Fom ob adapter regextopic1 - "
		// + i).setHeader("messageKey", String.valueOf(i))
		// .setHeader("topic", "regextopic1").build());
		// }
		// for (int i = 0; i < 10; i++) {
		// channel.send(MessageBuilder.withPayload("hello Fom ob adapter regextopic2 - "
		// + i).setHeader("messageKey", String.valueOf(i))
		// .setHeader("topic", "regextopic2").build());
		// }

		ctx.close();
	}
}
