package aya;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Functional {
    private List<Item> death;
    private List<Item> confirmed;
    private List<Item> recovered;

    public Functional() throws IOException {
        confirmed = readFromFile("resources/time_series_covid19_confirmed_global.csv");
        death = readFromFile("resources/time_series_covid19_deaths_global.csv");
        recovered = readFromFile("resources/time_series_covid19_recovered_global.csv");
    }

    private List<Item> readFromFile(String path) throws IOException {
        return freshData(readToList(new CSVReaderHeaderAware(
                new FileReader(new File(path).getAbsoluteFile()))));
    }

    public List<Map<String, String>> readToList(CSVReaderHeaderAware reader) {
        List<Map<String, String>> data =new LinkedList<>();
        while(true) {
            try {
                Map<String, String> now = reader.readMap();
                if (now == null) {
                    break;
                } else {
                    data.add(now);
                }
            } catch (IOException | CsvValidationException e) {
                System.out.println("csv invalid");
            }
        }
        return data;
    }

    private List<Item> freshData(List<Map<String, String>> data) {
        return data.stream().map(i -> {
            Item item = new Item();
            item.records = new HashMap<>();
            i.forEach((key, value) -> {
                var pattern = Pattern.compile("([0-9]+)/([0-9]+)/([0-9]+)");
                var matches = pattern.matcher(key);
                if (key.equals("Province/State")) {
                    item.province = value;
                } else if (key.equals("Country/Region")) {
                    item.country = value;
                } else if (matches.matches()) {
                    var day = Integer.parseInt(matches.group(2));
                    var month = Integer.parseInt(matches.group(1));
                    var year = Integer.parseInt("20" + matches.group(3));
                    var localDate = LocalDate.of(year, month, day);
                    item.records.put(localDate, Integer.parseInt(value));
                }
            });
            return item;
        }).collect(Collectors.toList());
    }

    private<T1, T2> Map<T1, T2> formMapData(List<Item> l,
                                            Function<Item, T1> getKey,
                                            BiFunction<Item, T2, T2> reducer,
                                            T2 def) {
        Map<T1, T2> m = new HashMap<>();
        l.forEach(i -> {
            T1 key = getKey.apply(i);
            T2 v = m.get(key);
            if (v == null) {
                v = def;
            }
            v = reducer.apply(i, v);
            m.put(key, v);
        });
        return m;
    }

    public Map<String, Integer> task1() {
        return formMapData(confirmed, i -> i.country,
                (i, v) -> {
                   var list = new ArrayList<Integer>(i.records.values());
                   var now = list.get(list.size() - 1);
                   return v + now;
                }, 0);
    }
}
