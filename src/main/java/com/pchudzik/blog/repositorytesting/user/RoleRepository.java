package com.pchudzik.blog.repositorytesting.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface RoleRepository extends JpaRepository<Role, Long> {
}
