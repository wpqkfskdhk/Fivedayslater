package Util;

import ZombieDAO.ZombieSQL;
import ZombieDTO.Characters;
import ZombieDTO.Player;

import java.util.List;
import java.util.Scanner;

import static Util.ColorUtil.*;
import static Util.StoryUtil.slowPrint;

public class PlayerUtil {
    private ZombieSQL sql = new ZombieSQL();
    private Scanner sc = new Scanner(System.in);
    private String loggedInUserId;

    // 플레이어 ID로 캐릭터 목록을 가져오는 메서드
    public List<Characters> getCharacterList(String playerId) {
        return sql.characterList(playerId);  // SQL 클래스의 메서드 호출하여 캐릭터 목록 반환
    }

    // 회원가입
    public void join() {
        System.out.print("아이디 입력: ");
        String pId = sc.nextLine();
        if (sql.checkId(pId)) {
            System.out.println("이미 존재하는 아이디입니다.");
            return;
        }
        System.out.print("비밀번호 입력(8자리 이상): ");
        String pPw = sc.nextLine();
        if (pPw.length() < 8) {
            System.out.println("비밀번호는 8자리 이상이어야 합니다.");
            return;
        }
        Player player = new Player(sql.playerNumber(), pId, pPw);
        sql.createPlayer(player);
        System.out.println("로그인 후 이용해주세요.");
    }

    // 로그인
    public String login() {
        System.out.print("아이디 입력: ");
        String pId = sc.nextLine();
        System.out.print("비밀번호 입력: ");
        String pPw = sc.nextLine();

        boolean success = sql.login(pId, pPw);
        loginImpact(success);  // 로그인 시각화 메소드에 성공 여부 전달

        if (success) {
            loggedInUserId = pId;
            PrintUtil.helloArt();
            return pId; // 로그인 성공 시, 로그인한 사용자의 ID 반환
        } else {
            return null; // 로그인 실패 시, null 반환
        }
    }


    // 로그인 시각화 메소드
    public void loginImpact(boolean success) {
        String[] loadingSymbols = {"|", "/", "-", "\\"};  // 로딩 애니메이션 효과
        String loginIn = "로그인 중 입니다";
        int loadingCount = 10;  // 로딩 애니메이션 반복 횟수

        for (int i = 0; i < loadingCount; i++) {
            System.out.print("\r" + loginIn + " " + loadingSymbols[i % loadingSymbols.length]); // 애니메이션 효과
            try {
                Thread.sleep(300);  // 0.3초마다 로딩 상태 갱신
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // 로딩 완료 후 성공/실패 메시지
        if (success) {
            slowPrint("\n★ 로그인 성공! ★\n", 30, GREEN);  // 로그인 성공 메시지
        } else {
            slowPrint("\n✖ 로그인 실패... ✖\n", 30, RED);   // 로그인 실패 메시지
        }

        for (int i = 0; i < 5; ++i) System.out.println();  // 화면 정리
    }


    public void createCharacter() {
        System.out.print("캐릭터 이름을 입력하세요>> ");
        String charName = sc.nextLine();

        int charId = sql.characterNumber();  // 새로운 캐릭터 ID 생성
        int initialHp = 4;  // 고정된 초기 체력 값

        Characters newCharacter = new Characters();
        newCharacter.setCharId(charId);
        newCharacter.setpId(loggedInUserId);  // 로그인한 플레이어 ID 설정
        newCharacter.setCharName(charName);
        newCharacter.setHP(initialHp);  // 초기 체력 설정

        sql.createCharacter(newCharacter);  // SQL 클래스의 createCharacter 메소드 호출
    }


    public void printCharacterList() {
        List<Characters> characterList = sql.characterList(loggedInUserId);
        if (characterList.isEmpty()) {
            System.out.println("생성된 캐릭터가 없습니다.");
        } else {
            System.out.println("< 캐릭터 목록 >");
            for (int i = 0; i < characterList.size(); i++) {
                Characters character = characterList.get(i);
                System.out.println((i + 1) + ". 이름: " + character.getCharName() + " | 체력: " + character.getHP());
            }
        }
    }

    //  사용 안할시 삭제
//
//    // 캐릭터 선택 및 관리
//    public void manageCharacter(String playerId) {
//        System.out.println(" 로그인한 사용자 ID: " + playerId);
//        viewCharacterInfo(); // 캐릭터 정보 조회
//        loadCharacterGame(); // 게임 로드
//        deleteCharacter();   // 캐릭터 삭제
//    }
//
//    // 캐릭터 정보 조회
//    private void viewCharacterInfo() {
//        System.out.println("캐릭터 정보 조회 중...");
//    }
//
//    // 캐릭터 접속
//    private void loadCharacterGame() {
//        System.out.println("저장된 게임 로드 중...");
//    }
//
//    // 캐릭터 삭제
//    private void deleteCharacter() {
//        System.out.println("캐릭터 삭제 중...");
//    }


    public int findIdForCharacter(String charName) {
        return sql.findCharacterIdByName(charName);
    }

    // 수정중
    /*
    public int findCharacterIdByName(String charName) {
        List<String> characterList = sql.characterList(loggedInUserId);  // 캐릭터 목록을 문자열 리스트로 가져옴

        for (String character : characterList) {
            // 예시 : "이름: chae| 체력: 4" 형식에서 이름 부분만 추출
            String[] parts = character.split("\\|");  // "이름: chae "과 " 체력: 4"을 분리
            if (parts.length > 0) {
                String namePart = parts[0].trim();  // "이름: chae" 부분
                String[] nameKeyValue = namePart.split(":"); // "이름"과 "chae" 분리
                if (nameKeyValue.length > 1) {
                    String name = nameKeyValue[1].trim();  // 실제 캐릭터 이름 추출
                    if (name.equalsIgnoreCase(charName)) {
                        // 해당 캐릭터의 ID가 필요하므로, 여기서는 추가적으로 ID를 찾아야 합니다.
                        // 가정: 캐릭터 정보를 저장할 때 ID를 포함한 다른 형식의 문자열로 반환받아야 함
                        return findIdForCharacter(name);  // 캐릭터 ID를 찾는 메소드를 호출
                    }
                }
            }
        }

        return -1;  // 일치하는 캐릭터 이름이 없는 경우
    }
*/

    public int findCharacterIdByName(String charName) {
        // List<Characters>를 사용하여 캐릭터 객체를 가져옴
        List<Characters> characterList = sql.characterList(loggedInUserId);

        for (Characters character : characterList) {
            // Characters 객체에서 이름을 가져와 비교
            String name = character.getCharName();
            if (name.equalsIgnoreCase(charName)) {
                // 이름이 일치하면 캐릭터의 ID를 반환
                return character.getCharId();
            }
        }

        // 일치하는 캐릭터 이름이 없는 경우 -1 반환
        return -1;
    }


}
