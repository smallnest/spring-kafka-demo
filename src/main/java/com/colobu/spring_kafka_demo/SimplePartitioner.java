package com.colobu.spring_kafka_demo;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class SimplePartitioner implements Partitioner<String> {

	public SimplePartitioner () {
		 
    }
 
	
	public SimplePartitioner (VerifiableProperties props) {
		 
    }
 
	
	public int partition(String key, int numPartitions) {
		final String s = (String) key;
		final Integer i = Integer.parseInt(s);
		return i % numPartitions;
	}

}
