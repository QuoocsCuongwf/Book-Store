package NhanVien;

//D:\code\documents\NhanVien.xlsx

public class Main {
	public static void main(String[] args) {
		NhanVien nhanVien=new NhanVien();
		String fileName="D:\\code\\documents\\NhanVien.xlsx";
		String sheetName="Sheet1";
		for(int i=1;i<=10;i++) {
			nhanVien=nhanVien.reading(sheetName, i, fileName);
			nhanVien.output();
		}
	}
}
