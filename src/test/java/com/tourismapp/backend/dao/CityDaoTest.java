package com.tourismapp.backend.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tourismapp.backend.entity.location.City;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml" })
public class CityDaoTest {
	@Autowired
	private CityDao cityDao;

	@Before
	public void setUp() throws Exception {
		cityDao.deleteAll();
		City a1 = new City();
		a1.setBaiduCode("1");
		a1.setName("test1");
		a1.setDestinationStatus(true);
		a1.setProvince("anhui");
		a1.setFirstLetter("A");
		cityDao.save(a1);
		City a2 = new City();
		a2.setBaiduCode("2");
		a2.setName("test2");
		a2.setDestinationStatus(true);
		a2.setProvince("anhui");
		a2.setFirstLetter("B");
		cityDao.save(a2);
		City a3 = new City();
		a3.setBaiduCode("3");
		a3.setName("test3");
		a3.setDestinationStatus(true);
		a3.setProvince("anhui");
		a3.setFirstLetter("A");
		cityDao.save(a3);
	}

	@Test
	public void test() {
		List<City> ret = cityDao.findAllOrderByFirstLetter();
		Assert.assertNotNull(ret);
	}

}
