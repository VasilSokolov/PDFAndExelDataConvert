package ParameterizedData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

import javax.swing.JFileChooser;

public class Excel2 {
	
	public static void main(String[] args) {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				Workbook workbook = new HSSFWorkbook(new FileInputStream(fileChooser.getSelectedFile()));
				Sheet sheet = workbook.getSheetAt(0);
				
				for(Row row : sheet) {
					for(Cell cell : row) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
						System.out.print(cell.getStringCellValue() + "\t");
					}
					System.out.println();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
