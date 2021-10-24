package Baitap24102021;

public class Student extends Person {
	private String batch;

	public Student() {
		super();
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getInfo(Student student) {
		return student.getId() + "|" + student.getName() + "|" + student.getDob() + "|" + student.getAddress() + "|"
				+ student.getBatch() + "\n";
	}

}
