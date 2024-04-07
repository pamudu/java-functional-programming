package Collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ElementFinding {
    public static void main(String[] args) {
        final List<String> friends =
                Arrays.asList("Pamudu", "Prabathiya", "Tharindu", "Pasan", "Shenal");
        findNamesStartsWithP(friends);
    }

    public static void findNamesStartsWithP_oldWay(List<String> friends){
        final List<String> startsWithP = new ArrayList<>();
        for(String name : friends){
            if(name.startsWith("P")){
                startsWithP.add(name);
            }
        }
    }

    public static void findNamesStartsWithP(List<String> friends){
        final List<String> startsWithP =
                friends.stream()
                .filter(name -> name.startsWith("P"))
                .toList();

        /*
        The filter operation returns a new stream that contains elements that match its predicate
        (this operation's parameter).
        The filter() method expects a lambda expression that returns a boolean result.
        If the lambda expression returns a true, the element in context while executing
        that lambda expression is added to a result collection; it’s skipped otherwise.
        */
        System.out.printf("Found %d names%n", startsWithP.size());
    }
}
