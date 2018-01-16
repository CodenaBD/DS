/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Field {
    public int size;
    public Student[] Heap;
    public int position;

    String name;
    int authorizedRank;
    private int maxsize;
    private int capacity;

    private static final int FRONT = 1;

    public Field(String name, Student[] students, int authorizedRank, int capacity) {
        this.name = name;
        this.maxsize = students.length;
        this.authorizedRank =authorizedRank;
        this.capacity = capacity;
        Heap = new Student[this.maxsize + 1];
        for (int i = 0; i < Heap.length; i++) {
            Heap[i] = new Student();
        }
        position = 0;
        createHeap(students);
    }

    public void createHeap(Student[] arrA) {
        if (arrA.length > 0) {
            for (int i = 0; i < arrA.length; i++) {
                insert(arrA[i]);
            }
        }
    }

    public List<Student> getStudents() {
        List<Student> authorizedStudent = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            Student student = extractMin();
            if (student.rank < authorizedRank) {
                student.accepted="مجاز";
                authorizedStudent.add(student);
            }else {
                student.accepted="غیر مجاز";
                authorizedStudent.add(student);
            }
        }
        return authorizedStudent;
    }

    public void display() {
        for (int i = 1; i < Heap.length; i++) {
            System.out.print(" " + Heap[i]);
        }
        System.out.println("");
    }

    public void insert(Student x) {
        if (position == 0) {
            Heap[position + 1].rank = x.rank;
            Heap[position + 1].name = x.name;
            Heap[position + 1].lastName = x.lastName;
            position = 2;
        } else {
            int c = position++;
            Heap[c].rank = x.rank;
            Heap[c].name = x.name;
            Heap[c].lastName = x.lastName;
            bubbleUp();
        }
    }

    public void bubbleUp() {
        int pos = position - 1;
        while (pos > 0 && Heap[pos / 2].rank > Heap[pos].rank) {
            swap(pos, pos / 2);
            pos = pos / 2;
        }
    }

    public Student extractMin() {
        Student min = Heap[FRONT];
        Heap[FRONT] = Heap[position - 1];
        Heap[position - 1] = new Student();
        position--;
        sinkDown(FRONT);
        return min;
    }

    public void sinkDown(int k) {
        int a = Heap[k].rank;
        int smallest = k;
        if (2 * k < position && Heap[smallest].rank > Heap[2 * k].rank) {
            smallest = 2 * k;
        }
        if (2 * k + 1 < position && Heap[smallest].rank > Heap[2 * k + 1].rank) {
            smallest = 2 * k + 1;
        }
        if (smallest != k) {
            swap(k, smallest);
            sinkDown(smallest);
        }

    }

    public void swap(int fpos, int spos) {
        //System.out.println("swappinh" + Heap[a] + " and " + Heap[b]);
        int tmp = Heap[fpos].rank;
        Heap[fpos].rank = Heap[spos].rank;
        Heap[spos].rank = tmp;
        String name = Heap[fpos].name;
        Heap[fpos].name = Heap[spos].name;
        Heap[spos].name = name;
        String Lname = Heap[fpos].lastName;
        Heap[fpos].lastName = Heap[spos].lastName;
        Heap[spos].lastName = Lname;


    }
}
