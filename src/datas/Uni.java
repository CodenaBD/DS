/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;


import java.util.*;

public class Uni {
    public List<Field> fs;

    public Uni(HashMap<String,List<Student>> hashMap) {
        fs = new ArrayList<>();
        for(Map.Entry<String,List<Student>> entry : hashMap.entrySet()) {
            String name = entry.getKey();
             Object[] objects =entry.getValue().toArray();
            Student[] students=new Student[objects.length];
            for (int i = 0; i < objects.length; i++) {
                students[i] =(Student)objects[i];
            }
            fs.add(new Field(name,students,15,2));
        }
    }
}
