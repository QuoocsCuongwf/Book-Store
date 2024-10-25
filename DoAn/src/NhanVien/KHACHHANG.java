package NhanVien;

import java.util.Scanner;

public class KHACHHANG {
    public String hoten;
    //public int maKH;
    public int sdt;
    public String diachi;
    public KHACHHANG(){}

    public KHACHHANG(String hoten, int sdt, String diachi) {
        this.hoten = hoten;
        //this.maKH = maKH;
        this.sdt = sdt;
        this.diachi = diachi;
    }
    public KHACHHANG ( KHACHHANG x) {
        this.hoten = x.hoten;
        //this.maKH = x.maKH;
        this.sdt = x.sdt;
        this.diachi = x.diachi;
    }

    public String getHoten() {
        return hoten;
    }

//    public int getMaKH() {
//        return maKH;
//    }

    public int getSdt() {
        return sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

//    public void setMaKH(int maKH) {
//        this.maKH = maKH;
//    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public void nhap(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("nhap ten ho ten  : ");
        this.hoten = scanner.nextLine();
        this.hoten = this.hoten.toUpperCase();
        System.out.print("nhap SDT  : ");
        this.sdt = scanner.nextInt();
        scanner.nextLine();
        System.out.println("nhap dia chi : ");
        this.diachi = scanner.nextLine();
    }
    public void xuat(){
        System.out.println("ho ten: "+this.getHoten());
        System.out.println("SDT: "+this.getSdt());
        System.out.println("Dia chi: "+this.getDiachi());
        System.out.println("------------------------------");
    }


}

