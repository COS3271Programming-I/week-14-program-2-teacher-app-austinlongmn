// File originally appeared in Week 12 Project 1: Bicycle App
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Scanner;

// This is my custom class to facilitate error handling for user input.
public class InputUtils implements Closeable {

  /// Scans STDIN for input.
  private Scanner scanner;

  /// Creates a new InputUtils object
  public InputUtils() {
    scanner = new Scanner(System.in);
  }

  /// Closes the scanner and STDIN
  @Override
  public void close() {
    scanner.close();
  }

  /// Gets user input with a prompt and transformer
  public <T> T getUserInput(String message, InputTransformer<T> transformer) {
    while (true) {
      System.out.print(message);
      try {
        return transformer.transform(scanner.nextLine());
      } catch (InvalidInputException e) {
        System.err.format("Error: %s\n", e.getUserFeedback());
      }
    }
  }

  /// Handles the transforming of the users' string into an actual value.
  public static interface InputTransformer<T> {
    /// Takes userInput and turns it into a value of type T.
    public T transform(String userInput) throws InvalidInputException;
  }

  /// Thrown when a user inputted invalid input.
  public static class InvalidInputException extends Exception {

    /// What to say to the user to get them to enter a valid value.
    private String feedback;

    /// Returns the feedback to be displayed to the user
    public String getUserFeedback() {
      return feedback;
    }

    public InvalidInputException(String feedback) {
      super();
      this.feedback = feedback;
    }
  }

  /// A transformer that allows any user input and just returns it.
  public static class StringTransformer
    implements InputUtils.InputTransformer<String> {

    @Override
    public String transform(String userInput) {
      return userInput;
    }
  }

  /// An input transformer that interprets the user's input as an float.
  /// You may optionally specify a minimum and maximum value.
  public static class FloatTransformer
    implements InputUtils.InputTransformer<Float> {

    private float minValue = Float.MIN_VALUE;
    private float maxValue = Float.MAX_VALUE;

    public FloatTransformer() {}

    public FloatTransformer(float minValue) {
      this.minValue = minValue;
    }

    public FloatTransformer(float minValue, float maxValue) {
      this.minValue = minValue;
      this.maxValue = maxValue;
    }

    @Override
    public Float transform(String userInput)
      throws InputUtils.InvalidInputException {
      try {
        Float result = Float.parseFloat(userInput);

        if (result >= minValue && result <= maxValue) {
          return result;
        } else {
          throw new InvalidInputException(
            String.format(
              "You must enter a value from %d to %d.",
              minValue,
              maxValue
            )
          );
        }
      } catch (NumberFormatException e) {
        throw new InputUtils.InvalidInputException("You must enter a number.");
      }
    }
  }

  /// An input transformer that interprets the user's input as an integer.
  /// You may optionally specify a minimum and maximum value.
  public static class IntegerTransformer
    implements InputUtils.InputTransformer<Integer> {

    private int minValue = Integer.MIN_VALUE;
    private int maxValue = Integer.MAX_VALUE;

    public IntegerTransformer() {}

    public IntegerTransformer(int minValue) {
      this.minValue = minValue;
    }

    public IntegerTransformer(int minValue, int maxValue) {
      this.minValue = minValue;
      this.maxValue = maxValue;
    }

    @Override
    public Integer transform(String userInput)
      throws InputUtils.InvalidInputException {
      try {
        Integer result = Integer.parseInt(userInput);

        if (result >= minValue && result <= maxValue) {
          return result;
        } else {
          throw new InvalidInputException(
            String.format(
              "You must enter a value from %d to %d.",
              minValue,
              maxValue
            )
          );
        }
      } catch (NumberFormatException e) {
        throw new InputUtils.InvalidInputException(
          "You must enter an integer."
        );
      }
    }
  }

  /// Transforms a yes or no question into a boolean.
  public static class BooleanTransformer
    implements InputUtils.InputTransformer<Boolean> {

    @Override
    public Boolean transform(String userInput)
      throws InputUtils.InvalidInputException {
      if (userInput.matches("[Yy](es)?")) {
        return true;
      } else if (userInput.matches("[Nn](o)?")) {
        return false;
      } else {
        throw new InputUtils.InvalidInputException(
          "You must answer yes or no."
        );
      }
    }
  }

  public static class CommaSeparatedListTransformer<
    UnitType,
    TransformerType extends InputTransformer<UnitType>
  >
    implements InputTransformer<ArrayList<UnitType>> {

    private TransformerType unitTransformer;

    public CommaSeparatedListTransformer(TransformerType unitTransformer) {
      this.unitTransformer = unitTransformer;
    }

    @Override
    public ArrayList<UnitType> transform(String userInput)
      throws InputUtils.InvalidInputException {
      ArrayList<UnitType> result = new ArrayList<>();

      for (String unit : userInput.split(",")) {
        result.add(unitTransformer.transform(unit));
      }

      return result;
    }
  }
}
