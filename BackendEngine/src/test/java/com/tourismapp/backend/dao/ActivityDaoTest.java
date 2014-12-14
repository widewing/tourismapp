package com.tourismapp.backend.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tourismapp.backend.entity.Activity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml" })
public class ActivityDaoTest {
	@Autowired
	private ActivityDao activityDao;

	@Before
	public void setUp() throws Exception {
		activityDao.deleteAll();
		Activity a1 = new Activity();
		a1.setName("test1");
		a1.setTag("tag1");
		activityDao.save(a1);
		Activity a2 = new Activity();
		a2.setName("test2");
		a2.setTag("tag1");
		activityDao.save(a2);
		Activity a3 = new Activity();
		a3.setName("test3");
		a3.setTag("tag2");
		activityDao.save(a3);
	}

	@Test
	public void testCount() {
		Assert.assertTrue(activityDao.count() > 0);
	}

	@Test
	public void testFindAll() {
		List<Activity> activities = activityDao.findAll();
		Assert.assertNotNull(activities);
		Assert.assertTrue(activities.size() > 0);
	}

}
