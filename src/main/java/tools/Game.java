package tools;

import java.util.Map;

public class Game {
    public static Integer[] guessing(Integer correctNumber, Integer ourNumber) { //можно было сразу использовать Integer
        Integer[] array = new Integer[]{0,0};
        for (int i = 0; i < 4; i++) {
            if (correctNumber.toString().charAt(i) == ourNumber.toString().charAt(i)) {
                array[0]++;
            } else if (correctNumber.toString().contains(ourNumber.toString().substring(i,i+1))) {
                array[1]++;
            }
        }
        return array;
    }

    //метод изначально заполняющий базу всеми вариантами чисел
    public static Map<String,Boolean> getMainMap(){
        return null;
    }
    //метод приводящий базу к изначальному виду.
    public static Map<String,Boolean> getDefaultMap(Map<String,Boolean> map){
        map.values().stream().map(s -> s=true).count();
        return map;
    }
    //метод отсеивающий в базе неподходящие варианты в процессе обработки числа
    public static Map<String,Boolean> processNumber(Map<String,Boolean> map,String number, Integer[] mas){
        return map;
    }

    //метод отсеивающий числа в базе по подсказке
    public static Map<String,Boolean> getSitoMap(Map<String,Boolean> map, String sito){
        return map;
    }

    //метод возвращающий кол-во оставшихся вариантов в базе
    public static Integer getTrueInMap(Map<String,Boolean> map){
        Integer result = 0;
        return result;
    }
}

