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
package com.mradul.template.repository.impl;

import org.springframework.stereotype.Repository;

import com.mradul.template.entity.Role;
import com.mradul.template.repository.RoleRepository;

@Repository
public class HibernateRoleRepositoryImpl extends AbstractHibernateRepository
		implements RoleRepository {
	public Role getRole(int id) {
		Role role = (Role) getCurrentSession().load(Role.class, id);
		return role;
	}
}
