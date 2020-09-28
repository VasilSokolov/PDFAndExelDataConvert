package ParameterizedData;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
public class Excel {

	public static void main(String[] args) {
		Workbook workbook = new HSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("Data");
		Cell cell1 = sheet.createRow(0).createCell(0);
		Cell cell2 = sheet.createRow(0).createCell(1);
		Cell cell3 = sheet.createRow(0).createCell(2);
		Cell cell4 = sheet.createRow(1).createCell(0);
		Cell cell5 = sheet.createRow(1).createCell(1);
		Cell cell6 = sheet.createRow(1).createCell(2);
		cell1.setCellValue(1);
		cell2.setCellValue("name");
		cell3.setCellValue("Aria");
		cell4.setCellValue(2);
		cell5.setCellValue("interest");
		cell6.setCellValue(0.11);
		try {
			FileOutputStream output = new FileOutputStream("src/main/resources/Names.xls");
			workbook.write(output);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error message: " + e.getMessage());
		}
	}
}
