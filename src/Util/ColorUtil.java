package Util;

public class ColorUtil {
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    // 밝은 색상
    public static final String BLACK_BRIGHT = "\u001B[90m"; // 밝은 검은색 (회색)
    public static final String RED_BRIGHT = "\u001B[91m";   // 밝은 빨간색
    public static final String GREEN_BRIGHT = "\u001B[92m"; // 밝은 초록색
    public static final String YELLOW_BRIGHT = "\u001B[93m";// 밝은 노란색
    public static final String BLUE_BRIGHT = "\u001B[94m";  // 밝은 파란색
    public static final String MAGENTA_BRIGHT = "\u001B[95m";// 밝은 마젠타
    public static final String CYAN_BRIGHT = "\u001B[96m";  // 밝은 시안
    public static final String WHITE_BRIGHT = "\u001B[97m"; // 밝은 흰색

    // 초기화
    public static final String RESET = "\u001B[0m";

    public static String colorize(String text, String colorCode) {
        // 지정된 색상 코드를 텍스트 앞에 추가하고, RESET 코드를 끝에 추가하여
        // 텍스트 출력 후 콘솔 색상을 기본값으로 리셋.
        // 이 메서드는 slowPrint와 이어집니다.
        return colorCode + text + RESET;
    }
}
