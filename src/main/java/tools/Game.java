package tools;

public class Game {
    public static Integer[] guessing(Integer correctNumber, Integer ourNumber) { //можно было сразу использовать Integer
        Integer[] array = new Integer[]{0,0};
        for (int i = 0; i < 4; i++) {
            if (correctNumber.toString().charAt(i) == ourNumber.toString().charAt(i)) {
                array[0]++; //
            } else if (correctNumber.toString().contains(ourNumber.toString().substring(i,i+1))) {
                array[1]++;
            }
        }
        return array;
    }
}

