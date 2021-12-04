package aya;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Item {
    public String province;
    public String country;
    public Map<LocalDate, Integer> records;

    public LocalDate begin;
    public LocalDate end;

    public int getLastDay() {
        if (end == null) {
            var r = records.keySet().stream().sorted().collect(Collectors.toList());
            end = r.get(r.size() - 1);
        }
        return records.get(end);
    }

    public int getMax() {
        return Collections.max(records.values());
    }

    public int getMin() {
        return Collections.min(records.values());
    }
}
