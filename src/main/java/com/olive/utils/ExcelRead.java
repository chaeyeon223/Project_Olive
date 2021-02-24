package com.olive.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelRead {

	public static List<Map<String, String>> read(ExcelReadOption excelReadOption) {

		Workbook workbook = ExcelFileType.getWorkbook(excelReadOption.getFilePath());

		Sheet sheet = workbook.getSheetAt(0);
		System.out.println("Sheet 이름: " + workbook.getSheetName(0));
		System.out.println("데이터가 있는 Sheet의 수 :" + workbook.getNumberOfSheets());

		int numOfRows = sheet.getPhysicalNumberOfRows();
		System.out.println(numOfRows);
		int numOfCells = 0;

		Row row = null;
		Cell cell = null;
		String cellName = null;
		Map<String, String> map = null;
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		for (int rowIndex = excelReadOption.getStartRow()-1; rowIndex < numOfRows; rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (row != null) {
				numOfCells = row.getPhysicalNumberOfCells();
				map = new HashMap<String, String>();	
				for (int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {

					cell = row.getCell(cellIndex);
					System.out.println("[cell]"+cell);

					cellName = sheet.getRow(0).getCell(cellIndex).getStringCellValue()+"";
					System.out.println("[cellName]"+cellName);
					
					if (!excelReadOption.getOutputColumns().contains(cellName)) {
						continue;
					}
					map.put(cellName, ExcelCellRef.getValue(cell));
				}
				result.add(map);
				System.out.println(map);
			}
		}
		return result;
	}
}
