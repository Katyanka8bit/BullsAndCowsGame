public class Game {
    public static Object[] guessing(int correctNumber, int ourNumber) {
        Object[] array = new Object[2];
        int cows = 0;
        int bulls = 0;
        String correctString = correctNumber + "";
        String ourString = ourNumber + "";
        char[] number = ("" + ourString).toCharArray();
        for (int i = 0; i < 4; i++) {
            if (correctString.charAt(i) == ourString.charAt(i)) {
                bulls++;

            } else if (correctString.contains(number[i] + "")) {
                cows++;
            }
        }
        array[0] = bulls;
        array[1] = cows;
        return array;
    }
}

