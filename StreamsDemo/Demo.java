package StreamsDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Demo {
    public static void main(String[] args) {
        List<Transaction> data = loadData();

        /*
        stream: sequence of elements, streams don’t actually store elements; they are computed on demand.
        Source: Streams consume from a data-providing source such as collections, arrays, or I/O resources.
        Aggregate operations: Streams support SQL-like operations and common operations from functional
                    programing languages, such as filter, map, reduce, find, match, sorted, and so on.
        */

        /*
        Furthermore, stream operations have two fundamental characteristics that make them very different
        from collection operations:

        Pipelining: Many stream operations return a stream themselves. This allows operations to be
                    chained to form a larger pipeline. This enables certain optimizations, such as laziness
                    and short-circuiting, which we explore later.

        Internal iteration: In contrast to collections, which are iterated explicitly (external iteration),
                    stream operations do the iteration behind the scenes for you.
        */

        /*
        *           Collection             |               Stream
        * --------------------------------------------------------------------------------------
        *     in-memory data structure,    |  stream is a conceptually fixed data structure in which
        *                                  |  elements are computed on demand.
        * */

        /*
        *  intermediate operation - They can be connected together because their return type is a Stream.
        *
        *  terminate operations - Operations that close a stream pipeline are called terminal operations.
         * */

        /*You might be wondering why the distinction is important. Well, intermediate operations do not
        perform any processing until a terminal operation is invoked on the stream pipeline;
        they are “lazy.” This is because intermediate operations can usually be “merged” and
        processed into a single pass by the terminal operation.*/

        getEvenSquares();
        isTransactionsExpensive(data);
    }

    private static List<Transaction> loadData() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(Transaction.Type.GROCERY, 1, 1200));
        transactions.add(new Transaction(Transaction.Type.CONSUMER_ELECTRONICS, 2, 2500));
        transactions.add(new Transaction(Transaction.Type.GROCERY, 3, 800));
        transactions.add(new Transaction(Transaction.Type.GROCERY, 4, 50));
        transactions.add(new Transaction(Transaction.Type.GROCERY, 5, 3450));
        transactions.add(new Transaction(Transaction.Type.CONSUMER_ELECTRONICS, 6, 6000));
        transactions.add(new Transaction(Transaction.Type.CONSUMER_ELECTRONICS, 7, 4999));
        transactions.add(new Transaction(Transaction.Type.GROCERY, 8, 150));
        return transactions;
    }

    public static void listAllIds(List<Transaction> transactions){
        List<Integer> transactionIds =
                transactions.stream()
                        .map(Transaction::getId)
                        .toList();
    }

    public static void getEvenSquares(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> list =
                numbers.stream()
                        .filter(e -> e % 2 == 0)
                        .map(n -> n * n)
                        .limit(2)
                        .toList();

        list.forEach(System.out::println);

        //limit(2) this is a short circuiting
    }

    public static void isTransactionsExpensive(List<Transaction> transactions){
        boolean expensive = transactions.stream().allMatch(t -> t.getValue() > 500);
        System.out.println("expensive:" + expensive);
    }
}
