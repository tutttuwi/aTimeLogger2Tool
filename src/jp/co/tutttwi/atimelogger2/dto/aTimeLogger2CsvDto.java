package jp.co.tutttwi.atimelogger2.dto;

import java.util.Date;

public class aTimeLogger2CsvDto {
	/**
	 * CSVファイルデータ項目
	 */

	// アクティビティの種類
	String actType;
	// 記録時間
	double duration;
	// 開始日時
	Date startDate;
	// 終了日時
	Date endDate;
	// コメント
	String comment;

	/**
	 * getter/setter定義
	 */
	public String getActType() {
		return actType;
	}
	public void setActType(String actType) {
		this.actType = actType;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}



}
