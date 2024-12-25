package y2024.java;

import day.Day;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 extends Day {
    public Day1() {
        super(2024, 1);
    }

    public List<List<Long>> parseInput() {
        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();
        List<String> lines = getInput().lines().toList();
        for (String line : lines) {
            String[] parts = line.split("\\s+");
            left.add(Long.parseLong(parts[0]));
            right.add(Long.parseLong(parts[1]));
        }
        return List.of(left, right);
    }

    @NotNull
    @Override
    protected Object part1() {
        List<List<Long>> lists = parseInput();
        List<Long> left = lists.get(0);
        List<Long> right = lists.get(1);

        left.sort(Long::compareTo);
        right.sort(Long::compareTo);

        assert left.size() == right.size();

        long sum = 0;
        for (int i = 0; i < left.size(); i++) {
            sum += Math.abs(left.get(i) - right.get(i));
        }
        return sum;
    }

    @NotNull
    @Override
    protected Object part2() {
        List<List<Long>> lists = parseInput();
        List<Long> left = lists.get(0);
        List<Long> right = lists.get(1);

        Map<Long, Long> frequency = new HashMap<>();
        for (Long l : right) {
            frequency.put(l, frequency.getOrDefault(l, 0L) + 1);
        }
        return left.stream().map(l -> l * frequency.getOrDefault(l, 0L)).reduce(0L, Long::sum);
    }

    public static void main(String[] args) {
        new Day1().run();
    }
}
