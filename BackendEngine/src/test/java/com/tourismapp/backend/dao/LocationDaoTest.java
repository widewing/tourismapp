package com.tourismapp.backend.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tourismapp.backend.entity.location.Location;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml" })
public class LocationDaoTest {

	@Autowired
	private LocationDao locationDao;

	@Before
	public void setUp() throws Exception {
		Location l1 = new Location();
		l1.setBaiduCode("123");
		l1.setName("test");
		locationDao.save(l1);
		Location l2 = locationDao.findByBaiduCode("123");
		Assert.assertNotNull(l2);
		Assert.assertEquals(l1.getBaiduCode(), l2.getBaiduCode());
		Assert.assertEquals(l1.getId(), l2.getId());
	}

	@Test
	public void testCount() {
		long count = locationDao.count();
		Assert.assertEquals(1, count);
	}

}
