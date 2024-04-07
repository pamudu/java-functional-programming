package Collection;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ReusingLambdaDemo {
    public static void main(String[] args) {
        /*let’s see how easy it is to fall into the duplication trap when using
        lambda expressions, and consider ways to avoid it.*/

        final List<String> friends =
                Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        final List<String> editors =
                Arrays.asList("Brian", "Jackie", "John", "Mike");
        final List<String> comrades =
                Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");

        //filter out names start with 'N'
        final long countFriendsStartN =
                friends.stream()
                        .filter(name -> name.startsWith("N"))
                        .count();

        final long countEditorsStartN =
                editors.stream()
                        .filter(name -> name.startsWith("N"))
                        .count();

        final long countComradesStartN =
                comrades.stream()
                        .filter(name -> name.startsWith("N"))
                        .count();

        //If we want to do one change to the lambda expression needs
        //to change in more than one place

        //Let’s refactor the previous code to make it DRY.(See the Don’t Repeat Yourself —DRY)
        final Predicate<String> startWithN = name -> name.startsWith("N");
        final long countFriendsStartNWithPredicate =
                friends.stream()
                        .filter(startWithN)
                        .count();

        final long countEditorsStartNWithPredicate =
                editors.stream()
                        .filter(startWithN)
                        .count();

        final long countComradesStartNWithPredicate =
                comrades.stream()
                        .filter(startWithN)
                        .count();

        //Rather than duplicate the lambda expression several times, we created it once
        //and stored it in a reference named startsWithN of type Predicate.
    }
}
