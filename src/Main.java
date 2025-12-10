import java.util.ArrayList;
import java.util.function.Supplier;

/// Inputs teacher, student, or person and returns the factory method for each.
class PersonTypeTransformer
  implements InputUtils.InputTransformer<Supplier<Person>> {

  @Override
  public Supplier<Person> transform(String userInput)
    throws InputUtils.InvalidInputException {
    if (userInput.matches("\\s*[Tt]eacher\\s*")) {
      return PersonInput::createTeacher;
    } else if (userInput.matches("\\s*[Ss]tudent\\s*")) {
      return PersonInput::createStudent;
    } else if (userInput.matches("\\s*[Pp]erson\\s*")) {
      return PersonInput::createPerson;
    }

    throw new InputUtils.InvalidInputException(
      "You must enter either teacher, student, or person."
    );
  }
}

class Main {

  static InputUtils inputUtils = new InputUtils();

  public static void main(String[] args) {
    PersonInput.registerInputUtils(inputUtils);
    ArrayList<Person> people = new ArrayList<>();

    // Input loop
    while (
      inputUtils.getUserInput(
        "Would you like to add another person? ",
        new InputUtils.BooleanTransformer()
      )
    ) {
      people.add(
        inputUtils
          .getUserInput(
            "What type of person? Options are teacher, student, or person: ",
            new PersonTypeTransformer()
          )
          .get()
      );
    }

    // Output loop
    for (int i = 0; i < people.size(); i++) {
      Person person = people.get(i);
      boolean isTeacher = person instanceof Teacher;
      boolean isStudent = person instanceof Student;

      String suffix = "";
      if (isTeacher) {
        suffix = ", teacher";
      } else if (isStudent) {
        suffix = ", student";
      }

      System.out.format(
        "Person %d: %s, age %d%s\n",
        i + 1,
        person.getName(),
        person.age,
        suffix
      );
    }
  }
}
