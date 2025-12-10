import java.util.ArrayList;
import java.util.Random;

/// A class representing an academic teacher
class Teacher extends Person {

  final ArrayList<String> classes = new ArrayList<>();
  private String department;

  public void teachClass() {
    System.out.println(
      "Welcome to " +
        classes.get((new Random()).nextInt(0, classes.size())) +
        "."
    );
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
