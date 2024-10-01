package parser;

import java.util.Arrays;
import java.util.List;

public class TEST {
    public static void main(String[] args) {
        var stream = List.of(1, 2, 3).stream();

        var list1 = stream.toArray();
        var list2 = stream.toArray();

        System.out.println(Arrays.toString(list1));
        System.out.println(Arrays.toString(list2));


    }
}
