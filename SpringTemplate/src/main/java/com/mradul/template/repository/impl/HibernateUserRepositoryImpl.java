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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mradul.template.entity.User;
import com.mradul.template.repository.UserRepository;

@Repository
public class HibernateUserRepositoryImpl extends AbstractHibernateRepository
		implements UserRepository {

	@SuppressWarnings("unchecked")
	public User getUser(String name, String password) {
		List<User> userList = new ArrayList<User>();
		Query query = getCurrentSession().createQuery(
				"from User u where u.name = :name and u.password = :password");
		query.setParameter("name", name);
		query.setParameter("password", password);
		userList = query.list();
		if (userList.size() > 0)
			return userList.get(0);
		else
			return null;
	}

}
