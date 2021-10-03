package tools;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Game {
    public static Integer[] guessing(String correctNumber, String ourNumber) { //можно было сразу использовать Integer
        Integer[] array = new Integer[]{0,0};
        for (int i = 0; i < 4; i++) {
            if (correctNumber.charAt(i) == ourNumber.charAt(i)) {
                array[0]++;
            } else if (correctNumber.contains(ourNumber.substring(i,i+1))) {
                array[1]++;
            }
        }
        return array;
    }

    //метод изначально заполняющий базу всеми вариантами чисел
    public static LinkedHashMap<String, Boolean> getMainMap() {
        LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();
        for (int i = 0; i <= 9999; i++) {
            int x = i;
            int a = x % 10;
            x /= 10;
            int b = x % 10;
            x /= 10;
            int d = x % 10;
            x /= 10;
            if (a != b && a != d && a != x && b != d && b != x && d != x) {
                map.put(String.format("%04d", i), true);
            }
        }
        return map;
    }

    //метод приводящий базу к изначальному виду.
    public static Map<String,Boolean> getDefaultMap(Map<String,Boolean> map){
//        for(Map.Entry<String,Boolean> entry:map.entrySet()) entry.setValue(true);
            map.entrySet().forEach(x-> x.setValue(true));
        return map;
    }
    //метод отсеивающий в базе неподходящие варианты в процессе обработки числа
    public static Map<String,Boolean> processNumber(Map<String,Boolean> map,String number, Integer[] mas){
            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                if (!Arrays.equals(mas, guessing(entry.getKey().trim(), number.trim()))) {
                    entry.setValue(false);
                }
            }
            return map;
        }

    //метод отсеивающий числа в базе по подсказке
    public static Map<String,Boolean> getSitoMap(Map<String,Boolean> map, String sito){
        for(Map.Entry<String,Boolean> entry:map.entrySet()) {
            for (Character ch : sito.toCharArray()) {
                if (entry.getKey().contains(ch.toString())) {
                    entry.setValue(false);
                }
            }
        }
        return map;
    }

    //метод возвращающий кол-во оставшихся вариантов в базе
    public static Integer getTrueInMap(Map<String,Boolean> map){
        return (int)map.values().stream().filter( f -> f).count();
    }

    public static String getRecommendation(Map<String,Boolean> map){
        String result =map.entrySet().stream().filter(x -> x.getValue()==true).findAny().get().getKey();
        return result;
    }
}

