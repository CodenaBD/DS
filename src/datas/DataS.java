/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author MASTER
 */
public class DataS {
    static boolean lastsharp = true;
    static List<HashMap<String, List<Student>>> list = new ArrayList<>();
    static List<Student> st;
    static HashMap<String,List<Student>> hashMap = new HashMap<>();;


    public static void main(String[] args) {

        try {
            File excel = new File("data.xlsx");
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheetAt(0);

            Iterator<Row> itr = sheet.iterator();
            String title = "";
            while (itr.hasNext()) {
                Row row = itr.next();
                if (lastsharp) {
                    title = row.cellIterator().next().getStringCellValue();
                    st = new ArrayList<>();
                    lastsharp = false;
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();


                int c = 0;
                Student student = new Student();

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    try {
                        if (cell.getStringCellValue().equals("#")) {
                            hashMap.put(title,st );
                            lastsharp = true;
                            break;
                        }
                    }catch (Exception e){}

                    switch (c) {
                        case 0:
                            student.name=cell.getStringCellValue();
                            break;
                        case 1:
                            student.lastName=cell.getStringCellValue();
                            break;
                        case 2:
                            student.rank=(int) cell.getNumericCellValue();
                            break;
                        default:
                    }
                    c++;
                }
                if (!lastsharp) {
                    st.add(student);
                }
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        Uni uni = new Uni(hashMap);
        new MainForm(uni).setVisible(true);
    }
}
