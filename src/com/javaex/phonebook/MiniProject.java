package com.javaex.phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiniProject {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		String num, list, name, hp, op, str;
		int sel;
		boolean stat = true;
		List<String> dl = new ArrayList<String>();
		InputStream is = new FileInputStream("./PhoneDB.txt");
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		while (true) {
			list = br.readLine();
			if (list == null) {
				break;
			}
			dl.add(list);
		}
		System.out.println(
				"********************************\n*       전화번호 관리 프로그램       *\n********************************");
		while (stat) {
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료\n-----------------------------");
			System.out.print(">메뉴번호: ");
			num = sc.next();
			switch (num) {
			case "1":
				System.out.print("<1.리스트>\n");
				for (int i = 0; i < dl.size(); i++) {
					String[] details;
					details = dl.get(i).split(",");
					System.out.println((i + 1) + ".    " + details[0] + "     " + details[1] + "  " + details[2]);
				}
				System.out.println();
				break;
			case "2":
				OutputStream os = new FileOutputStream("./PhoneDB.txt");
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);
				System.out.print("<2.등록>\n");
				System.out.print(">이름: ");
				name = sc.next();
				System.out.print(">휴대전화: ");
				hp = sc.next();
				System.out.print(">회사전화: ");
				op = sc.next();
				dl.add(name + "," + hp + "," + op);
				for (int i = 0; i < dl.size(); i++) {
					bw.write(dl.get(i));
					bw.newLine();
				}
				bw.close();
				System.out.println("[등록되었습니다.]\n");
				break;
			case "3":
				OutputStream os2 = new FileOutputStream("./PhoneDB.txt");
				OutputStreamWriter osw2 = new OutputStreamWriter(os2, "UTF-8");
				BufferedWriter bw2 = new BufferedWriter(osw2);
				System.out.print("<3.삭제>\n>번호 : ");
				sel = sc.nextInt() - 1;
				if (sel < dl.size()) {
					dl.remove(sel);
					for (int i = 0; i < dl.size(); i++) {
						bw2.write(dl.get(i));
						bw2.newLine();
					}
					bw2.close();
					System.out.println("[삭제되었습니다.]\n");
				} else {
					for (int i = 0; i < dl.size(); i++) {
						bw2.write(dl.get(i));
						bw2.newLine();
					}
					bw2.close();
					System.out.println("리스트 범위를 초과하였습니다\n");
				}
				break;
			case "4":
				int chk = 0;
				System.out.print("<4.검색>\n>이름 : ");
				str = sc.next();
				for (int i = 0; i < dl.size(); i++) {
					if (dl.get(i).contains(str)) {
						String[] details;
						details = dl.get(i).split(",");
						System.out.println((i + 1) + ".    " + details[0] + "     " + details[1] + "  " + details[2]);
					} else {
						chk += 1;
					}
				}
				if (chk == dl.size()) {
					System.out.println("[찾으시는 결과가 없습니다.]");
				}
				System.out.println();
				break;
			case "5":
				System.out.println(
						"\n********************************\n*            감사합니다           *\n********************************");
				stat = false;
				break;
			default:
				System.out.println("[다시 입력해 주세요.]\n");
				break;
			}
		}
		br.close();
		sc.close();

	}

}
