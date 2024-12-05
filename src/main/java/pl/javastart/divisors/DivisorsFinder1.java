package pl.javastart.divisors;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Divisors {
    private final Set<Integer> positiveDivisors = new HashSet<>();

    public Stream<Integer> findAllDivisors (int n) {
        int number = n;
        if (n != 0) {
            n = Math.abs(n);
            positiveDivisors.add(1);
            positiveDivisors.add(n);
        }
        for (int i = 2; i <= n/2; i++) {
            if (n%i == 0) {
                positiveDivisors.add(i);
            }
        }
        Set<Integer> allDivisors;
        if (number < 0) {
            allDivisors = new HashSet<>();
            positiveDivisors.forEach(e -> {
                allDivisors.add(e);
                allDivisors.add(e*(-1));
            });
        } else {
            allDivisors = positiveDivisors;
        }
        return allDivisors.stream().sorted();
    }
}
