package StreamsDemo;

import java.util.*;
import java.util.stream.Collectors;

public class FlatMapDemo {

    public static void main(String[] args) {
        List<List<String>> boxes = new ArrayList<>();
        boxes.add(Arrays.asList("apple","banana", "orange"));
        boxes.add(Arrays.asList("carrot", "lettuce"));
        boxes.add(Arrays.asList("pencil", "book", "eraser"));
        //flattenList(boxes);
        //findDistinctWords();
//        handlingOptionalValues();
        flattenMap();
    }

    public static void flattenList(List<List<String>> stringList){
        List<String> list = stringList.stream()
                .flatMap(Collection::stream) //item -> item.stream()
                .toList();

        list.forEach(System.out :: println);
    }

    public static void findDistinctWords(){
        List<String> sentences = Arrays.asList(
                "Java is a programming language",
                "It is widely used for developing applications",
                "Streams provide a powerful way to process data in Java"
        );

        Set<String> collect = sentences.stream()
                .map(sentence -> sentence.split(" "))
                .flatMap(Arrays::stream)//word -> Arrays.stream(word)
                .distinct()
                .collect(Collectors.toSet());

        collect.forEach(System.out :: println);
    }

    static class Order{
        private List<String> items;

        public Order(List<String> items) {
            this.items = items;
        }

        public List<String> getItems() {
            return items;
        }
    }

    public static void findDistinctItems(){
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Arrays.asList("apple", "banana", "orange")));
        orders.add(new Order(Arrays.asList("banana", "grapes")));
        orders.add(new Order(Arrays.asList("orange", "apple", "mango")));

        Set<String> orderItems = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .distinct()
                .collect(Collectors.toSet());
    }

    public static void handlingOptionalValues(){
        List<Optional<String>> optionalString = Arrays.asList(
                Optional.of("apple"),
                Optional.empty(),
                Optional.of("banana"),
                Optional.of(""),
                Optional.of("orange")
        );

        Set<String> elements = optionalString.stream()
                .flatMap(item -> item.stream())
                .filter(item -> !item.isEmpty())
                .collect(Collectors.toSet());

        elements.forEach(System.out::println);
    }

    public static void flattenMap(){
        Map<String, List<Integer>> data = new HashMap<>();
        data.put("group1", Arrays.asList(1, 2, 3));
        data.put("group2", Arrays.asList(4, 5, 6, 7));
        data.put("group3", Arrays.asList(8, 9));

        long total = data.values()
                .stream()
                .flatMapToInt(list -> list.stream().mapToInt(number -> number.intValue()))
                .count();

        System.out.println(total);
    }


}
