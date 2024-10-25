package NhanVien;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import javax.print.DocFlavor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
//import java.io.IOException;

public class QL_KHACHHANG {
    public KHACHHANG[] danhsachkhachhang = new KHACHHANG[0];

    public QL_KHACHHANG(){}

    public void themDanhSach( KHACHHANG x){
        danhsachkhachhang = Arrays.copyOf(danhsachkhachhang,danhsachkhachhang.length+1);
        danhsachkhachhang[danhsachkhachhang.length-1] = x;
    }
    public void nhapDanhSach(){
        Scanner scanner = new Scanner(System.in);
//        System.out.println("nhap so dien thoai");
//        int sdt = scanner.nextInt();
//        System.out.println("nhap ho ten khach hang");
//        String ten = scanner.nextLine();
//        scanner.nextLine();
//        System.out.println("nhap dia chi");
//        String diachi = scanner.nextLine();
//        KHACHHANG khachhang = new KHACHHANG(ten,sdt,diachi);
//        themDanhSach(khachhang);
        System.out.println("ban muon nhap bao nhieu khach hang ?");
        int n = scanner.nextInt();
        for ( int i = 0 ; i < n ; i++){
            KHACHHANG khachhang = new KHACHHANG();
            khachhang.nhap();
            if ( traVeTonTai(khachhang.getSdt()) == null)
                themDanhSach(khachhang);
        }
    }
    public void duyetFileExcel( String filename , String sheetname ){
        FileInputStream fileinput = null;
        XSSFWorkbook workbook = null;
        try {
            fileinput = new FileInputStream(filename);
            workbook = new XSSFWorkbook(fileinput);

            XSSFSheet sheet = workbook.getSheet(sheetname);
            if ( sheet == null) {
                System.out.println("Không tìm thấy sheet");
                return;
            }
           // sheet.getPhysicalNumberOfRows() trả về số hàng có nội dung
            // sheet.getLastRowNum() trả về chỉ số của hang cuối cùng , vd có 9 hàng , bắt đầu
            // từ 0 , trả về 8

            for ( int i_row = 0 ; i_row <= sheet.getLastRowNum() ; i_row++){
                XSSFRow row = sheet.getRow(i_row);
                KHACHHANG khachhang = new KHACHHANG();
            //    row.getLastCellNum() trả về tổng số ô có trong hàng
            //    VD có 9 ô , trả về 9   , khác với getLastRowNum
                if ( row == null) continue;
                for ( int i_cell = 0 ; i_cell < row.getLastCellNum() ; i_cell++){
                    XSSFCell cell = row.getCell(i_cell);
                    if ( cell == null) continue;
                    switch ( cell.getCellType()){
                        case STRING :
                            if ( i_cell == 0) {
                                khachhang.setHoten(cell.getStringCellValue());
                                break;
                            }
                            else {
                                khachhang.setDiachi(cell.getStringCellValue());
                                break;
                            }
                        case NUMERIC:
                            khachhang.setSdt((int)cell.getNumericCellValue());
                            break;
                    }

                }
                themDanhSach(khachhang);
            }
        }
        catch (Exception ex){
            System.out.println(" lỗi ");
            ex.printStackTrace();
        }

    }
    public void xuatDanhSach(){
        for ( KHACHHANG khachhang : danhsachkhachhang){
            khachhang.xuat();
        }
    }
//    public KHACHHANG kiemTraTonTai ( int SDT){
//        for ( KHACHHANG khachhang : danhsachkhachhang){
//            if ( khachhang.getSdt() == SDT) {
//                System.out.println(" SDT : "+SDT+" đã tồn tại");
//                return khachhang;
//            }
//        }
//        return null;
//    }
    public KHACHHANG traVeTonTai(int SDT){
        int tmp = timViTriKhachHang(SDT);
        if ( tmp != -1) return danhsachkhachhang[tmp];
        return null;
    }

    public int timViTriKhachHang( int SDT){
        for ( int i = 0 ; i < danhsachkhachhang.length ; i++){
            if (danhsachkhachhang[i].getSdt() == SDT) return i;
        }
        return -1;
    }
    public void xoaKhachHang ( int SDT){
        int vitri = timViTriKhachHang(SDT);
        for ( int i = vitri ; i < danhsachkhachhang.length-1 ; i++)
                danhsachkhachhang[i] =danhsachkhachhang[i+1];
        danhsachkhachhang = Arrays.copyOf(danhsachkhachhang,danhsachkhachhang.length-1);
    }
    public String timDiaChiKhachHang ( int SDT){
        if(traVeTonTai(SDT) != null){
            for ( KHACHHANG khachhang : danhsachkhachhang){
                if ( khachhang.getSdt() == SDT) return khachhang.getDiachi();
            }
        }
        return null;
    }
    public void suaDiaChiKhachHang ( int SDT){
        int vitri = timViTriKhachHang(SDT);
        System.out.println("nhập địa chỉ mới ");
        String diachi = new Scanner(System.in).nextLine();
        if ( vitri != -1){
            danhsachkhachhang[vitri].setDiachi(diachi);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        QL_KHACHHANG kh1 = new QL_KHACHHANG();
        //kh1.nhapDanhSach();
        //kh1.xuatDanhSach();
        //System.out.println("nhap sdt khach hang can xoa");
        //int sdt = new Scanner(System.in).nextInt();
        //kh1.xoaKhachHang(sdt);
//        System.out.println("******************************");
//        kh1.xuatDanhSach();
//        FileInputStream file = new FileInputStream("test.xlsx");
        kh1.duyetFileExcel("E:\\NĂM 2\\JAVA_HUONG_DOITUONG_NĂM 2\\java\\untitled\\src\\test.xlsx","Sheet1");
        kh1.xuatDanhSach();

    }




}
