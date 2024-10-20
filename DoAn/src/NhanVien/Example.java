package NhanVien;

import java.lang.reflect.Field;

public class Example {
    public static void main(String[] args) {
        Field[] fields = NhanVien.class.getDeclaredFields();

        for (Field field : fields) {
            System.out.println("Tên thuộc tính: " + field.getName());
            System.out.println("Kiểu dữ liệu: " + field.getType().getSimpleName());
            System.out.println("--------");
            
            // Kiểm tra kiểu dữ liệu là Integer hoặc int
            if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                System.out.println(field.getName() + " là kiểu Integer hoặc int.");
            }
        }
        System.out.println(Double.class);
    }
}

