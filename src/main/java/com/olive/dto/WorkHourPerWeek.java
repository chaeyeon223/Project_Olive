package com.olive.dto;


import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class WorkHourPerWeek {
	private String startOfWeek;
	private String endOfWeek;
	private int empno;
	private int totalHour;
	private int totalMinutes;
}
