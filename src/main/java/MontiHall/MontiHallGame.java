package MontiHall;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MontiHallGame {
    private static Random random;
    private static Map<Integer, Boolean> doNotChangeDoor;
    private static Map<Integer, Boolean> changeDoor;
    private final static int COUNT_DOORS = 3;
    private static int tryCount;
    private static int winCount;

    public static void main(String[] args) {
        random = new Random();
        doNotChangeDoor = new HashMap<>();
        changeDoor = new HashMap<>();
        tryCount = 1000;
        winCount = 0;

        for (int i = 0; i < tryCount; i++) {
            play(i);
        }
        for (Map.Entry<Integer, Boolean> entry : doNotChangeDoor.entrySet()) {
            if (entry.getValue()) {
                winCount++;
            }
        }
        System.out.println("Если игрок не будет менять свой выбор двери после предложения ведущего,то при " + tryCount + "  игр он одержит победу " + winCount + "  раз");
        winCount = 0;
        for (Map.Entry<Integer, Boolean> entry : changeDoor.entrySet()) {
            if (entry.getValue()) {
                winCount++;
            }
        }
        System.out.println("Если игрок поменяет свой выбор двери после предложения ведущего, то при " + tryCount + " игр он одержит победу " + winCount + "  раз");
    }

    private static void play(int round) {
        int successChoice = random.nextInt(COUNT_DOORS);
        int firstChoice = random.nextInt(COUNT_DOORS);
        int open = -1;
        int secondChoice = -1;
        for (int i = 0; i < COUNT_DOORS; i++) {
            if (i != successChoice && i != firstChoice) {
                open = i;
            }
        }
        for (int i = 0; i < COUNT_DOORS; i++) {
            if (i != open && i != firstChoice) {
                secondChoice = firstChoice;
            }
        }
        doNotChangeDoor.put(round, successChoice == secondChoice);
        for (int i = 0; i < COUNT_DOORS; i++) {
            if (i != open && i != firstChoice) {
                secondChoice = i;
            }
        }
        changeDoor.put(round, successChoice == secondChoice);

    }

}