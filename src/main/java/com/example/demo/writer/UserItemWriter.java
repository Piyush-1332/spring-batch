package com.example.demo.writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Configuration
public class UserItemWriter {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public ItemWriter<User> writer() {
        return new ItemWriter<User>() {
			@Override
			public void write(Chunk<? extends User> users) throws Exception {
				userRepository.saveAll(users);
				
			}
        };
    }
}