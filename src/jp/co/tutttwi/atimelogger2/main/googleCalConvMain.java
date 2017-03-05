package jp.co.tutttwi.atimelogger2.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import jp.co.tutttwi.atimelogger2.dto.aTimeLogger2CsvDto;
import jp.co.tutttwi.atimelogger2.dto.gCalImportCsvDto;

public class googleCalConvMain {
	// アプリログDTO
	List<aTimeLogger2CsvDto> timeLogDtoList = new ArrayList<>();
	// グーグルカレンダーDTO
	List<gCalImportCsvDto> gCalDtoList = new ArrayList<>();
	// DateFormat
	DateFormat logDf = new SimpleDateFormat("MM月dd日 HH:mm");
	DateFormat gCalDateDf = new SimpleDateFormat("yyyy/MM/dd");
	DateFormat gCalTimeDf = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		try {
			// 処理メソッド実行
			new googleCalConvMain().Exec();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 処理メソッド
	 * @throws Exception
	 */
	public void Exec() throws Exception{
		// TODO 自動生成されたメソッド・スタブ
		CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("report.csv"), "UTF-8"));
		List<String[]> lines = reader.readAll();
		data: for (String[] strings : lines) {
			int col = 0;
			aTimeLogger2CsvDto dto = new aTimeLogger2CsvDto();
			for (String string : strings) {
				col++;
				if (timeLogDtoList.size() == 0) {
					// header行スキップ
					break;
				}
				switch (col) {
				case 1:
					dto.setActType(string);
					break;
				case 2:
					try{
						// 2カラム目でキャストに失敗した場合、集計行判定
						double t = Double.parseDouble(string);
						dto.setDuration(t);
					} catch (NumberFormatException ex) {
						//スタックトレースするとエラーで落ちるのでデバッグ用
						//ex.printStackTrace();
						break data;
					}
					break;
				case 3:
					Calendar startCal = Calendar.getInstance();
					startCal.setTime(logDf.parse(string));
					startCal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
					dto.setStartDate(startCal.getTime());
					break;
				case 4:
					Calendar endCal = Calendar.getInstance();
					endCal.setTime(logDf.parse(string));
					endCal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
					dto.setEndDate(endCal.getTime());
					break;
				case 5:
					dto.setComment(string);
					break;
				}
			}
			timeLogDtoList.add(dto);
		}

		for (aTimeLogger2CsvDto dto : timeLogDtoList){
			gCalImportCsvDto gCalDto = new gCalImportCsvDto();
			gCalDto.setSubject(dto.getActType());
			gCalDto.setStartDate(dto.getStartDate());
			gCalDto.setStartTime(dto.getStartDate());
			gCalDto.setEndDate(dto.getEndDate());
			gCalDto.setEndTime(dto.getEndDate());
			gCalDto.setAllDayEventFlag(false);
			gCalDto.setDescription(dto.getComment());
			gCalDto.setLocation(null);
			gCalDto.setPrivateFlag(true);
			gCalDtoList.add(gCalDto);
		}

		List<String[]> writeList = new ArrayList<>();
		// ヘッダー行作成
		String[] header = new String[]{"Subject","Start Date","Start Time","End Date","End Time","All Day Event","Description","Location","Private"};
		writeList.add(header);
		for (gCalImportCsvDto gCalDto : gCalDtoList){
			if (gCalDto.getSubject() == null) {
				// ヘッダー行はサブジェクトがない為スキップ
				continue;
			}
			String[] aryStr = new String[9];
			aryStr[0] = gCalDto.getSubject();
			aryStr[1] = gCalDateDf.format(gCalDto.getStartDate());
			aryStr[2] = gCalTimeDf.format(gCalDto.getStartTime());
			aryStr[3] = gCalDateDf.format(gCalDto.getEndDate());
			aryStr[4] = gCalTimeDf.format(gCalDto.getEndTime());
			aryStr[5] = String.valueOf(gCalDto.isAllDayEventFlag());
			aryStr[6] = gCalDto.getDescription();
			aryStr[7] = gCalDto.getLocation();
			aryStr[8] = String.valueOf(gCalDto.isPrivateFlag());
			writeList.add(aryStr);
		}


		FileOutputStream out = new FileOutputStream("gCalImport.csv");
		Writer writer = new OutputStreamWriter(out);
		CSVWriter csvWriter = new CSVWriter(writer);
		csvWriter.writeAll(writeList);

		csvWriter.close();
		writer.close();
		out.close();
		reader.close();


	}



}
