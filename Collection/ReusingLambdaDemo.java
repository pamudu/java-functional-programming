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


        //however, duplication will sneak in quickly when we bring in another letter to match.
        final Predicate<String> startsWithN = name -> name.startsWith("N");
        final Predicate<String> startsWithB = name -> name.startsWith("B");

        final long countFriendsStartWithN =
                friends.stream()
                        .filter(startsWithN).count();
        final long countFriendsStartWithB =
                friends.stream()
                        .filter(startsWithB).count();

        // but the two predicates are mere duplicates,
        //with only the letter they use being different. Let’s figure out a way to eliminate
        //this duplication. --> check the checkIfStartsWith() method

        final long countFriendNamesStartN =
                friends.stream()
                        .filter(checkIfStartsWith("N")).count();
        final long countFriendNamesStartB =
                friends.stream()
                        .filter(checkIfStartsWith("B")).count();
    }

    public static Predicate<String> checkIfStartsWith(final String letter){
        // the checkIfStartsWith() returns a
        //function as a result. This is a higher-order function
        return name -> name.startsWith(letter);
    }

    /*
    * From within a lambda expression we can only access local variables that are final or
    effectively final in the enclosing scope.
    A lambda expression may be invoked right away, or it may be invoked lazily or from
    multiple threads. To avoid race conditions, the local variables we access in the
    enclosing scope are not allowed to change once initialized. Any attempt to change
    them will result in a compilation error.
    Variables marked final directly fit this bill, but Java does not insist that we mark them
    as such. Instead, Java looks for two things. First, the accessed variables have to be
    initialized within the enclosing methods before the lambda expression is defined.
    Second, the values of these variables don’t change anywhere else—that is, they’re
    effectively final although they are not marked as such.
    When using lambda expressions that capture local state, we should be aware that
    stateless lambda expressions are runtime constants, but those that capture local
    state have an additional evaluation cost.
    * */
}
