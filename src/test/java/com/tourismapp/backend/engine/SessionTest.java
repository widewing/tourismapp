package com.tourismapp.backend.engine;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.tourismapp.backend.dto.location.CityDto;
import com.tourismapp.backend.dto.location.District;
import com.tourismapp.backend.dto.location.HotelDto;
import com.tourismapp.backend.dto.location.Location;
import com.tourismapp.backend.dto.location.RestaurantDto;
import com.tourismapp.backend.dto.location.SceneryDto;
import com.tourismapp.backend.dto.location.TransitStationDto;
import com.tourismapp.backend.entity.Coord;
import com.tourismapp.backend.service.HotelDataService;

public class SessionTest {
	private Location home;
	private District hangzhou,shenzhen,hongkong,macao;
	private SceneryDto sc_h1,sc_h2,sc_h3,sc_m1,sc_m2;
	private RestaurantDto re_h1,re_h2,re_h3,re_m1,re_m2;
	private HotelDto ho_h1,ho_h2,ho_m1,ho_m2;
	private TransitStationDto hk_airport,hk_dock,mc_dock,sz_station,hk_station,sz_airport,hz_airport;
	
	@Before
	public void init(){
		hangzhou = new CityDto();
		shenzhen = new CityDto();
		hongkong = new CityDto();
		macao = new CityDto();
		
		hk_airport = new TransitStationDto(TransitStationDto.Type.AirPort);
		hk_airport.setBelongTo(hongkong);
		hk_airport.setCoord(new Coord(22.30889,113.91444));
		hk_airport.setName("Hongkong Airport");
		hz_airport = new TransitStationDto(TransitStationDto.Type.AirPort);
		hz_airport.setBelongTo(hangzhou);
		hz_airport.setCoord(new Coord(30.22944,120.43444));
		hz_airport.setName("Hangzhou Airport");
		sz_airport = new TransitStationDto(TransitStationDto.Type.AirPort);
		sz_airport.setBelongTo(shenzhen);
		sz_airport.setCoord(new Coord(22.639444,113.810833));
		sz_airport.setName("Shenzhen Airport");
		
		hk_dock = new TransitStationDto(TransitStationDto.Type.Dock);
		hk_dock.setBelongTo(hongkong);
		hk_dock.setCoord(new Coord(22.372956, 113.965993));
		hk_dock.setName("Hongkong Dock");
		mc_dock = new TransitStationDto(TransitStationDto.Type.Dock);
		mc_dock.setBelongTo(macao);
		mc_dock.setCoord(new Coord(22.198018, 113.558360));
		mc_dock.setName("Macao Dock");
		
		sz_station = new TransitStationDto(TransitStationDto.Type.BusStation);
		sz_station.setBelongTo(shenzhen);
		sz_station.setCoord(new Coord(22.532644, 114.013993));
		sz_station.setName("Shenzhen Bus Station");
		hk_station = new TransitStationDto(TransitStationDto.Type.BusStation);
		hk_station.setBelongTo(hongkong);
		hk_station.setCoord(new Coord(22.330532, 114.145426));
		hk_station.setName("Hongkong Bus Station");
		
		home = new Location();
		home.setCoord(new Coord(30.1,120.1));
		home.setName("Home");
		home.setBelongTo(hangzhou);
		
		sc_h1 = new SceneryDto();
		sc_h1.setBelongTo(hongkong);
		sc_h1.setName("Hongkong Scenery 1");
		sc_h1.setCoord(new Coord(22.325715, 114.201473));
		sc_h1.setOpenTime(8.5f);
		sc_h1.setCloseTime(18.0f);
		sc_h1.setPrice(100);
		sc_h1.setRank(3);
		sc_h1.setVisitMinutes(120);
		
		sc_h2 = new SceneryDto();
		sc_h2.setBelongTo(hongkong);
		sc_h2.setName("Hongkong Scenery 2");
		sc_h2.setCoord(new Coord(22.328891, 114.174651));
		sc_h2.setOpenTime(8.5f);
		sc_h2.setCloseTime(18.0f);
		sc_h2.setPrice(100);
		sc_h2.setRank(2);
		sc_h2.setVisitMinutes(120);
		
		sc_h3 = new SceneryDto();
		sc_h3.setBelongTo(hongkong);
		sc_h3.setName("Hongkong Scenery 3");
		sc_h3.setCoord(new Coord(22.267301, 114.128953));
		sc_h3.setOpenTime(8.5f);
		sc_h3.setCloseTime(18.0f);
		sc_h3.setPrice(100);
		sc_h3.setRank(1);
		sc_h3.setVisitMinutes(120);
		
		sc_m1 = new SceneryDto();
		sc_m1.setBelongTo(macao);
		sc_m1.setName("Macao Scenery 1");
		sc_m1.setCoord(new Coord(22.193105, 113.549773));
		sc_m1.setOpenTime(8.5f);
		sc_m1.setCloseTime(18.0f);
		sc_m1.setPrice(100);
		sc_m1.setRank(3);
		sc_m1.setVisitMinutes(120);
		
		sc_m2 = new SceneryDto();
		sc_m2.setBelongTo(macao);
		sc_m2.setName("Macao Scenery 2");
		sc_m2.setCoord(new Coord(22.140167, 113.570200));
		sc_m2.setOpenTime(8.5f);
		sc_m2.setCloseTime(18.0f);
		sc_m2.setPrice(100);
		sc_m2.setRank(3);
		sc_m2.setVisitMinutes(120);
	
	}
	public static class HotelDataServiceTestImpl extends HotelDataService{
		public Collection<HotelDto> getAvailableHotels(Coord coord, int radius, Date time) {
			return null;
		}
	}
	@Test
	public void test(){
		final Session session = new Session();
		session.setStartLocation(home);
		session.setEndLocation(home);
		Calendar cal = Calendar.getInstance();
		session.setStartDate(cal.getTime());
		cal.add(Calendar.DATE, 3);
		session.setEndDate(cal.getTime());
		session.setSelectedSceneries(Arrays.asList(new SceneryDto[]{sc_h1,sc_h2,sc_h3,sc_m1,sc_m2}));
		
		session.makeTravelRoute();
	}
}
