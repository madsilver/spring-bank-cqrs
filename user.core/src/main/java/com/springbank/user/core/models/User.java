package com.springbank.user.core.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class User {
	@Id
	private String id;
	private String firstname;
	private String lastname;
	private String enailAddress;
	private Account account;
	
}
