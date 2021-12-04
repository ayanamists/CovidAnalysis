package aya;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;
import org.jpl7.Query;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Functional {
    private List<Item> death;
    private List<Item> confirmed;
    private List<Item> recovered;

    LocalDate begin = LocalDate.of(2020, 1, 22);
    LocalDate end = LocalDate.of(2021, 8, 16);

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
                                            Supplier<T2> def) {
        Map<T1, T2> m = new HashMap<>();
        l.forEach(i -> {
            T1 key = getKey.apply(i);
            T2 v = m.get(key);
            if (v == null) {
                v = def.get();
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
                }, () -> 0);
    }

    private int getWeek(LocalDate date) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        return weekNumber;
    }

    public int task2WeekMeta(String country, int year, int week) {
        return confirmed.stream().filter(i -> i.country.equals(country)).map(i ->
        {
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.WEEK_OF_YEAR, week);
            LocalDate l = LocalDate.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
            if (l.isBefore(LocalDate.of(2020, 1, 22)) ||
                    l.isAfter(LocalDate.of(2021, 8, 16))) {
                throw new UnsupportedOperationException("task2Week: time " +
                        l.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " invalid");
            } else {
                return i.records.get(l.plusDays(7));
            }
        }).reduce(0, Integer::sum);
    }

    public List<Object[]> task2Week(String country){
        var res = new LinkedList<Object[]>();
        int begin2020 = getWeek(begin);
        int end2020 = getWeek(LocalDate.of(2020, 12, 31));
        System.out.println(begin2020);
        for (var i = begin2020; i <= end2020; ++i){
            Object [] o = {"2020/" + i, task2WeekMeta(country, 2020 , i)};
            res.add(o);
        }

        int end2021 = getWeek(end.minusDays(7));
        for (var i = 1; i <= end2021; ++i) {
            Object[] o = {"2021/" + i, task2WeekMeta(country , 2021, i)};
            res.add(o);
        }
        return res;
    }

    public List<Object[]> task2Month(String country) {
        var res = new LinkedList<Object[]>();
        for (var i = 1; i < 13 ; ++i) {
            Object[] o = {"2020/" + i, task2MonthMeta(country, 2020, i)};
            res.add(o);
        }
        for (var i = 1; i < 8;++i) {
            Object[] o = {"2021/" + i, task2MonthMeta(country, 2021, i)};
            res.add(o);
        }
        return res;
    }
    public int task2MonthMeta(String country, int year, int month) {
        return confirmed.stream().filter(i -> i.country.equals(country)).map(i ->
        {
            LocalDate l = LocalDate.of(year, month, 1);
            l = l.withDayOfMonth(
                    l.getMonth().length(l.isLeapYear()));
            if (l.isBefore(LocalDate.of(2020, 1, 22)) ||
                    l.isAfter(LocalDate.of(2021, 8, 16))) {
                throw new UnsupportedOperationException("task2Week: time " +
                        l.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " invalid");
            } else {
                return i.records.get(l);
            }
        }).reduce(0, Integer::sum);
    }

    private int[] updateFunction(Item i, int[] v) {
        var l = i.records.get(LocalDate.of(2020, 1, 22));
        var h = i.records.get(LocalDate.of(2021, 8, 16));
        v[0] += l;
        v[1] += h;
        return v;
    }
    public List<Map<String, int[]>> task3() {
        var res = new HashMap<String, int[]>();
        var m1 = formMapData(death, i -> i.country, this::updateFunction, () -> new int[2]);
        var m2 = formMapData(recovered, i -> i.country, this::updateFunction, () -> new int[2]);
        var l = new LinkedList<Map<String, int[]>>();
        l.add(m1);
        l.add(m2);
        return l;
    }

    public void ascendingsorting(String country){
        Query query=new Query ("consult('C:/Users/Acer/CovidAnalysis/homework/sample2.pl').");
        if(query.hasNext()) {
            Query query1=new Query("insortAsc(" + country + ",Sorted).");

            if(query1.hasNext()) {
                System.out.println(query1.next().get("Sorted"));
            }
        }
//        Query query = new Query("consult('C:/Users/Acer/CovidAnalysis/homework/sample2.pl').");
//        System.out.println((query.hasSolution()) ? true : false);
    }

    public void descendingsorting(String country){
        Query query=new Query ("consult('C:/Users/Acer/CovidAnalysis/homework/sample2.pl').");
        if(query.hasNext()) {
            Query query1=new Query("insortDesc(" + country + ",Sorted).");

            if(query1.hasNext()) {
                System.out.println(query1.next().get("Sorted"));
            }
        }
    }
}
