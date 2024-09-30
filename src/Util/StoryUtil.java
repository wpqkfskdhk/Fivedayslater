package Util;

import static Util.ColorUtil.*;
import static java.awt.Color.green;

public class StoryUtil {

    // 공통 slowPrint 간소화
    public static void slowPrint(String message, long millisPerChar, String color) {
        message = color + message + RESET;
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(millisPerChar);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println();
    }

    // 5일 후 스토리 intro
    public static void intro() {
        slowPrint("[ 5일 후 ]", 30, RED);
        slowPrint("달력을 노려보면서 마지막 남은 물 한 모금을 입 안에 털어넣는다.\n", 30, WHITE);
        slowPrint("20XX년 9월 22일. 좀비사태가 발생한 날.\n", 30, RED);
        slowPrint("너무 순식간에 일어난 일이라 생필품과 식량을 챙길 틈도 없이 집에서 고립되었다.\n", 30, WHITE);
        slowPrint("생존이 우선이라 생각하고 정보 수집을 위해 뉴스와 커뮤니티를 통해 소식을 전달받았고\n", 30, WHITE);
        slowPrint("정부에서는 빠르게 진압될 것이고 구조대를 파견하겠다고 하였다.\n", 30, WHITE);
        slowPrint("아직 초기이기에 진압이 가능할 것이라 믿었다.\n", 30, RED);
    }

    // 스킵가능
    public static void intro2() {
        slowPrint("20XX년 9월 23일. 좀비사태 발생 2일차..\n", 30, RED);
        slowPrint("저 멀리 구조를 위한 헬기 한 대가 돌아다니는 것을 보았다.. 내쪽으로 먼저 왔으면.\n", 30, WHITE);
        slowPrint("전기와 수도가 끊겼다...잠이나 자자\n", 30, WHITE);
    }
    // 스킵가능
    public static void intro3() {
        slowPrint("20XX년 9월 24일. 좀비사태 발생 3일차..\n", 30, RED);
        slowPrint("3일째가 되었을 떄 무언가 잘못되었음을 깨달았다...\n", 30, WHITE);
        slowPrint("사태발생 이틀과는 비교가 되지 않을 만큼 처참함이 창문밖으로 보였기 때문이다.\n", 30, WHITE);
        slowPrint("어제까지만 해도 구조를 위한 헬기가 보였지만 지금의 허공에는 그 무엇도 돌아다니지 않고 지상에 돌아다니는 좀비를 보고 있을 뿐이다.\n", 30, WHITE);
        slowPrint("얼마 있지 않던 식량조차 바닥을 보이고 있었다..\n", 30, RED);
    }

    //  본격 게임(스토리) 시작
    // 무기 선택 (식칼 vs 알루미늄 배트)
    public static void weaponChoice() {
        slowPrint("[ 5일 후 ]", 30, RED);
        slowPrint("20XX년 9월 27일. 5일째가 되었다.\n", 30, RED);
        slowPrint("이제는 올지 안올지도 모르는 구조대를 계속 기다리는건 바보같은 짓이다.\n", 30, WHITE);
        slowPrint("조금이라도 기력이 있을 때 나가는 것이 좋겠다.\n", 30, WHITE);
        slowPrint("남은 식량과 일부 생필품을 모두 챙겼고 내 몸을 지키기 위한 물건을 찾아야한다.\n", 30, WHITE);
        slowPrint("집안의 물건을 모두 찾아보았고 현관문 앞에 무기로 쓸만한 두개의 물건을 놓았다. \n", 30, WHITE);
        slowPrint("현재 무기로 쓸만한 것은 부엌에 있던 식칼과 취미로 했던 야구의 알루미늄 배트이다.\n", 30, WHITE);
        slowPrint("어설프게 양손으로 쓰다가는 역으로 당할 수 있다. 한 가지만 선택해야 한다.\n", 30, RED);
    }

    // 식칼 선택
    public static void knifeChoice(String charName) {
        slowPrint(charName + " : 좋아! 난 식칼을 선택하겠어.\n이제 복도로 나가자!\n", 30, BLUE);
        slowPrint("- 복도에 서성이는 좀비 1마리를 마주쳤습니다 -\n", 30, RED);
        slowPrint("당신은 식칼로 좀비를 공격했습니다. 하지만 식칼로 누구를 찔러보는 것이 처음인 당신은\n", 30, WHITE);
        slowPrint("힘 조절 실패로 인해 제대로 조준하지 못하고 좀비의 몸을 찔렀습니다.\n", 30,WHITE);
        slowPrint("깊숙하게 박힌 식칼은 빠지지 않았고, 발버둥치다 그대로 좀비에게 물립니다.\n", 30, WHITE);
        slowPrint("[ 당신은 사망하였습니다. ]\n", 30, RED);
    }

    // 알루미늄 배트 선택
    public static void batChoice(String charName) {
        slowPrint(charName + " : 좋아! 난 알루미늄 배트를 선택하겠어.\n이제 복도로 나가자!\n", 30, BLUE);
        slowPrint("- 복도에 서성이는 좀비 1마리를 마주쳤습니다 -\n", 30, RED);
    }

    // 복도에서 좀비 공격 선택
    public static void hallwayAttack() {
        slowPrint("당신은 알루미늄 배트로 좀비를 공격하였습니다.\n평소 야구를 좋아했던 당신은 좀비의 머리를 치는 것은 어렵지 않았습니다.\n", 30, WHITE);
        slowPrint("정확히 머리를 치고 좀비를 넘어뜨렸습니다.\n길이 열렸고, 당신은 복도 끝에 있는 계단으로 도망칩니다.\n", 30, WHITE);
        slowPrint("[ 피로도가 2 증가하였습니다. ]\n", 30, RED);
    }

    // 복도에서 도망 선택
    public static void hallwayRun() {
        slowPrint("당신은 도망가려 했지만 또 다른 좀비와 마주쳐서 사망했습니다.\n", 30, WHITE);
        slowPrint("[ 당신은 사망하였습니다. ]\n", 30, RED);
    }

    // 옥상 선택 (사망 루트)
    public static void roofChoice(String charName) {
        slowPrint(charName + " : 난 옥상으로 가겠어!\n", 30, BLUE);
        slowPrint("다행히 옥상으로 가는 도중엔 좀비를 만나지 않고 안전하게 도착할 수 있었다.\n", 30, WHITE);
        slowPrint("옥상문을 철저히 잠그고 가지고 있던 물품으로 SOS 신호를 보냈다.\n", 30, WHITE);
        slowPrint("저 멀리 구조 헬기가 지나갔고 나는 있는 힘껏 소리를 쳤지만, 헬기는 나를 보지 못하고 지나쳤다.\n", 30, WHITE);
        slowPrint("그로 인해 옥상에 좀비가 몰려들었고 나는 고립되었다.\n나갈 수도 없고, 식량은 떨어졌으며 구조 헬기는 더 이상 오지 않았다.\n", 30, WHITE);
        slowPrint("[ 당신은 굶어 죽었습니다. ]\n", 30, RED);
    }

    // 지상 선택 (생존 루트)
    public static void groundChoice(String charName) {
        slowPrint(charName + " : 난 지상으로 가겠어!\n", 30, BLUE);
        slowPrint("다행히 지상으로 내려가는 도중엔 좀비를 만나지 않고 1층으로 내려갈 수 있었다.\n", 30, WHITE);
        slowPrint("지상에 좀비들이 서성이고 있었지만 숫자가 많지 않아 곧바로 자동차 뒤에 숨었다.\n", 30, WHITE);
        slowPrint("라디오에서 멀지 않은 곳에 대피소가 있다는 소식을 들었다.\n", 30, WHITE);
    }

    // 이동 수단 선택 (수정된 메소드 추가)
    public static void handleBikeSituation() {
        slowPrint("슬슬 날이 어두워지고 있습니다.\n자전거를 계속 타고 가겠습니까, 아니면 지금이라도 자전거를 버리고 도망치겠습니까?\n", 30, BLACK);
    }

    // 선택지: 걷기, 자전거, 자동차
    public static void chooseTransport() {
        slowPrint("\n대피소로 가기 위한 이동수단을 선택하세요.\n", 30, WHITE);
    }

    // 걷기 선택
    public static void walkChoice(String charName) {
        slowPrint(charName + " : 좋아! 난 걸어서 이동하겠어.\n", 30, BLUE);
        slowPrint("속도는 느리지만 엄폐물을 활용하여 좀비를 피하고 당신은 대피소로 점점 다가갑니다.\n", 30, WHITE);
        slowPrint("[ 피로도가 1 증가하였습니다. ]\n", 30, RED);
    }

    // 자전거 선택
    public static void bikeChoice(String charName) {
        slowPrint(charName + " : 좋아! 난 자전거로 이동하겠어.\n", 30, BLUE);
        slowPrint("적당한 속도로 이동하며 몇몇 좀비가 당신을 쫓아오지만, 속도를 유지하며 안전하게 이동 중입니다.\n", 30, WHITE);
        slowPrint("[ 피로도가 1 증가하였습니다 ]\n", 30, RED);
    }

    // 자동차 선택 (사망 확정)
    public static void carChoice(String charName) {
        slowPrint(charName + " : 좋아! 난 자동차를 선택하겠어.\n", 30, BLUE);
        slowPrint("매우 빠르게 이동하지만 차량 소음으로 인해 근방의 좀비들이 몰려옵니다.\n", 30, WHITE);
        slowPrint("도로가 정체되어 길이 막히고, 사방에서 몰려온 좀비들에게 둘러싸입니다.\n", 30, WHITE);
        slowPrint("[ 당신은 사망하였습니다. ]\n", 30, RED);
    }

    // 자전거 계속 타기 (사망 루트)
    public static void bicycle_choice1(String charName) {
        slowPrint(charName + " : 계속 자전거를 타고 가자! 조금만 더 힘을 내서 좀비들과 거리를 벌리자!\n", 30, BLUE);
        slowPrint("하지만 브레이크가 고장난 것을 뒤늦게 알아차렸고, 당신은 지형지물과 충돌했습니다.\n", 30, WHITE);
        slowPrint("뒤늦게 도망치려 했지만 좀비 무리에게 둘러싸여 사망하였습니다.\n", 30, WHITE);
        slowPrint("[ 당신은 사망하였습니다. ]\n", 30, RED);
    }

    // 자전거 버리기 (사망 루트)
    public static void bicycle_choice2(String charName) {
        slowPrint(charName + " : 자전거를 버리고 걸어서 도망가는 것이 더 안전할 것 같아!\n", 30, BLUE);
        slowPrint("당신은 자전거를 멈춰 세우고 자동차에 던져 소음을 내며 좀비를 따돌렸습니다.\n", 30, WHITE);
        slowPrint("그러나 밤이 되어 미처 보지 못한 좀비들에게 둘러싸여 사망하였습니다.\n", 30, WHITE);
        slowPrint("[ 당신은 사망하였습니다. ]\n", 30, RED);
    }

    // 편의점 선택지 (들어가기 vs 지나치기)
    public static void convenienceStore() {
        slowPrint("계속 이동하던 중, 당신은 불이 꺼진 편의점을 발견했습니다.\n생필품이나 식량이 있을 수 있습니다.\n들어가시겠습니까, 아니면 지나치시겠습니까?\n", 30, WHITE);
    }

    // 편의점 들어가기 선택
    public static void store_choice1(String charName) {
        slowPrint(charName + " : 생존을 위해 어떤 위험이라도 감수해야해! 들어가보자!\n", 30, BLUE);
        // System.out.println(BLACK+"당신은 난장판이 된 편의점 안에서 약간의 식량과 생수 한 병을 발견했습니다."+RESET);
        slowPrint("당신은 난장판이 된 편의점 안에서 약간의 식량과 생수 한 병을 발견했습니다.\n", 30, WHITE);
        slowPrint("[ 피로도가 1 증가하였습니다 ]\n", 30, RED);
    }

    // 편의점 지나치기 선택
    public static void store_choice2(String charName) {
        slowPrint(charName + " : 혹시 모를 위험을 피하기 위해 편의점을 지나칩니다.\n이제 대피소가 멀지 않으니 그곳에서 생필품을 얻자.\n", 30, BLUE);
        slowPrint("[ 피로도가 1 증가하였습니다. ]\n", 30, RED);
    }

    // 잠 선택 (식량을 먹고 잔다 / 그냥 잔다)
    public static void sleepOrContinue(boolean hasFood) {
        if (hasFood) {
            slowPrint("식량을 먹고 자야겠다.\n배고픔과 갈증이 해소되었다. 기력을 회복하고 더 좋은 잠을 청한다.\n", 30, WHITE);
        } else {
            slowPrint("배고프고 갈증이 있지만 일단 자는 것이 나을 것 같다. 조금이라도 회복을 해야겠다.\n", 30, WHITE);
        }
        slowPrint("[ 피로도가 2 감소하였습니다 ]\n", 30, GREEN_BRIGHT);
    }

    // 도로 선택
    public static void roadChoice() {
        slowPrint("매우 덥지만 시야를 확인할 수 있는 도로로 이동하겠어!\n힘든 열기 속에 당신은 걸어갑니다.\n", 30, BLUE);
        slowPrint("[ 피로도가 1 증가하였습니다. ]\n", 30, RED);
    }

    // 지하철 선택 (사망 루트)
    public static void subwayChoice(String charName) {
        slowPrint(charName + "난 더운 열기 속에서 걸을 수 없어 지하철로 이동하겠어!\n", 30, BLUE);
        slowPrint("지하철로 내려가 어둠 속에서 걷던 중 육안으로 보이지 않던 좀비 무리와 마주쳤습니다.\n", 30, WHITE);
        slowPrint("뒤늦게 도망치려 했지만 소음으로 인해 더 많은 좀비들이 몰려왔고, 결국 사망하였습니다.\n", 30, WHITE);
        slowPrint("[ 당신은 사망하였습니다. ]\n", 30, RED);
    }

    // 대피소 선택지 (뛰어가기 vs 걸어가기)
    public static void shelterApproach(boolean run) {
        if (run) {
            slowPrint("대피소를 보고 흥분한 당신은 달려가려 했지만, 좀비로 오해받아 총에 맞아 사망했습니다.\n", 30, WHITE);
            slowPrint("[ 당신은 사망하였습니다. ]\n", 30, RED);
        } else {
            slowPrint("두 손을 들고 천천히 걸어가며 사람으로 인식되었습니다.\n검문소를 통과해 대피소에 안전하게 도착합니다.\n", 30, BLACK);
            slowPrint("[ 축하합니다! 당신은 생존하였습니다! ]\n", 30, YELLOW);
        }
    }

    // 랜덤
    public static void randomRestChance() {
        slowPrint("쉼 없이 걷다 보니 피로가 좀 쌓였다. 계속 가도 문제는 없을 것 같지만, 잠깐 숨 좀 고르는 것도 나쁘지 않겠지. 주위에 좀비가 안 보이기도 하고.\n", 30,BLUE_BRIGHT);
        slowPrint("좀비가 없다는 걸 한 번 더 확인한 다음, 주변 사물로 시야가 가려진 장소를 찾는다. 휴. 긴장을 완전히 늦출 수는 없지만 그래도 앉으니까 좀 살 것 같다. 여기서 잠깐 있다가 다시 이동해야지.\n", 30, BLUE_BRIGHT);
        slowPrint("[ 피로도가 2 감소하였습니다 ]\n", 30, GREEN_BRIGHT);
    }
    //랜덤
    public static void randomFoodChance() {
        slowPrint("이동 중에 바닥에 반짝거리는 무언가가 눈에 들어온다. 가서 확인해보니 에너지바다. 살짝 더러워진 것 빼곤 포장지가 온전하다.\n", 30, BLUE_BRIGHT);
        slowPrint("웬일로 운이 좋데. 에너지바를 주머니에 넣고 계속 가던 길 간다.\n", 30, BLUE_BRIGHT);
    }

    // 랜덤
    public static void randomWeaponChance() {
        slowPrint("가다가 무언가에 발이 걸린다.\n", 30, BLUE_BRIGHT);
        slowPrint("“...!”\n", 30, BLACK);
        slowPrint("튀어나올 뻔한 비명을 가까스로 참고 어렵게 균형을 잡는다.\n", 30, BLUE_BRIGHT);
        slowPrint("뭐지? 짜증스럽게 밑을 내려다보니 웬 철봉이 있다.\n", 30, BLACK);
        slowPrint("철봉이라...\n", 30, BLACK);
        slowPrint("두께도 괜찮고, 배트보다 길어서 가져가면 꽤 유용한 무기로 사용 할 수 있을 것 같다. 챙겨가도 나쁠 건 없겠지.\n", 30, BLACK);
        slowPrint("철봉 외엔 다른 거 얻을 게 없으니 계속 이동한다.\n", 30, BLUE_BRIGHT);
    }


}
