/// A class representing a college student
class Student extends Person {

  private String major;

  public void setMajor(String major) {
    this.major = major;
  }

  public String getMajor() {
    return major;
  }

  private String classRank;

  public void setClassRank(String classRank) {
    this.classRank = classRank;
  }

  public String getClassRank() {
    return classRank;
  }

  private String studentEmail;

  public void setStudentEmail(String studentEmail) {
    this.studentEmail = studentEmail;
  }

  public String getStudentEmail() {
    return studentEmail;
  }

  public void goToClass() {
    System.out.println("It's time to go to class. Here I go!");
  }

  @Override
  public void eat() {
    System.out.println("Yum! This is some delicious pizza!");
  }
}
