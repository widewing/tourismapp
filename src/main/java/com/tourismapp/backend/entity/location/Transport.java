package com.tourismapp.backend.entity.location;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transportBase")
public class Transport {

	private Date endTime;

	private TransitStation endTransitStation;
	private Integer id;
	private Date startTime;
	private TransitStation startTransitStation;
	private String transInfo;

	@Column(name = "end_time", nullable = false)
	public Date getEndTime() {
		return endTime;
	}

	@ManyToOne
	@JoinColumn(name = "end_transitst_id", nullable = false)
	public TransitStation getEndTransitStation() {
		return endTransitStation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	public Integer getId() {
		return id;
	}

	@Column(name = "start_time", nullable = false)
	public Date getStartTime() {
		return startTime;
	}

	@ManyToOne
	@JoinColumn(name = "start_transitst_id", nullable = false)
	public TransitStation getStartTransitStation() {
		return startTransitStation;
	}

	@Column(name = "transinfo", length = 255)
	public String getTransInfo() {
		return transInfo;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setEndTransitStation(TransitStation endTransitStation) {
		this.endTransitStation = endTransitStation;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setStartTransitStation(TransitStation startTransitStation) {
		this.startTransitStation = startTransitStation;
	}

	public void setTransInfo(String transInfo) {
		this.transInfo = transInfo;
	}

}
