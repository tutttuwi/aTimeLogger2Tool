package jp.co.tutttwi.atimelogger2.dto;

import java.util.Date;

public class gCalImportCsvDto {
	/**
	 * CSVファイルデータ項目
	 */

	// スケジュールの件名
	String Subject;
	// 開始日時
	Date startDate;
	// 開始日時
	Date startTime;
	// 終了日時
	Date endDate;
	// 終了日時
	Date endTime;
	// 終日設定
	boolean allDayEventFlag;
	// メモ
	String description;
	// 場所
	String location;
	// 公開・非公開
	boolean privateFlag;


	/**
	 *  getter/setter定義
	 */
	
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public boolean isAllDayEventFlag() {
		return allDayEventFlag;
	}
	public void setAllDayEventFlag(boolean allDayEventFlag) {
		this.allDayEventFlag = allDayEventFlag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isPrivateFlag() {
		return privateFlag;
	}
	public void setPrivateFlag(boolean privateFlag) {
		this.privateFlag = privateFlag;
	}


}
