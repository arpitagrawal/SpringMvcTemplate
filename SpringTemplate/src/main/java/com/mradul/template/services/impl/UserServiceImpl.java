/*******************************************************************************
 * Copyright 2013 Mradul Pandey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.mradul.template.services.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mradul.template.entity.Authority;
import com.mradul.template.entity.Role;
import com.mradul.template.entity.User;
import com.mradul.template.modal.AuthorityB;
import com.mradul.template.repository.AuthorityRepository;
import com.mradul.template.repository.RoleRepository;
import com.mradul.template.repository.UserRepository;
import com.mradul.template.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final String AUTHORITY_PREFIX = "AUTHORITY_";

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private AuthorityRepository authorityRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			RoleRepository roleRepository,
			AuthorityRepository authorityRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.authorityRepository = authorityRepository;
	}

	public Role getRole(int id) {
		return roleRepository.getRole(id);
	}

	public User getUser(String name, String password) {
		return userRepository.getUser(name, password);
	}

	public Authority getAuthority(int id) {
		return authorityRepository.getAuthority(id);
	}

	public Collection<? extends AuthorityB> getAuthorities(Set<Role> roles) {
		Set<AuthorityB> authorities = new HashSet<AuthorityB>();
		for (Role role : roles) {
			for (Authority authority : role.getAuthorities()) {
				AuthorityB produxAuthority = new AuthorityB(authority.getId(),
						AUTHORITY_PREFIX + authority.getName());
				authorities.add(produxAuthority);
			}
		}
		return authorities;
	}

}
