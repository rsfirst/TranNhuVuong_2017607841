package Baitap24102021;

public class Lecturers extends Person {
	private String dept;

	public Lecturers() {
		super();
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getInfo(Lecturers lecturers) {
		return lecturers.getId() + "|" + lecturers.getName() + "|" + lecturers.getDob() + "|" + lecturers.getAddress()
				+ "|" + lecturers.getDept() + "\n";
	}
}
