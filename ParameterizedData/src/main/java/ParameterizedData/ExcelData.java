package ParameterizedData;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
public class ExcelData {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Workbook workbook = new HSSFWorkbook(new FileInputStream("src/main/resources/Names.xls"));
			Sheet sheet = workbook.getSheetAt(0);
			for(Row row : sheet) {
				for(Cell cell : row) {
					if(cell.getColumnIndex() != 0) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						System.out.print(cell.getStringCellValue() + "\t");
					}
				}
				if(row.getCell(2).getStringCellValue() != null && NumberUtils.isParsable(row.getCell(2).getStringCellValue())) {
					try {
						map.putIfAbsent(row.getCell(1).getStringCellValue(), Integer.parseInt(row.getCell(2).getStringCellValue()));
					} catch (NumberFormatException e) { 
						map.putIfAbsent(row.getCell(1).getStringCellValue(), Double.parseDouble(row.getCell(2).getStringCellValue()));
						System.out.println();
						continue;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
				map.putIfAbsent(row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue());
				System.out.println();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println(map.toString());
	}
}
