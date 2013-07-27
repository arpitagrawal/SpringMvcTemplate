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
package com.mradul.template.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mradul.template.entity.User;
import com.mradul.template.services.UserService;

@Component
@Transactional(readOnly = true)
public class TemplateAuthProvider implements AuthenticationProvider {
	@Autowired
	private UserService userService;

	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		User user = userService.getUser(authentication.getPrincipal()
				.toString(), authentication.getCredentials().toString());

		if (user == null) {
			throw new UsernameNotFoundException(String.format(
					"Invalid credentials", authentication.getPrincipal()));
		}

		/*
		 * String suppliedPasswordHash = DigestUtils.shaHex(authentication
		 * .getCredentials().toString());
		 *
		 * if (!user.getPassword().equals(suppliedPasswordHash)) { throw new
		 * BadCredentialsException("Invalid credentials"); }
		 */

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user, null, userService.getAuthorities(user.getRoles()));

		return token;
	}

	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
