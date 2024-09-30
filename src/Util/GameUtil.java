package Util;

import java.util.Scanner;
import static Util.ColorUtil.*;

public class GameUtil {
    // 선택지 출력 및 입력 받기
    public static int getChoice(Scanner sc, String message, String... options) {
        System.out.println(message);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print(PURPLE +"나의 선택 >>  " + RESET);
        return sc.nextInt();
    }

    // 게임 종료 메시지
    public static void endGame() {
        System.out.println();
        System.out.println("게임이 종료되었습니다.");
        System.out.println("이용해주셔서 감사합니다.");
        System.out.println();

    }
}
