package Baitap24102021;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApplicationManage {
	static List<Student> studentList;
	static List<Lecturers> lecturersList;
	static Scanner scan;
	static int state = 0;
	final static String patternIdGtStudent = "[GT]+[0-9]{6}";
	final static String patternIdGcStudent = "[GC]+[0-9]{6}";
	final static String patternDob = "^(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}$";

	public static void main(String[] args) throws Exception {
		studentList = new ArrayList<>();
		studentList = loadInfoStudent();
		lecturersList = loadInfoLecturers();
		int c = 0;
		scan = new Scanner(System.in);
		showMainMenu(c);

	}

	static void showMainMenu(int c) throws Exception {
		System.out.println("1.	Manage Students");
		System.out.println("2.	Manage Lecturers");
		System.out.println("3.	Exit");
		do {
			c = scan.nextInt();
			switch (c) {
			case 1:
				showStudentMenu(c);
				break;
			case 2:
				showLecturersMenu(c);
				break;
			case 3:
				System.out.println("Exit done!");
				System.exit(0);
				break;
			default:
				System.out.println("Wrong choise!!! Please enter again.");
				break;
			}
		} while (c != 0);
	}

	static void showStudentMenu(int c) throws Exception {
		System.out.println("1.	Add new student");
		System.out.println("2.	View all students");
		System.out.println("3.	Search students");
		System.out.println("4.	Delete students");
		System.out.println("5.	Update student");
		System.out.println("6.	Back to main menu");
		do {
			c = scan.nextInt();
			switch (c) {
			case 1:
				inputStudent();
				break;
			case 2:
				viewInfoStudent(studentList);
				break;
			case 3:
				findStudentByName();
				break;
			case 4:
				deleteStudentById();
				break;
			case 5:
				editStudentById();
				break;
			case 6:
				showMainMenu(c);
				break;
			default:
				System.out.println("Wrong choise!!! Please enter again.");
				break;
			}
		} while (c != 0);

	}

	static void showLecturersMenu(int c) throws Exception {
		System.out.println("1.	Add new lecturer");
		System.out.println("2.	View all lecturers");
		System.out.println("3.	Search lecturers");
		System.out.println("4.	Delete lecturers");
		System.out.println("5.	Update lecturer");
		System.out.println("6.	Back to main menu");
		do {
			c = scan.nextInt();
			switch (c) {
			case 1:
				inputLecturers();
				break;
			case 2:
				viewInfoLecturers(lecturersList);
				break;
			case 3:
				findLecturersByName();
				break;
			case 4:
				deleteLecturersById();
				break;
			case 5:
				editLecturersById();
				break;
			case 6:
				showMainMenu(c);
				break;
			default:
				System.out.println("Wrong choise!!! Please enter again.");
				break;
			}
		} while (c != 0);

	}

	static void inputStudent() throws Exception {
		Student std = new Student();
		scan = new Scanner(System.in);
		System.out.println("Enter id student fomat GTxxxxxx or GCxxxxxx : ");
		String id = scan.nextLine();
		id = id.toUpperCase();

		boolean checkExist = false;
		while (checkExist) {
			while (!id.matches(patternIdGtStudent) && !id.matches(patternIdGcStudent)) {
				System.out.println("Enter id student fomat GTxxxxxx or GCxxxxxx : ");
				id = scan.nextLine();
			}
			for (Student student : studentList) {
				if (student.getId().equals(id)) {
					checkExist = true;
					break;
				} else {
					checkExist = false;
				}
			}
			if (checkExist) {
				System.out.println("Id: " + id + " existed,Try enter: ");
				id = scan.nextLine();
			}
		}
		std.setId(id);
		System.out.println("Enter name : ");
		std.setName(scan.nextLine());
		System.out.println("Enter date of birth fomat dd/MM/yyyy : ");
		String dob = scan.nextLine();
		while (!dob.matches(patternDob)) {
			System.out.println("Enter date of birth fomat dd/MM/yyyy : ");
			dob = scan.nextLine();
		}
		std.setDob(dob);
		System.out.println("Enter address : ");
		std.setAddress(scan.nextLine());
		System.out.println("Enter batch : ");
		std.setBatch(scan.nextLine());
		studentList.add(std);
		saveFileStudent();
		System.out.println("Add student success!");
		int c = 0;
		showStudentMenu(c);
	}

	static void findStudentByName() throws Exception {
		boolean isExisted = false;
		System.out.println("Enter name Student: ");
		scan = new Scanner(System.in);
		String findName = scan.nextLine();
		for (Student student : studentList) {
			if (student.getName().trim().toUpperCase().contains(findName.trim().toUpperCase())) {
				isExisted = true;
				System.out.println(student.getInfo(student));
			}
		}
		if (!isExisted) {
			System.out.println("Name = " + findName + " does not exist");
		}
		int c = 0;
		showStudentMenu(c);
	}

	static void editStudentById() throws Exception {
		boolean isExisted = false;
		scan = new Scanner(System.in);
		System.out.println("Enter Id update: ");
		String findId = scan.nextLine();
		for (Student student : studentList) {
			if (student.getId().trim().equals(findId.trim())) {
				isExisted = true;
				System.out.println("Enter name : ");
				String name = scan.nextLine();
				student.setName("".equals(name.trim()) ? student.getName() : name);
				System.out.println("Enter date of birth fomat dd/MM/yyyy : ");
				String dob = scan.nextLine();

				while (!"".equals(dob.trim()) && !dob.matches(patternDob)) {
					System.out.println("Enter date of birth fomat dd/MM/yyyy : ");
					dob = scan.nextLine();
				}
				student.setDob("".equals(dob.trim()) ? student.getDob() : dob);
				System.out.println("Enter address : ");
				String address = scan.nextLine();
				student.setAddress("".equals(address.trim()) ? student.getAddress() : address);
				System.out.println("Enter batch : ");
				String batch = scan.nextLine();
				student.setBatch("".equals(batch.trim()) ? student.getBatch() : batch);
				saveFileStudent();
				System.out.println("Update id " + findId + " success!");
			}
		}
		if (!isExisted) {
			System.out.println("Id: " + findId + " does not exist");
		}
		int c = 0;
		showStudentMenu(c);
	}

	static void deleteStudentById() throws Exception {
		boolean isExisted = false;
		scan = new Scanner(System.in);
		System.out.println("Enter Id Student delete: ");
		String deleteId = scan.nextLine();
		for (Student student : studentList) {
			if (student.getId().trim().equals(deleteId.trim())) {
				isExisted = true;
				studentList.remove(student);
				System.out.println("Deleted id : " + deleteId);
			}
		}
		if (!isExisted) {
			System.out.println("Id: " + deleteId + " does not exist");
		}
		int c = 0;
		showStudentMenu(c);
	}

	static void inputLecturers() throws Exception {
		Lecturers lecturers = new Lecturers();
		scan = new Scanner(System.in);
		System.out.println("Enter id Lecturers : ");
		String id = scan.nextLine();
		id = id.toUpperCase();
		boolean checkExist = false;
		while (checkExist) {
			for (Lecturers lecturer : lecturersList) {
				if (lecturer.getId().equals(id)) {
					checkExist = true;
					break;
				} else {
					checkExist = false;
				}
			}
			if (checkExist) {
				System.out.println("Id: " + id + " existed,Try enter: ");
				id = scan.nextLine();
			}
		}
		lecturers.setId(id);
		System.out.println("Enter name : ");
		lecturers.setName(scan.nextLine());
		System.out.println("Enter date of birth fomat dd/MM/yyyy : ");
		String dob = scan.nextLine();
		while (!dob.matches(patternDob)) {
			System.out.println("Enter date of birth fomat dd/MM/yyyy : ");
			dob = scan.nextLine();
		}
		lecturers.setDob(dob);
		System.out.println("Enter address : ");
		lecturers.setAddress(scan.nextLine());
		System.out.println("Enter Dept : ");
		lecturers.setDept(scan.nextLine());
		lecturersList.add(lecturers);
		saveFileLecturers();
		System.out.println("Add Lecturers success!");
		int c = 0;
		showLecturersMenu(c);
	}

	static void findLecturersByName() throws Exception {
		boolean isExisted = false;
		System.out.println("Enter name Lecturers: ");
		scan = new Scanner(System.in);
		String findName = scan.nextLine();
		for (Lecturers lecturers : lecturersList) {
			if (lecturers.getName().trim().toUpperCase().contains(findName.trim().toUpperCase())) {
				isExisted = true;
				System.out.println(lecturers.getInfo(lecturers));
			}
		}
		if (!isExisted) {
			System.out.println("Name = " + findName + " does not exist");
		}
		int c = 0;
		showLecturersMenu(c);
	}

	static void editLecturersById() throws Exception {
		boolean isExisted = false;
		scan = new Scanner(System.in);
		System.out.println("Enter Id update: ");
		String findId = scan.nextLine();
		for (Lecturers lecturers : lecturersList) {
			if (lecturers.getId().trim().equals(findId.trim())) {
				isExisted = true;
				System.out.println("Enter name : ");
				String name = scan.nextLine();
				lecturers.setName("".equals(name.trim()) ? lecturers.getName() : name);
				System.out.println("Enter date of birth fomat dd/MM/yyyy : ");
				String dob = scan.nextLine();

				while (!"".equals(dob.trim()) && !dob.matches(patternDob)) {
					System.out.println("Enter date of birth fomat dd/MM/yyyy : ");
					dob = scan.nextLine();
				}
				lecturers.setDob("".equals(dob.trim()) ? lecturers.getDob() : dob);
				System.out.println("Enter address : ");
				String address = scan.nextLine();
				lecturers.setAddress("".equals(address.trim()) ? lecturers.getAddress() : address);
				System.out.println("Enter dept : ");
				String batch = scan.nextLine();
				lecturers.setDept("".equals(batch.trim()) ? lecturers.getDept() : batch);
				saveFileLecturers();
				System.out.println("Update id " + findId + " success!");
			}
		}
		if (!isExisted) {
			System.out.println("Id: " + findId + " does not exist");
		}
		int c = 0;
		showStudentMenu(c);
	}

	static void deleteLecturersById() throws Exception {
		boolean isExisted = false;
		scan = new Scanner(System.in);
		System.out.println("Enter Id Lecturers delete: ");
		String deleteId = scan.nextLine();
		for (Lecturers lecturers : lecturersList) {
			if (lecturers.getId().trim().equals(deleteId.trim())) {
				isExisted = true;
				studentList.remove(lecturers);
				System.out.println("Deleted id : " + deleteId);
			}
		}
		if (!isExisted) {
			System.out.println("Id: " + deleteId + " does not exist");
		}
		int c = 0;
		showStudentMenu(c);
	}

	static void saveFileStudent() throws Exception {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("student.txt");

			for (Student student : studentList) {
				String line = student.getInfo(student);
				byte[] data = line.getBytes("utf8");

				fos.write(data);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException ex) {

				}
			}
		}
	}

	static void saveFileLecturers() throws Exception {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("lecturers.txt");

			for (Lecturers lecturers : lecturersList) {
				String line = lecturers.getInfo(lecturers);
				byte[] data = line.getBytes("utf8");

				fos.write(data);
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException ex) {

				}
			}
		}
	}

	static List<Student> loadInfoStudent() throws Exception {
		List<Student> studentList = new ArrayList<>();
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream("student.txt");
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String line = bufferedReader.readLine();
			while (line != null) {
				String[] infoSplit = line.split("\\|");
				if (infoSplit.length > 0) {
					Student student = new Student();
					for (int i = 0; i < infoSplit.length; i++) {
						if (i == 0) {
							student.setId(infoSplit[i]);
						} else if (i == 1) {
							student.setName(infoSplit[i]);
						} else if (i == 2) {
							student.setDob(infoSplit[i]);
						} else if (i == 3) {
							student.setAddress(infoSplit[i]);
						} else if (i == 4) {
							student.setBatch(infoSplit[i]);
						}
					}
					studentList.add(student);
				}
				line = bufferedReader.readLine();
			}

		} catch (FileNotFoundException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {

				}
			}
		}
		return studentList;

	}

	static List<Lecturers> loadInfoLecturers() throws Exception {
		List<Lecturers> lecturersList = new ArrayList<>();
		FileInputStream fileInputStream = null;
		BufferedReader bufferedReader = null;
		try {
			fileInputStream = new FileInputStream("lecturers.txt");
			bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
			String line = bufferedReader.readLine();
			while (line != null) {
				String[] infoSplit = line.split("\\|");
				if (infoSplit.length > 0) {
					Lecturers lecturers = new Lecturers();
					for (int i = 0; i < infoSplit.length; i++) {
						if (i == 0) {
							lecturers.setId(infoSplit[i]);
						} else if (i == 1) {
							lecturers.setName(infoSplit[i]);
						} else if (i == 2) {
							lecturers.setDob(infoSplit[i]);
						} else if (i == 3) {
							lecturers.setAddress(infoSplit[i]);
						} else if (i == 4) {
							lecturers.setDept(infoSplit[i]);
						}
					}
					lecturersList.add(lecturers);
				}
				line = bufferedReader.readLine();
			}

		} catch (FileNotFoundException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {

				}
			}
		}
		return lecturersList;

	}

	static void viewInfoStudent(List<Student> studentList) throws Exception {

		for (Student student : studentList) {
			System.out.println(student.getInfo(student));
		}
		int c = 0;
		showStudentMenu(c);
	}

	static void viewInfoLecturers(List<Lecturers> lecturersList) throws Exception {

		for (Lecturers lecturers : lecturersList) {
			System.out.println(lecturers.getInfo(lecturers));
		}
		int c = 0;
		showLecturersMenu(c);

	}

}
