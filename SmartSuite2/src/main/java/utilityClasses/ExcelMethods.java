package utilityClasses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelMethods {
	
	//Initialize Variable(s)
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	
	//Location of the Excel File
	private static String path;
	public static String sheetName = "";
	
	//Set the current/active Excel File
	public void setDataTablePath(String dataTablePath) {
//		path = System.getProperty("user.dir") + dataTablePath;
		path = dataTablePath;
	}
	
	//Set the current/active Excel Sheet
	public void setSheetName(String desiredSheetName) {
		sheetName = desiredSheetName;
	}
	
	//Method returns the current time when called
	public String getCurrentDateTime() {
		//Get the current time - will be used to indicate when the test finished for the Excel File output
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyy/MM/dd HH:mm:ss");
		LocalDateTime currentDateTime = LocalDateTime.now();
		String currentDateTimeStr = dateTimeFormat.format(currentDateTime);
		
		//Return the value of the current date & time
		return currentDateTimeStr;
	}
	
	//Retrieve a single row of a DataSheet - each row of a DataSheet is iterated through by the method, 'getDataTable()' in this java class to be used as the input/parameters for the active script
	public static String[] retrieveRow(XSSFRow row, int numCols) {
		//Initialize Variable(s)
		String[] importRow = new String[numCols];
		XSSFCell cell;
		
		//Iterate through each cell in the active row
		for (int i = 0; i < row.getLastCellNum(); i++) {
			//Set the values for Variable(s)
			cell = row.getCell(i);
			
			//If a cell has no value, create a cell & specify it as blank, then add that cell
			if(cell != null) {
				importRow[i] = cell.toString();
			} else {
				row.createCell(i);
				importRow[i] = row.getCell(i).toString();
			}
		}
		
		//Return an array with the active row's data (one array entry for each cell)
		return importRow;
	}
	
	//Retrieve an Excel DataSheet for the active script's input/parameters
	@DataProvider(name="inputs")
	public String[][] getDataTable() {
		//Initialise Variable(s)
		String[][] importDataTable = null;
		
		try {
			//Open the Excel File
			FileInputStream ExcelFile = new FileInputStream(path);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			//Retrieve the number of rows & columns of the DataTable
			int numRows = ExcelWSheet.getPhysicalNumberOfRows() - 1;
			int numCols = ExcelWSheet.getRow(0).getPhysicalNumberOfCells() + 1;
			
			//Initialize the 2D array to store the DataTable's data
			importDataTable = new String[numRows][numCols];
			
			//Retrieve the DataTable values (one row at a time) and place them into the array
			for (int i = 1; i < ExcelWSheet.getPhysicalNumberOfRows(); i++) {
				//Focus on a specific row
				XSSFRow row = ExcelWSheet.getRow(i);
				
				//Use another method to retrieve the values of each cell in the specified row
				importDataTable[i-1] = retrieveRow(row, numCols);
			}
			
			//Enter a number into the last column of each row to indicate which row it is 
			for (int i = 0; i < importDataTable.length; i++) {
				importDataTable[i][numCols-1] = String.valueOf(i+1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Return the 2D array to be used as the active script's parameters
		return importDataTable;
	}

	public String getDataTableCell(int rowNum, int colNum) {
		//Initialize Variable(s)
		String cellValue = "";
		
		try {
			//Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);
			
			//Access the Excel DataTable
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			//Focus on the specific cell of the DataTable
			Row = ExcelWSheet.getRow(rowNum);
			Cell = Row.getCell(colNum);
			
			//If the cell is empty, retrieve an empty value. Otherwise, retrieve the cell's value
			if (Cell == null) {
				cellValue = "";
			} else {
				cellValue = Cell.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Return the desired cell's value
		return cellValue;
	}

	//Send output to a specific DataSheet cell
	public void setDataTableCell(String result, int rowNum, int colNum) {
		//Initialize Variable(s)
		colNum = colNum - 1; //Reduce the colNum by 1, since the ExcelSheet starts with 0. (no need to do this with rowNum, since the rows have row #0 dedicated to column titles)
		
		try {
			//Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);
			
			//Access the Excel DataSheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			//Focus on the specific cell of the DataTable
			Row = ExcelWSheet.getRow(rowNum);
			Cell = Row.getCell(colNum);
			
			//Enter the desired value into the DataTable cell (if the DataTable cell is empty, prepare it to be able to receive the value first)
			if (Cell == null) {
				Cell = Row.createCell(colNum);
				Cell.setCellValue(result);
			} else {
				Cell.setCellValue(result);
			}
			
			//Open the file to write the results
			FileOutputStream outputFile = new FileOutputStream(path);
			
			//Output the changes to the Excel File
			ExcelWBook.write(outputFile);
			
			//Close the Excel File
			outputFile.flush();
			outputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Send output to a specific DataSheet cell
	public void setDataTableCell(int result, int rowNum, int colNum) {
		//Initialize Variable(s)
		colNum = colNum - 1; //Reduce the colNum by 1, since the ExcelSheet starts with 0. (no need to do this with rowNum, since the rows have row #0 dedicated to column titles)
		
		try {
			//Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(path);
			
			//Access the Excel DataSheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
			
			//Focus on the specific cell of the DataTable
			Row = ExcelWSheet.getRow(rowNum);
			Cell = Row.getCell(colNum);
			
			//Enter the desired value into the DataTable cell (if the DataTable cell is empty, prepare it to be able to receive the value first)
			if (Cell == null) {
				Cell = Row.createCell(colNum);
				Cell.setCellValue(result);
			} else {
				Cell.setCellValue(result);
			}
			
			//Open the file to write the results
			FileOutputStream outputFile = new FileOutputStream(path);
			
			//Output the changes to the Excel File
			ExcelWBook.write(outputFile);
			
			//Close the Excel File
			outputFile.flush();
			outputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Send output to a specific DataSheet cell
	public void setDataTableCell(Double result, int rowNum, int colNum) {
		try {
			//Focus on the specific cell of the DataTable
			Row = ExcelWSheet.getRow(rowNum);
			Cell = Row.getCell(colNum);
			
			//Enter the desired value into the DataTable cell (if the DataTable cell is empty, prepare it to be able to receive the value first)
			if (Cell == null) {
				Cell = Row.createCell(colNum);
				Cell.setCellValue(result);
			} else {
				Cell.setCellValue(result);
			}
			
			//Open the file to write the results
			FileOutputStream outputFile = new FileOutputStream(path);
			
			//Output the changes to the Excel File
			ExcelWBook.write(outputFile);
			
			//Close the Excel File
			outputFile.flush();
			outputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}