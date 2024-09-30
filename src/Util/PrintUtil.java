package Util;

import java.util.Scanner;
import static Util.ColorUtil.*;
import ZombieDTO.Characters;
public class PrintUtil {

    // 슬로우 print
    public static void slowPrint(String message, long millisPerChar, String color) {
        message = ColorUtil.colorize(message, color); // 색상 적용
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(millisPerChar);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    // 화면 클리어 시각적 효과
    public static void printClear() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    // 라인
    public static void printLine() {
        slowPrint(GREEN + "════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n", 5, ColorUtil.RESET);
    }

    // 접속 환영
    public static void hello() {
        String[][] linesAndColors = {
                {"5 Days Later 접속을 환영합니다", RED},
                {"생존을 위한 게임을 시작하세요", RED},
                {"당신의 선택이 미래를 바꿀 수 있습니다.", RED}
        };

        for (String[] lineAndColor : linesAndColors) {
            String line = lineAndColor[0];
            String color = lineAndColor[1];
            slowPrint(line + "\n", 30, color);
        }
    }

    // 소개
    public static void gameIntroduction() {
        slowPrint("\n[게임 소개]\n‘5 Days Later’는 ~~~~~~~~~ .\r즐겜!\n", 20, ColorUtil.BLUE);
    }

    // 게임오버 (사망)
    public static void gameOver() {
        // slowPrint("\n사망하였습니다. \n", 10, RED);
        // slowPrint("[ GAME OVER ]\n", 10, RED);
        System.out.println(RED + " ▄████  ▄▄▄      ▓█████  ███▄ ▄███▓    ▒█████   ██▒   █▓▓█████  ██▀███  " + RESET);
        System.out.println(RED + " ██▒ ▀█▒▒████▄    ▓█   ▀ ▓██▒▀█▀ ██▒   ▒██▒  ██▒▓██░   █▒▓█   ▀ ▓██ ▒ ██▒" + RESET);
        System.out.println(RED + " ▒██░▄▄▄░▒██  ▀█▄  ▒███   ▓██    ▓██░   ▒██░  ██▒ ▓██  █▒░▒███   ▓██ ░▄█ ▒" + RESET);
        System.out.println(RED + " ░▓█  ██▓░██▄▄▄▄██ ▒▓█  ▄ ▒██    ▒██    ▒██   ██░  ▒██ █░░▒▓█  ▄ ▒██▀▀█▄   " + RESET);
        System.out.println(RED + " ░▒▓███▀▒ ▓█   ▓██▒░▒████▒▒██▒   ░██▒   ░ ████▓▒░   ▒▀█░  ░▒████▒░██▓ ▒██▒ " + RESET);
        System.out.println(RED + " ░▒   ▒  ▒▒   ▓▒█░░░ ▒░ ░░ ▒░   ░  ░   ░ ▒░▒░▒░    ░ ▐░  ░░ ▒░ ░░ ▒▓ ░▒▓░" + RESET);
        System.out.println(RED + " ░   ░   ▒   ▒▒ ░ ░ ░  ░░  ░      ░     ░ ▒ ▒░    ░ ░░   ░ ░  ░  ░▒ ░ ▒░" + RESET);
        System.out.println(RED + " ░ ░   ░   ░   ▒      ░   ░      ░      ░ ░ ░ ▒       ░░     ░     ░░   ░" + RESET);
        System.out.println(RED + " ░       ░  ░   ░  ░       ░          ░ ░        ░     ░  ░   ░");


    }

    // 게임 종료
    public static void goodbyeMessage() {
        slowPrint("\n5 Days Later를 이용해주셔서 감사합니다. 안녕히 가세요!\n", 20, RED);
    }

    // 잘못된 입력
    public static void printInvalidOptionMessage() {
        slowPrint("\n잘못된 옵션입니다. 다시 입력해주세요.\n", 50, ColorUtil.WHITE);
    }

    // 입력 오류
    public static void printInputErrorMessage() {
        slowPrint("\n오류: 유효한 입력을 해주세요.\n", 50, RED);
    }


    // 데모 멘트
    public static void demo() {
        System.out.println("지금까지 [5일 후] DEMO_VERSION 이었습니다.");
        System.out.println("정식 출시 이전까지 기다려주세요");
        System.out.println("플레이 해주셔서 감사합니다");   // 간단한 인사, 데모 종료 메세지에 넣어도 될 것 같음
    }

    public static void titleArt() { //게임시작 로고
        System.out.println();
        System.out.println(RED + "  █████▒██▓ ██▒   █▓▓█████    ▓█████▄  ▄▄▄     ▓██   ██▓  ██████     ██▓    ▄▄▄     ▄▄▄█████▓▓█████  ██▀███  " + RESET);
        System.out.println(RED + "▓██   ▒▓██▒▓██░   █▒▓█   ▀    ▒██▀ ██▌▒████▄    ▒██  ██▒▒██    ▒    ▓██▒   ▒████▄   ▓  ██▒ ▓▒▓█   ▀ ▓██ ▒ ██▒" + RESET);
        System.out.println(RED + "▒████ ░▒██▒ ▓██  █▒░▒███      ░██   █▌▒██  ▀█▄   ▒██ ██░░ ▓██▄      ▒██░   ▒██  ▀█▄ ▒ ▓██░ ▒░▒███   ▓██ ░▄█ ▒" + RESET);
        System.out.println(RED + "░▓█▒  ░░██░  ▒██ █░░▒▓█  ▄    ░▓█▄   ▌░██▄▄▄▄██  ░ ▐██▓░  ▒   ██▒   ▒██░   ░██▄▄▄▄██░ ▓██▓ ░ ▒▓█  ▄ ▒██▀▀█▄  " + RESET);
        System.out.println(RED + "░▒█░   ░██░   ▒▀█░  ░▒████▒   ░▒████▓  ▓█   ▓██▒ ░ ██▒▓░▒██████▒▒   ░██████▒▓█   ▓██▒ ▒██▒ ░ ░▒████▒░██▓ ▒██▒" + RESET);
        System.out.println(RED + " ▒ ░   ░▓     ░ ▐░  ░░ ▒░ ░    ▒▒▓  ▒  ▒▒   ▓▒█░  ██▒▒▒ ▒ ▒▓▒ ▒ ░   ░ ▒░▓  ░▒▒   ▓▒█░ ▒ ░░   ░░ ▒░ ░░ ▒▓ ░▒▓░" + RESET);
        System.out.println(RED + " ░      ▒ ░   ░ ░░   ░ ░  ░    ░ ▒  ▒   ▒   ▒▒ ░▓██ ░▒░ ░ ░▒  ░ ░   ░ ░ ▒  ░ ▒   ▒▒ ░   ░     ░ ░  ░  ░▒ ░ ▒░" + RESET);
        System.out.println(RED + " ░ ░    ▒ ░     ░░     ░       ░ ░  ░   ░   ▒   ▒ ▒ ░░  ░  ░  ░       ░ ░    ░   ▒    ░         ░     ░░   ░ " + RESET);
        System.out.println(RED + "        ░        ░     ░  ░      ░          ░  ░░ ░           ░         ░  ░     ░  ░           ░  ░   ░     " + RESET);
        System.out.println(RED + "                ░              ░                ░ ░                                                           " + RESET);
        System.out.println();
    }

    // 라인
    public static void redLine() {
        slowPrint(RED + "════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n", 5, ColorUtil.RESET);
    }

    public static void helloArt() { //입장을 환영합니다~
        System.out.println(RED + "          ██░ ██ ▓█████  ██▓     ██▓     ▒█████ " + RESET);
        System.out.println(RED + "          ▓██░ ██▒▓█   ▀ ▓██▒    ▓██▒    ▒██▒  ██▒" + RESET);
        System.out.println(RED + "          ▒██▀▀██░▒███   ▒██░    ▒██░    ▒██░  ██▒" + RESET);
        System.out.println(RED + "          ░▓█ ░██ ▒▓█  ▄ ▒██░    ▒██░    ▒██   ██░" + RESET);
        System.out.println(RED + "          ░▓█▒░██▓░▒████▒░██████▒░██████▒░ ████▓▒░" + RESET);
        System.out.println(RED + "          ▒ ░░▒░▒░░ ▒░ ░░ ▒░▓  ░░ ▒░▓  ░░ ▒░▒░▒░" + RESET);
        System.out.println(RED + "          ▒ ░▒░ ░ ░ ░  ░░ ░ ▒  ░░ ░ ▒  ░  ░ ▒ ▒░" + RESET);
        System.out.println(RED + "          ░  ░░ ░   ░     ░ ░     ░ ░   ░ ░ ░ ▒" + RESET);
        System.out.println(RED + "          ░  ░  ░   ░  ░    ░  ░    ░  ░    ░ ░" + RESET);
        System.out.println();
    }
}
