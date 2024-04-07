package Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTransformation {

    public static void main(String[] args) {
        final List<String> friends =
                Arrays.asList("pamudu", "prabathiya", "tharindu", "pasan", "shenal");

        final List<String> uppercaseNames = new ArrayList<>();
        //Strings are immutable in java, which means instances can't be changed
        //So, we can create new strings in all caps and replace the element
        //However, the original collection would be lost

        //Creating a new list that has the elements in all caps is a better option.
        for(String name : friends){
            uppercaseNames.add(name.toUpperCase());
        }

        //we can use internal forEach() method for the above code
        //but we still need an empty list and add elements to it.
        friends.forEach(name -> uppercaseNames.add(name.toUpperCase()));

        // Lambda expression
        friends.stream()
                .map(name -> name.toUpperCase())
                .forEach(name -> System.out.println(name + " "));

        /*
        stream is a sequence of elements.
        The Stream’s map() method can map or transform a sequence of input to a sequence of output.
        The map() operation applies the function specified in its parameter to each element in a
            particular stream and returns a new stream.
        */

        /*
        This method will ensure that the same number of elements exists in the input and the output
        sequence. However, element types in the input don’t have to match the element types in the
        output collection.
        Below example takes a string input stream and produce an integer stream.
        For that example, method references are used. The method reference is for an instance method.
        */
        friends.stream()
                .map(String::length)
                .forEach(System.out::println);
    }


}
