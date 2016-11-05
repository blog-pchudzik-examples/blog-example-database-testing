package com.pchudzik.blog.repositorytesting.user;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_")
@ToString(exclude = {"password", "roles"})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Getter
	private String login;

	private String password;

	@ManyToMany
	private Set<Role> roles = new HashSet<>();
}
