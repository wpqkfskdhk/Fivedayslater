package ZombieMain;

import ZombieDAO.ZombieSQL;
import Util.GameMainUtil;
import Util.PlayerUtil;
import Util.SaveUtil;
import ZombieDTO.Characters;


import java.util.List;
import java.util.Scanner;

import static Util.ColorUtil.GREEN;

public class GameMain {
    private final Scanner sc = new Scanner(System.in);
    private final PlayerUtil playerUtil = new PlayerUtil();
    private final SaveUtil gameSaveUtil = new SaveUtil();
    private final GameMainUtil gameMainUtil = new GameMainUtil(this);  // GameMain 객체를 전달
    private final ZombieSQL sql = new ZombieSQL();
    private boolean gameRunning = true;

    public static void main(String[] args) {
        GameMain game = new GameMain();
        game.startProgram();
    }

    public void startProgram() {
        while (gameRunning) {
            System.out.println();
            System.out.println(GREEN + "╔══════════════════════════ MENU ══════════════════════════════╗");
            System.out.println(GREEN + "║                   WELCOME TO 5 Days Later                    ║");
            System.out.println(GREEN + "║     1. LOGIN                                                 ║");
            System.out.println(GREEN + "║     2. JOIN                                                  ║");
            System.out.println(GREEN + "║     3. CLOSE                                                 ║");
            System.out.println(GREEN + "╚══════════════════════════════════════════════════════════════╝" );
            System.out.print("원하시는 서비스를 선택하여 주세요 >> ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    playerUtil.join();
                    break;
                case 3:
                    gameRunning = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void login() {
        String loggedInPlayerId = playerUtil.login();
        if (loggedInPlayerId != null) {
            gameMainUtil.setLoggedInPlayerId(loggedInPlayerId);  // 로그인 성공 후 ID 설정
            gameMenu();
        }
    }

    public void gameMenu() {
        boolean inGameMenu = true;

        while (inGameMenu) {

            System.out.println(GREEN + "╔══════════════════════════ MENU ══════════════════════════════╗");
            System.out.println(GREEN + "║                   WELCOME TO 5 Days Later                    ║");
            System.out.println(GREEN + "     1. START NEW GAME                                                  ");
            System.out.println(GREEN + "     2. LOAD GAME                                                  ");
            System.out.println(GREEN + "     3. CHARACTER LIST                                               ");
            System.out.println(GREEN + "     4. DELETE ACCOUNT                                             ");
            System.out.println(GREEN + "║    5. LOGOUT                                                 ║        ");
            System.out.println(GREEN + "╚══════════════════════════════════════════════════════════════╝" );
            System.out.print("원하시는 서비스를 선택하여 주세요 >> ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    gameMainUtil.startGame();
                    break;
                case 2:
                    loadGame();
                    break;
                case 3:
                    manageCharacters();  // 캐릭터 관리
                    break;
                case 4:
                    deleteAccount();
                    break;
                case 5:
                    inGameMenu = false;
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }































    // 게임 불러오기 수정
    private void loadGame() {
        System.out.println();
        System.out.print(" 캐릭터 번호를 입력해주세요 >> ");

        List<Characters> characterList = playerUtil.getCharacterList(gameMainUtil.getLoggedInPlayerId());  // 플레이어 ID로 캐릭터 목록 가져오기

        // 캐릭터 목록을 번호와 함께 출력
        if (characterList.isEmpty()) {
            System.out.println("불러올 캐릭터가 없습니다.");
            return;
        }

        for (int i = 0; i < characterList.size(); i++) {
            Characters character = characterList.get(i);
            System.out.println((i + 1) + ". 이름: " + character.getCharName() + " | 피로도: " + character.getHP());
        }

        // 번호 선택 입력
        System.out.println();
        System.out.print("불러올 캐릭터 번호를 선택해주세요 ");
        int choice = sc.nextInt();
        sc.nextLine();  // 버퍼 비우기

        // 번호 유효성 검사
        if (choice < 1 || choice > characterList.size()) {
            System.out.println("잘못된 선택입니다.");
            return;
        }

        // 선택된 캐릭터 가져오기
        Characters selectedCharacter = characterList.get(choice - 1);

        // 캐릭터 정보를 GameMainUtil에 설정
        gameMainUtil.setCharacter(selectedCharacter);

        // 저장된 게임 데이터를 불러오기
        String saveData = gameSaveUtil.loadGame(gameMainUtil.getLoggedInPlayerId(), selectedCharacter.getCharId());
        if (saveData != null) {
            gameMainUtil.resumeFromSavePoint(saveData);  // 저장된 시점에서 게임을 이어서 진행
        } else {
            System.out.println();
            System.out.println("저장된 게임이 없습니다.");
            System.out.println("새 게임을 시작합니다.");
            System.out.println();
            gameMainUtil.resetCharacterToStart(selectedCharacter.getCharId());  // 캐릭터를 초기화
            gameMainUtil.storySequence();  // 새로운 게임 시작
        }
    }




    // 캐릭터 관리 기능 수정 (번호를 통한 선택)
    private void manageCharacters() {
        System.out.println();
        System.out.println("로그인한 사용자 ID: " + gameMainUtil.getLoggedInPlayerId());
        System.out.println("---------------------------------");
        List<Characters> characterList = sql.characterList(gameMainUtil.getLoggedInPlayerId());
        if (characterList.isEmpty()) {
            System.out.println("생성된 캐릭터가 없습니다.");
            return;
        }

        for (int i = 0; i < characterList.size(); i++) {
            Characters character = characterList.get(i);
            System.out.println((i + 1) + ". 이름: " + character.getCharName() + " | 피로도: " + character.getHP());
        }

        System.out.println();
        System.out.println("================================================================");
        System.out.println("[1] 캐릭터 정보 조회  |  [2] 저장된 게임 불러오기  |  [3] 캐릭터 삭제");
        System.out.println("================================================================");
        System.out.print("원하시는 서비스를 선택하여주세요 >> ");
        int choice = sc.nextInt();
        System.out.println();
        sc.nextLine(); // 버퍼 비우기

        System.out.print("선택할 캐릭터 번호를 입력하세요 >> ");
        int charIndex = sc.nextInt() - 1;
        sc.nextLine(); // 버퍼 비우기
        System.out.println();

        if (charIndex < 0 || charIndex >= characterList.size()) {
            System.out.println("잘못된 번호를 입력하였습니다.");
            System.out.println();
            return;
        }

        int charId = characterList.get(charIndex).getCharId();

        switch (choice) {
            case 1:
                viewCharacterInfo(charId);  // 캐릭터 정보 조회 기능
                break;
            case 2:
                loadCharacterGame(charId);  // 저장된 게임 불러오기
                break;
            case 3:
                deleteCharacter(charId);    // 캐릭터 삭제
                break;
            default:
                System.out.println("잘못된 선택입니다.");
        }
    }




    // 저장된 게임 불러오기 기능
    private void loadCharacterGame(int charId) {
        Characters character = sql.getCharacterById(charId);  // 캐릭터 정보를 다시 가져옴
        if (character != null) {
            gameMainUtil.setCharacter(character);  // 캐릭터 정보 설정
        } else {
            System.out.println("캐릭터 정보를 불러오지 못했습니다.");
            return;
        }
        String saveData = gameSaveUtil.loadGame(gameMainUtil.getLoggedInPlayerId(), charId);
        if (saveData != null) {
            gameMainUtil.resumeFromSavePoint(saveData);  // 저장된 게임을 이어서 진행
        } else {
            System.out.println("저장된 게임이 없습니다.");
            System.out.println("새 게임을 시작합니다.");
            gameMainUtil.resetCharacterToStart(charId);  // 캐릭터를 초기화
            gameMainUtil.startGame();                    // 새로운 게임 시작


        }
    }


    // 캐릭터 삭제 기능
    private void deleteCharacter(int charId) {
        boolean deleted = sql.deleteCharacter(charId);  // 캐릭터 삭제 SQL 호출
        if (deleted) {
            System.out.println("캐릭터가 삭제되었습니다.");
        } else {
            System.out.println("캐릭터 삭제 중 오류가 발생했습니다.");
        }
    }

    private void deleteAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("삭제할 플레이어의 ID를 입력하세요: ");
        String pId = scanner.nextLine();
        sql.deletePlayer(pId);  // 계정삭제 sql호출
    }



    private void viewCharacterInfo(int charId) {
        Characters character = sql.getCharacterById(charId);  // 캐릭터 정보를 가져오는 SQL 호출
        List<String> inventoryList = sql.viewInventoryList(charId);

        if (character != null) {
            System.out.println("╔═════════════════════════════════════════════════╗");
            System.out.println("║                ★Character 정보★                 ║");
            System.out.println("╠═════════════════════════════════════════════════╣");
            System.out.printf("║ 이름: %-40s   ║\n", character.getCharName());
            System.out.printf("║ 체력: %-39d    ║\n", character.getHP());
            System.out.println("╠═════════════════════════════════════════════════╣");
            System.out.println("║               ★ 인벤토리 목록 ★                   ║");
            System.out.println("╠═════════════════════════════════════════════════╣");


            if (!inventoryList.isEmpty()) {
                for (int i = 0; i < inventoryList.size(); i++) {
                    System.out.printf("║  %-41s║\n", inventoryList.get(i));
                    System.out.println();
                }
            } else {
                System.out.println("║          인벤토리가 비어있습니다.       ║");
            }

            System.out.println("╚═════════════════════════════════════════════════╝");
        } else {
            System.out.println("╔═════════════════════════════════════════════════╗");
            System.out.println("          캐릭터 정보를 찾을 수 없습니다.                 ");
            System.out.println("╚═════════════════════════════════════════════════╝");
        }
    }

}


