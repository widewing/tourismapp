package com.tourismapp.backend.dao;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

public interface BaseDao<T, ID extends Serializable> extends Repository<T, ID> {
	/**
	 * Returns the number of entities available.
	 *
	 * @return the number of entities
	 */
	long count();

	/**
	 * Deletes the entity with the given id.
	 *
	 * @param id
	 *            must not be {@literal null}.
	 * @throws IllegalArgumentException
	 *             in case the given {@code id} is {@literal null}
	 */
	void delete(ID id);

	/**
	 * Deletes a given entity.
	 *
	 * @param entity
	 * @throws IllegalArgumentException
	 *             in case the given entity is (@literal null}.
	 */
	void delete(T entity);

	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	T findOne(ID id);

	/**
	 * Saves a given entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 *
	 * @param entity
	 * @return the saved entity
	 */
	<S extends T> S save(S entity);
}
