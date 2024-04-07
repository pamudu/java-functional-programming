package Collection;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceDemo {
    public static void main(String[] args) {
        //JDK contains many terminal operations that returns one value by combining the contents of a stream
        //Reduction operations ->  average, sum, min, max, count
        final List<String> friends =
                Arrays.asList("Pamudu", "Prabathiya", "Tharindu", "Pasan", "Shenal");

        System.out.println("Total number of characters in all names: " +
                friends.stream()
                        .mapToInt(String::length)
                        .sum());

       /*
        We leveraged the mapToInt() method, a variation of the map operation (variations
        like mapToInt(), mapToDouble(), and so on create type-specialized streams such
        as IntStream and DoubleStream) and then reduced the resulting length to the sum value.
        */

        //the implementation of the sum() method in the JDK uses a reduce()
        //method. JDK also provides you with the general-purpose reduction operations such as
        // reduce and collect

        //let’s read over the given collection of names and display the
        //longest one.
        final Optional<String> aLongName =
                friends.stream()
                        .reduce((name1, name2) ->
                                        name1.length() >= name2.length() ? name1 : name2);
        aLongName.ifPresent(name ->
                                    System.out.printf("A longest name: %s%n", name));

//        The lambda expression we’re passing to the reduce() method takes two
//        parameters, name1 and name2, and returns one of them based on the length.
//        The reduce() method has no clue about our specific intent. That concern is
//        separated from this method into the lambda expression that we pass to it—
//        this is a lightweight application of the strategy pattern.

        final String steveOrLonger =
                friends.stream()
                        .reduce("Steve", (name1, name2) ->
                                name1.length() >= name2.length() ? name1 : name2);
    }
}
