package Util;

import ZombieDAO.ZombieSQL;

public class SaveUtil {
    private ZombieSQL sql = new ZombieSQL();

    // 게임 저장
    public void saveGame(String pId, int charId, String saveData) {
        try {
            sql.connect();  // DB 연결
            sql.saveGame(pId, charId, saveData);  // 게임 저장 SQL 실행
            System.out.println("게임이 저장되었습니다.");
        } finally {
            sql.conClose();  // DB 연결 닫기
        }
    }


    // 사용 안하면 지워주기
    // 게임 불러오기
    public String loadGame(String pId, int charId) {
        String saveData = null;
        try {
            sql.connect();  // DB 연결

            saveData = sql.loadGame(pId, charId);  // 게임 불러오기 SQL 실행
            if (saveData != null) {
                System.out.println("게임이 불러와졌습니다.");
            } else {
//                System.out.println("저장된 게임이 없습니다.");

            }
        } finally {
            sql.conClose();  // DB 연결 닫기
        }
        return saveData;
    }

    // 게임 선택지에서 저장 기능 추가
    public void saveDuringGame(String pId, int charId, String gameData) {
        this.saveGame(pId, charId, gameData);
    }

}
