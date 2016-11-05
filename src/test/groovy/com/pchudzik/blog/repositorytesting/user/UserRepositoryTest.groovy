package com.pchudzik.blog.repositorytesting.user

import com.pchudzik.blog.repositorytesting.RepositorySpecification
import org.springframework.beans.factory.annotation.Autowired

class UserRepositoryTest extends RepositorySpecification {
	@Autowired
	UserRepository userRepository

	@Autowired
	RoleRepository roleRepository

	def "database should be truncated before tests"() {
		expect:
		userRepository.findAll().isEmpty()
	}

	def "should login user by exact password match"() {
		given:
		final password = "pass"
		final user = userRepository.saveAndFlush(new User(login: "login", password: password))

		expect:
		userRepository.login(user.login, password).id == user.id

		and:
		userRepository.login(user.login, password.toUpperCase()) == null
	}

	def "login should be case insensitive"() {
		given:
		final user = new User(login: "newuser", password: "newuser")
		userRepository.saveAndFlush(user)

		when:
		final loggedUser = userRepository.login("NEWUser", "newuser")

		then:
		loggedUser.login == user.login
	}

	def "should detect if user has role"() {
		given:
		final role = new Role(name: "role 1")
		final otherRole = new Role(name: "otherRole")
		final user = new User(
				login: "login",
				password: "password",
				roles: [role] as Set)

		and:
		roleRepository.save([role, otherRole])
		userRepository.saveAndFlush(user)

		expect:
		userRepository.userHasRole(user.login, role) == true
		userRepository.userHasRole(user.login, otherRole) == false
	}
}
