package Collection;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaDemo {
    public static void main(String[] args) {
        final List<String> friends =
                Arrays.asList("Pamudu", "Prabathiya", "Tharindu", "Pasan", "Shenal");
        iterateOldWay(friends); //imperative style iteration
        enhancedIteration(friends);//this style is better than the first if we don’t intend to modify the collection at a particular index
        //both of the above implementations we control the iteration

        /*
         There are quite a few reasons to favor the change to the functional style:
        • The for loops are inherently sequential and are quite difficult to parallelize.
        • Such loops are non-polymorphic; we get exactly what we ask for. We passed the collection
            to for instead of invoking a method (a polymorphic operation) on the collection to perform the task.
        • At the design level, the code fails the "Tell, don’t ask” principle. We ask for a specific
            iteration to be performed instead of leaving the details of the iteration to underlying libraries.
        */

        iterateWithLambda(friends);
    }

    public static void iterateOldWay(List<String> names){
        // verbose and error-prone way of iterating
        for(int i=0; i<names.size(); i++){
            System.out.println(names.get(i));
        }
    }

    public static void enhancedIteration(List<String> names){
        //This form of iteration uses the Iterator interface under the hood.
        //and calls hasNext() and next() methods
        for(String name: names){
            System.out.println(name);
        }
    }

    public  static void iterateWithForeach(List<String> names){
        //Here we have used internal iterator
        //but code is more verbose
        names.forEach(new Consumer<String>() {
            @Override
            public void accept(String name) {
                System.out.println(name);
            }
        });
    }

    public static void iterateWithLambda(List<String> names){
        //names.forEach(name -> System.out.println(name));
        names.forEach(System.out::println);
    }
}
