// Gender enumeration
enum Gender {
  MALE,
  FEMALE
}

// Person class
class Person {

  String firstName;
  String lastName;
  Gender gender;
  int age;
  float weight;
  float height;
  String ethnicGroup;
  String religion;

  // Displays person in a nice format
  @Override
  public String toString() {
    return String.format(
      "%s %s\nGender: %s\nAge: %d years\nWeight: %.2f lbs\nHeight: %.2f in\nEthnic Group: %s\nReligion: %s",
      firstName,
      lastName,
      gender,
      age,
      weight,
      height,
      ethnicGroup,
      religion
    );
  }

  public void greeting() {
    if (age < 10) {
      System.out.format(
        "Hello! I'm %s, and I'm %d years old!\n",
        firstName,
        age
      );
    } else {
      System.out.format("Hello! I'm %s.\n", firstName);
    }
  }

  public void sayPrayer() {
    if (religion.toUpperCase().equals("CHRISTIAN")) {
      System.out.println(
        "Dear God, thank you for dying on the cross to save me."
      );
    } else {
      System.out.println("I don't pray to your god.");
    }
  }

  public void takeNap() {
    System.out.println("ZZZ...");
  }

  public void eat() {
    if (age < 10) {
      System.out.println("Yum! I love marshmallows!");
    } else if (age < 20) {
      System.out.println("<Crunch, crunch> I love this cereal!");
    } else {
      System.out.println("This is delicious!");
    }
  }

  public String getName() {
    return firstName + " " + lastName;
  }

  public void setReligion(String newReligion) {
    religion = newReligion;
  }

  public String getPossessivePronoun() {
    if (gender == Gender.MALE) {
      return "his";
    } else {
      return "her";
    }
  }
}
