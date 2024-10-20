package NhanVien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileExcel {
	public NhanVien reading(String sheetName, int rNum, String fileName) {
    	NhanVien nhanVien=new NhanVien();// Thay bang class muon doc
    	FileInputStream fis=null;
		XSSFWorkbook wb=null;
    	try {
			Field[] fields = nhanVien.getClass().getDeclaredFields();
			fis=new FileInputStream(fileName);
			wb=new XSSFWorkbook(fis);
			XSSFSheet sheet=wb.getSheet(sheetName);
			XSSFRow row=sheet.getRow(rNum);
			int cNum=0;
			for (Field field : fields) {
				field.setAccessible(true); // Cho phép truy cập vào thuộc tính private
				try {
					if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
						double temp=row.getCell(cNum).getNumericCellValue();
						field.set(nhanVien, (int)temp);
		            } else if ((field.getType().equals(Double.class) || field.getType().equals(double.class) )) {
		            	field.set(nhanVien, Double.parseDouble(row.getCell(cNum).toString()));
		            } else if (field.getType() == Boolean.class) {
		            	field.set(nhanVien, Boolean.parseBoolean(row.getCell(cNum).toString()));
		            } else if (field.getType() == String.class) {
		            	field.set(nhanVien, row.getCell(cNum).toString());
		            } else if (field.getType() == Date.class) {
		            	field.set(nhanVien, row.getCell(cNum).getDateCellValue());
		            }
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.out.println("loi khong the gan du lieu cho cell "+ cNum);
					e.printStackTrace();
				}
				cNum++;
				if(cNum==row.getLastCellNum()) break;
			}
		} catch (FileNotFoundException e) {
			 System.out.println("Tệp không tồn tại: " + e.getMessage());
		} catch (IOException e) {
			 System.out.println("Lỗi khi đọc tệp: " + e.getMessage());
		} catch (NullPointerException e) {
            System.out.println("Lỗi: Hàng hoặc ô không tồn tại: " + e.getMessage());
        }
    	return nhanVien;
	}
}
