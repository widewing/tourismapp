package com.tourismapp.backend.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tourismapp.backend.dto.location.CityDto;
import com.tourismapp.backend.dto.location.SceneryDto;
import com.tourismapp.backend.entity.location.BaseLocationEntity;
import com.tourismapp.backend.entity.location.City;
import com.tourismapp.backend.entity.location.Scenery;

public class DtoUtils<CDTO, CEntity> {
	public static DtoUtils<CityDto, City> cityDtoUtil = new DtoUtils<CityDto, City>(CityDto.class, City.class);
	private static final Logger logger = LoggerFactory.getLogger(DtoUtils.class);
	public static DtoUtils<SceneryDto, Scenery> sceneryDtoUtil = new DtoUtils<SceneryDto, Scenery>(SceneryDto.class,
			Scenery.class);
	private Class<?> dtoClass;
	private Class<?> entityClass;

	public DtoUtils(Class<?> dtoClass, Class<?> entityClass) {
		super();
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
	}

	public Map<String, List<CDTO>> groupByFirstLetter(List<CEntity> entities) {
		Map<String, List<CDTO>> result = new HashMap<String, List<CDTO>>();
		for (CEntity entity : entities) {
			List<CDTO> tmpCities = result.get(((BaseLocationEntity) entity).getFirstLetter());
			if (tmpCities == null)
				tmpCities = new ArrayList<CDTO>();
			tmpCities.add(newDtoInstance(entity));
			result.put(((BaseLocationEntity) entity).getFirstLetter(), tmpCities);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private CDTO newDtoInstance(CEntity entity) {
		try {
			return (CDTO) dtoClass.getConstructor(entityClass).newInstance(entity);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public List<CDTO> toDTO(List<CEntity> listSource) {
		List<CDTO> result = new ArrayList<CDTO>();
		for (CEntity entity : listSource)
			result.add(newDtoInstance(entity));

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<CEntity> toEntity(List<CDTO> listSource) {
		List<CEntity> result = new ArrayList<CEntity>();
		for (CDTO dto : listSource)
			try {
				Method method = dto.getClass().getMethod("toEntity");
				result.add((CEntity) method.invoke(dto));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		return result;
	}

}
