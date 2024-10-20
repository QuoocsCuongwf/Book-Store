package NhanVien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





public class NhanVien {
	private String maNhanVien;
	private String ho;
	private String ten;
	private String diaChi;
	private String sdt;
	private Date ngayVaoLam;
	private String chucVu;
	private double luong;
	static String msnv=null;
	
	// Constructor
	
	public NhanVien() {}
	
    public NhanVien(String maNhanVien, String ho, String ten, String diaChi, String sdt, Date ngayVaoLam, String chucVu, double luong) {
        this.maNhanVien = maNhanVien;
        this.ho = ho;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.ngayVaoLam = ngayVaoLam;
        this.chucVu = chucVu;
        this.luong = luong;
    }

    // Constructor sao chép
    public NhanVien(NhanVien nv) {
        this.maNhanVien = nv.maNhanVien;
        this.ho = nv.ho;
        this.ten = nv.ten;
        this.diaChi = nv.diaChi;
        this.sdt = nv.sdt;
        this.ngayVaoLam = nv.ngayVaoLam;
        this.chucVu = nv.chucVu;
        this.luong = nv.luong;
    }
    
    
    
    public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Date getNgayVaoLam() {
		return ngayVaoLam;
	}

	public void setNgayVaoLam(Date ngayVaoLam) {
		this.ngayVaoLam = ngayVaoLam;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public static String getMsnv() {
		return msnv;
	}

	public static void setMsnv(String msnv) {
		NhanVien.msnv = msnv;
	}

	public void input() {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Nhập mã nhân viên: ");
        maNhanVien = scanner.nextLine();

        System.out.print("Nhập họ: ");
        ho = scanner.nextLine();

        System.out.print("Nhập tên: ");
        ten = scanner.nextLine();

        System.out.print("Nhập địa chỉ: ");
        diaChi = scanner.nextLine();

        System.out.print("Nhập số điện thoại: ");
        sdt = scanner.nextLine();

        System.out.print("Nhập ngày vào làm (dd/MM/yyyy): ");
        ngayVaoLam = null;
        try {
            ngayVaoLam = sdf.parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        System.out.print("Nhập chức vụ: ");
        chucVu = scanner.nextLine();

        System.out.print("Nhập lương: ");
        luong = scanner.nextInt();
    }   
    
    public void output() {
        System.out.println("Mã NV: " + maNhanVien);
        System.out.println("Họ: " + ho);
        System.out.println("Tên: " + ten);
        System.out.println("Địa chỉ: " + diaChi);
        System.out.println("SĐT: " + sdt);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ngày vào làm: " +sdf.format(ngayVaoLam));
        System.out.println("Chức vụ: " + chucVu);
        System.out.printf("Lương: %.2f \n" , luong);
        msnv=maNhanVien;
        
    }
    public NhanVien reading(String sheetName, int rNum, String fileName) {
    	NhanVien nhanVien=new NhanVien();
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
