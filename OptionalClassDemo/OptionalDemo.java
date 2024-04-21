package OptionalClassDemo;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {

        //creating an Optional object
        // 3 ways to create an object

        // 1. create an empty Optional
        Optional<User> emptyUser = Optional.empty();
        // emptyOpt.get() - NoSuchElementException.

        //2. When object is not null
        User user = new User();
        Optional<User> normalUser = Optional.of(user); // optional with non value
        //If this above normalUser is null, NullPointerException would be immediately thrown

        // 3. When object can be either null or not null
        User nullUser = null;
        Optional<User> nullableUser = Optional.ofNullable(nullUser);
        //by using ofNullable, you can create an Optional object that may hold a null value:

        Address address = new Address(160, "Sri Lanka");
        User userWithValues = new User("Pamudu", address);

        // Accessing the actual object
        // Use get() method for that
        Optional<User> normalOptionalUser = Optional.of(userWithValues);
        System.out.println(normalOptionalUser.get()); // this method throws an exception in case the value is null.

        //Do something if a value is present
        Optional<User> optionalUser = Optional.ofNullable(userWithValues);
        checkAvailability(optionalUser);
        checkObjectPresence(optionalUser);

        //Returning default values
        Address address2 = new Address(100, "USA");
        User user2 = new User("Tharindu", address2);
        User defaultUser = Optional.ofNullable(user2).orElse(userWithValues);
        //If user2 is null, userWithValues will be returned as a default value.

        //Default values
        //orElse() method, which provides a default value if Optional is empty
        User userWithDefaultValues = optionalUser.orElse(new User());

        // orElse() vs orElseGet
        elseMethodDifference();

        //Similarly, you can use the orElseThrow() method, which instead of providing a default
        // value if Optional is empty, throws an exception:

        filterOptionalObjects(optionalUser);
    }

    public static void checkAvailability(Optional<User> optionalUser){
        //If the Optional object were empty, nothing would be printed.
        optionalUser.ifPresent(System.out :: println);
    }

    public static void checkObjectPresence(Optional<User> optionalUser){
        //This code can be replaced as the above checkAvailability method implementation
        if(optionalUser.isPresent()){
            System.out.println(optionalUser.get());
        }
        //get() method that returns the value contained in the Optional object, if it is present.
        // Otherwise, it throws a NoSuchElementException.
    }

    public static void filterOptionalObjects(Optional<User> optionalUser){
        optionalUser
                .filter(element -> element.getAddress().getCountry().equals("Sri Lanka"))
                .ifPresent(element -> System.out.println(element.getName()));
    }

    public static void extractValues(Optional<User> optionalUser){
        optionalUser.map(element -> element.getAddress())
                .filter(address -> address.getCountry().equals("Sri Lanka"))
                .ifPresent(Address::getCountry);
    }

    public static void elseMethodDifference(){
        User user = null;
        System.out.println("starting to execute orElse part");
        User optionalUer = Optional.ofNullable(user).orElse(createNewUser());
        System.out.println("starting to execute orElseGet part");
        User OptionalGetUser = Optional.ofNullable(user).orElseGet(() -> createNewUser());

        //output is same for both orElse and orElseGet method
        //when the object is empty and the default object is returned instead, there is no difference in behavior.
        System.out.println("------------------------------");
        Address address2 = new Address(100, "USA");
        User tharindu = new User("Tharindu", address2);
        System.out.println("starting to execute orElse part");
        User tharinduUser = Optional.ofNullable(tharindu).orElse(createNewUser());
        System.out.println("starting to execute orElseGet part");
        User tharinduGetUser = Optional.ofNullable(tharindu).orElseGet(() -> createNewUser());
        //Optional objects contain a non-null value which the methods will return. However, the orElse()
        // method will still create the default User object. By contrast, the orElseGet() method
        // will no longer create a User object.

        //This difference can have a significant effect on performance if the operation executed
        // involves more intensive calls
    }

    private static User createNewUser() {
        System.out.println("Executing create new user method");
        Address address = new Address(120, "Canada");
        return new User("Ruvindu", address);
    }

}
