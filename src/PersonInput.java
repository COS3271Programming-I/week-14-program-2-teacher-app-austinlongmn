/// Parses a male or female gender from the user
class GenderInputTransformer implements InputUtils.InputTransformer<Gender> {

  @Override
  public Gender transform(String userInput)
    throws InputUtils.InvalidInputException {
    if (userInput.matches("[Mm](ale)?")) {
      return Gender.MALE;
    } else if (userInput.matches("[Ff](emale)?")) {
      return Gender.FEMALE;
    } else {
      throw new InputUtils.InvalidInputException(
        "Gender must be either male or female (Genesis 1:27)"
      );
    }
  }
}

/// Parses an email from the user
class EmailTransformer implements InputUtils.InputTransformer<String> {

  @Override
  public String transform(String userInput)
    throws InputUtils.InvalidInputException {
    // Very simple regex; not fit for production
    if (userInput.matches(".+@.+")) {
      return userInput;
    } else {
      throw new InputUtils.InvalidInputException("Invalid email format.");
    }
  }
}

/// Contains helper functions to allow the user to modify a person
class PersonInput {

  private static InputUtils inputUtils;

  public static void registerInputUtils(InputUtils inputUtils) {
    PersonInput.inputUtils = inputUtils;
  }

  public static <T> T getUserInput(
    String attribute,
    InputUtils.InputTransformer<T> transformer,
    Person person
  ) {
    return inputUtils.getUserInput(
      String.format(
        "Enter %s's %s: ",
        person.firstName == null ? "the person" : person.firstName,
        attribute
      ),
      transformer
    );
  }

  public static Person createPerson() {
    Person person = new Person();

    updateFirstName(person);
    updateLastName(person);
    updateGender(person);
    updateAge(person);
    updateWeight(person);
    updateHeight(person);
    updateEthnicGroup(person);
    updateReligion(person);

    return person;
  }

  public static Person createTeacher() {
    Teacher teacher = new Teacher();

    updateFirstName(teacher);
    updateLastName(teacher);
    updateGender(teacher);
    updateAge(teacher);
    updateWeight(teacher);
    updateHeight(teacher);
    updateEthnicGroup(teacher);
    updateReligion(teacher);
    updateTeachingClasses(teacher);
    updateDepartment(teacher);

    return teacher;
  }

  public static Student createStudent() {
    Student student = new Student();

    updateFirstName(student);
    updateLastName(student);
    updateGender(student);
    updateAge(student);
    updateWeight(student);
    updateHeight(student);
    updateEthnicGroup(student);
    updateReligion(student);
    updateClassRank(student);
    updateMajor(student);
    updateStudentEmail(student);

    return student;
  }

  public static void updateFirstName(Person person) {
    person.firstName = getUserInput(
      "first name",
      new InputUtils.StringTransformer(),
      person
    );
  }

  public static void updateLastName(Person person) {
    person.lastName = getUserInput(
      "last name",
      new InputUtils.StringTransformer(),
      person
    );
  }

  public static void updateGender(Person person) {
    person.gender = getUserInput(
      "gender",
      new GenderInputTransformer(),
      person
    );
  }

  public static void updateAge(Person person) {
    person.age = getUserInput(
      "age",
      new InputUtils.IntegerTransformer(0, 130),
      person
    );
  }

  public static void updateWeight(Person person) {
    person.weight = getUserInput(
      "weight",
      new InputUtils.FloatTransformer(0, 2000),
      person
    );
  }

  public static void updateHeight(Person person) {
    person.height = getUserInput(
      "height in inches",
      new InputUtils.FloatTransformer(0, 150),
      person
    );
  }

  public static void updateEthnicGroup(Person person) {
    person.ethnicGroup = getUserInput(
      "ethnic group",
      new InputUtils.StringTransformer(),
      person
    );
  }

  public static void updateReligion(Person person) {
    person.religion = getUserInput(
      "religion",
      new InputUtils.StringTransformer(),
      person
    );
  }

  public static void updateStudentEmail(Student student) {
    student.setStudentEmail(
      getUserInput("student email", new EmailTransformer(), student)
    );
  }

  public static void updateMajor(Student student) {
    student.setMajor(
      getUserInput("major", new InputUtils.StringTransformer(), student)
    );
  }

  public static void updateClassRank(Student student) {
    student.setClassRank(
      getUserInput("class rank", new InputUtils.StringTransformer(), student)
    );
  }

  public static void updateDepartment(Teacher teacher) {
    teacher.setDepartment(
      getUserInput("department", new InputUtils.StringTransformer(), teacher)
    );
  }

  public static void updateTeachingClasses(Teacher teacher) {
    teacher.classes.clear();
    teacher.classes.addAll(
      getUserInput(
        "classes",
        new InputUtils.CommaSeparatedListTransformer<
          String,
          InputUtils.StringTransformer
        >(new InputUtils.StringTransformer()),
        teacher
      )
    );
  }
}
