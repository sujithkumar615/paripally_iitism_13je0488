import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		
		return students;
	}

	@Override
	public void setStudents(Student[] students) {
		for(int i=0;i<students.length;i++){
			if(students[i]==null) throw new IllegalArgumentException();
			this.students[i]=students[i];
		}
	}

	@Override
	public Student getStudent(int index) {
		if(index<0||index>students.length) throw new IllegalArgumentException();
		return students[index];
	}

	@Override
	public void setStudent(Student student, int index) {
		if(student==null||index<0||index>students.length)throw new IllegalArgumentException();
		students[index]=student;
	}

	@Override
	public void addFirst(Student student) {
		if(student==null) throw new IllegalArgumentException();
		for(int i=students.length-1;i>0;i--){
			students[i]=students[i-1];
		}
		students[0]=student;
	}

	@Override
	public void addLast(Student student) {
		if(student==null) throw new IllegalArgumentException();
		students[students.length-1]=student;
	}

	@Override
	public void add(Student student, int index) {
		if(student==null||index<0||index>students.length)throw new IllegalArgumentException();
		students[index]=student;
	}

	@Override
	public void remove(int index) {
		if(index<0||index>students.length)throw new IllegalArgumentException();
		students[index]=null;
	}

	@Override
	public void remove(Student student) {
		if(students==null) throw new IllegalArgumentException();
		boolean flag = true;
		for(int i=0;i<students.length;i++){
			if(students[i]==student){
				students[i]=null;
				flag=false;
				break;
			}
		}
		if(flag) throw new IllegalArgumentException("Student not exist");
	}

	@Override
	public void removeFromIndex(int index) {
		if(index<0||index>students.length)throw new IllegalArgumentException();
		for(int i=index+1;i<students.length;i++) students[i]=null;
	}

	@Override
	public void removeFromElement(Student student) {
		if(student==null)throw new IllegalArgumentException();
		int temp=students.length;
		for(int i=0;i<students.length;i++){
			if(students[i]==student){
				temp=i;
				break;
			}
		}
		for(int i=temp;i<students.length;i++) students[i]=null;
	}

	@Override
	public void removeToIndex(int index) {
		if(index<0||index>students.length) throw new IllegalArgumentException();
		for(int i=0;i<index;i++) students[i]=null;
	}

	@Override
	public void removeToElement(Student student) {
		if(student==null)throw new IllegalArgumentException();
		for(int i=0;i<students.length;i++){
			if(students[i]==student) break;
			students[i]=null;
		}
	}

	@Override
	public void bubbleSort() {
		for(int i=0;i<students.length-1;i++){
			for(int j=0;j<students.length-1-i;j++){
				int temp= students[j].compareTo(students[j+1]);
				if(temp>0){
					Student t = students[j+1];
					students[j+1]=students[j];
					students[j]=t;
				}
			}
		}
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		if(date==null) throw new IllegalArgumentException();
		Student [] s = new Student [students.length];
		int j=0;
		for(int i=0;i<students.length;i++){
			if(students[i].getBirthDate().compareTo(date)<1){
				s[j]=students[i];
				j++;
			}
		}
		return s;
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		if(firstDate==null||lastDate==null) throw new IllegalArgumentException();
		Student[] s =new Student[students.length];
		int j=0;
		for(int i=0;i<students.length;i++){
			if(students[i].getBirthDate().compareTo(lastDate)<0&&students[i].getBirthDate().compareTo(firstDate)>0){
				s[j]=students[i];
				j++;
			}
		}
		return s;
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		if(date==null)throw new IllegalArgumentException();
		Student s [] = new Student[students.length];
		int j=0;
		String st = date.toString();
		LocalDate N = LocalDate.parse(st).plusDays(days);
		java.util.Date ndate = java.sql.Date.valueOf(N);
		for(int i=0;i<students.length;i++){
			if(students[i].getBirthDate().compareTo(ndate)<0&&students[i].getBirthDate().compareTo(date)>0){
				s[j]=students[i];
				j++;
			}
		}
		
		return s;
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) {
		if(indexOfStudent<0||indexOfStudent>students.length) throw new IllegalArgumentException();
		String st = students[indexOfStudent].getBirthDate().toString();
		LocalDate dob = LocalDate.parse(st);
		LocalDate today=LocalDate.now();
		return Period.between(dob, today).getYears();
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		Student [] s = new Student[students.length];
		int j=0;
		for(Student t : students){
			String st = t.getBirthDate().toString();
			LocalDate dob = LocalDate.parse(st);
			LocalDate today=LocalDate.now();
			if(Period.between(dob, today).getYears()== age){
				s[j]=t;
				j++;
			}

		}
		return s;
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		double m = 0;
		for(int i=0;i<students.length;i++){
			if(m<students[i].getAvgMark()) m=students[i].getAvgMark();
		}
		Student [] s =new Student [students.length];
		int j=0;
		for(Student t : students){
			if(t.getAvgMark()==m){
				s[j]=t;
				j++;
			}
		}
		return s;
	}

	@Override
	public Student getNextStudent(Student student) {
		if(student==null)throw new IllegalArgumentException();
		for(int i=0;i<students.length;i++){
			if(students[i]==student) return students[i+1];
		}
		return null;
	}
	
}
