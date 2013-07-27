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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mradul.template.modal.Product;
import com.mradul.template.repository.ProductRepository;
import com.mradul.template.repository.exception.DataAccessException;

public class InMemoryProductRepository implements ProductRepository {
	private static Map<Integer, Product> products = Collections
			.synchronizedMap(new HashMap<Integer, Product>());

	public List<Product> getAll() {
		return new ArrayList<Product>(products.values());
	}

	public Product findProductById(Integer id) throws DataAccessException {
		return nullSafeAccountLookup(id);
	}

	public Product deleteProduct(Integer id) throws DataAccessException {
		Product product = nullSafeAccountLookup(id);
		return products.remove(product);
	}

	public Product addProduct(Product product) {
		return products.put(product.getId(), product);
	}

	private Product nullSafeAccountLookup(Integer id) throws DataAccessException {
		Product product = products.get(id);
		if (product == null)
			throw new DataAccessException(Integer.toString(id));
		return product;
	}
}
