package com.tourismapp.backend.entity;

public class Coord {
	private static final double EARTH_RADIUS = 6378137;
	private double latitude;
	private double longitude;

	public Coord(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double distance(Coord that) {
		double radLat1 = latitude * Math.PI / 180.0;
		double radLat2 = that.latitude * Math.PI / 180.0;
		double radLng1 = longitude * Math.PI / 180.0;
		double radLng2 = that.longitude * Math.PI / 180.0;
		double a = Math.sin((radLat1 - radLat2) / 2);
		double b = Math.sin((radLng1 - radLng2) / 2);
		double s = 2 * Math.asin(Math.sqrt(a * a + Math.cos(radLat1) * Math.cos(radLat2) * b * b));
		s = s * EARTH_RADIUS;
		return s;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
