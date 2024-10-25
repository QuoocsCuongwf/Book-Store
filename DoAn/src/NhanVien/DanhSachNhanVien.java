package NhanVien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class DanhSachNhanVien {
	NhanVien[] dsNhanVien = new NhanVien[0];
	static NhanVien nhanVienStatic=null;
	public void input(int n) {
		for (int i = 0; i < n; i++) {
			dsNhanVien = Arrays.copyOf(dsNhanVien, dsNhanVien.length+1);
			dsNhanVien[i]=new NhanVien();
			dsNhanVien[i].input();
		}
	}
	public void readFromExcel(String file, String sheetName){
		FileInputStream fis=null;
		XSSFWorkbook wb=null;
		try {
			fis=new FileInputStream(file);
			wb=new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			System.out.println("file error!");
		} catch (IOException e) {
			System.out.println("excel can not read");
		}
		XSSFSheet sheet=wb.getSheet(sheetName);
		int lastRowNum=sheet.getLastRowNum();
		System.out.println(lastRowNum);
		System.out.println("Last Row Number: "+lastRowNum);
		for (int i = 0; i < lastRowNum; i++) {
			dsNhanVien = Arrays.copyOf(dsNhanVien, dsNhanVien.length+1);
			dsNhanVien[i]=new NhanVien();
			dsNhanVien[i]=dsNhanVien[i].reading(sheetName, i+1, file);
			dsNhanVien[i].Output();
		}
		
	}
	public void output() {
		for (int i = 0; i < dsNhanVien.length; i++) {
			dsNhanVien[i].Output();
		}
	}
	
	public void xemThongTin(String mnv) {
		findMNV(mnv).Output();
	}
	
	public NhanVien findMNV(String mnv) {
		for(NhanVien nhanVien :dsNhanVien) {
			if(nhanVien.getMaNhanVien().contains(mnv)) {
				nhanVienStatic=nhanVien;
				return nhanVien;
			}
		}
		return null;
	}
	public NhanVien findMNV(String mnv, String sheetName, String file) {
		DanhSachNhanVien dsNhanVien=new DanhSachNhanVien();
		dsNhanVien.readFromExcel(file, sheetName);
		return dsNhanVien.findMNV(mnv);
	}
	
	
	public void them() {
		NhanVien nhanVien=new NhanVien();
		nhanVien.input();
		while (findMNV(nhanVien.getMaNhanVien())!=null) {
			nhanVien.input();			
		}
		dsNhanVien=Arrays.copyOf(dsNhanVien, dsNhanVien.length+1);
		dsNhanVien[dsNhanVien.length-1]=nhanVien;
	}
	public void write(String sheetName, String file,NhanVien nhanVien) {
		nhanVien.write(sheetName, file, nhanVien);
	}
	public void write(String sheetName, String file,NhanVien[] dsnv) {
		for(NhanVien nhanVien:dsnv) {
			nhanVien.write(sheetName, file, nhanVien);
		}
	}
	
	
	
}
