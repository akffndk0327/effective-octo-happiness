package kr.or.ddit.designpattern;

import java.util.Stack;

import kr.or.ddit.designpattern.templatemethodpattern.BadStudent;
import kr.or.ddit.designpattern.templatemethodpattern.GoodStudent;
import kr.or.ddit.designpattern.templatemethodpattern.Student;

public class DDIT {
	public static void main(String[] args) {
		//좋은 학생 5명
		//자는 학생 5명 
		//최소 컬렉선 10명필요해
		Student[] students = new Student[10];
		int i = 0;
		for(;i<5;i++) {
			students[i] = new GoodStudent(); 
		}
		for(; i<students.length; i++) {
			students[i] = new BadStudent();
		}
		for(Student tmp : students) {
			tmp.lifecycle();
		}
		
	}
}
