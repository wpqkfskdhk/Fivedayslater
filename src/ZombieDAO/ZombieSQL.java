package ZombieDAO;

import ZombieDTO.Characters;
import ZombieDTO.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZombieSQL {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // DB 연결
    public void connect() {
        con = DBC.DBConnect(); // 데이터베이스 연결 설정
    }

    // DB 연결 해제
    public void conClose() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("DB 연결 해제 중 오류 발생: " + e.getMessage());
        }
    }

    // 플레이어 고유번호 생성
    public int playerNumber() {
        int pNum = 0;
        String sql = "SELECT MAX(PNUM) FROM PLAYER";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("고객 번호 생성 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return pNum + 1;
    }

    // 회원가입
    public void createPlayer(Player player) {
        String sql = "INSERT INTO PLAYER (PNUM, PID, PPW) VALUES (?, ?, ?)";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, player.getpNum());
            pstmt.setString(2, player.getpId());
            pstmt.setString(3, player.getpPw());
            int result = pstmt.executeUpdate();
            System.out.println(result > 0 ? "회원가입 완료되었습니다." : "회원가입 실패하였습니다.");
        } catch (SQLException e) {
            System.out.println("회원가입 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
    }

    // ID 중복 확인
    public boolean checkId(String pId) {
        boolean exists = false;
        String sql = "SELECT PID FROM PLAYER WHERE PID = ?";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pId);
            rs = pstmt.executeQuery();
            exists = rs.next();
        } catch (SQLException e) {
            System.out.println("ID 중복 확인 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return exists;
    }

    // 플레이어 로그인
    public boolean login(String pId, String pPw) {
        boolean result = false;
        String sql = "SELECT * FROM PLAYER WHERE PID = ? AND PPW = ?";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pId);
            pstmt.setString(2, pPw);
            rs = pstmt.executeQuery();
            result = rs.next();
        } catch (SQLException e) {
            System.out.println("로그인 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return result;
    }

    // 캐릭터 고유번호 생성
    public int characterNumber() {
        int charId = 0;
        String sql = "SELECT MAX(CHARID) FROM CHARACTERS";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                charId = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("캐릭터 ID 생성 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return charId + 1;
    }

    // 캐릭터 생성 및 DB 저장
    public void createCharacter(Characters character) {
        connect(); // DB 연결
        try {
            String sql = "INSERT INTO CHARACTERS (CHARID, PID, CHARNAME) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, character.getCharId());
            pstmt.setString(2, character.getpId());
            pstmt.setString(3, character.getCharName());

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("캐릭터 생성 완료.");
            } else {
                System.out.println("캐릭터 생성 실패.");
            }
        } catch (SQLException e) {
            System.out.println("캐릭터 생성 중 오류 발생: " + e.getMessage());
        } finally {
            conClose(); // DB 연결 해제
        }
    }


    /*

    // 캐릭터 목록 조회
    public List<String> characterList(String pId) {
        List<String> characterList = new ArrayList<>();
        String sql = "SELECT CHARNAME, HP FROM CHARACTERS WHERE PID = ?";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String charName = rs.getString("CHARNAME");
                int hp = rs.getInt("HP");
                characterList.add("이름: " + charName + " | 체력: " + hp);
            }
        } catch (SQLException e) {
            System.out.println("캐릭터 조회 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return characterList;
    }
*/

    // 캐릭터 목록 조회 수정중
    public List<Characters> characterList(String pId) {
        List<Characters> characterList = new ArrayList<>();
        String sql = "SELECT CHARID, CHARNAME, HP FROM CHARACTERS WHERE PID = ?";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Characters character = new Characters();
                character.setCharId(rs.getInt("CHARID"));
                character.setCharName(rs.getString("CHARNAME"));
                character.setHP(rs.getInt("HP"));
                characterList.add(character);
            }
        } catch (SQLException e) {
            System.out.println("캐릭터 목록 조회 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return characterList;
    }



    public void updateCharacterHP(Characters character) {
        String sql = "UPDATE CHARACTERS SET HP = ? WHERE CHARID = ?";
        try {
            connect();

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, character.getHP());       // 현재 캐릭터의 HP 값
            pstmt.setInt(2, character.getCharId());   // 캐릭터 ID로 업데이트
            pstmt.executeUpdate();                    // DB 업데이트 실행

        } catch (SQLException e) {
            System.out.println("체력 업데이트 중 오류 발생: " + e.getMessage());

        } finally {
            conClose();
        }

    }


    // 게임 저장
    public void saveGame(String pId, int charId, String saveData) {

        String sql = "MERGE INTO GAME_SAVE g " +
                "USING (SELECT ? AS PID, ? AS CHARID FROM DUAL) s " +
                "ON (g.PID = s.PID AND g.CHARID = s.CHARID) " +
                "WHEN MATCHED THEN UPDATE SET g.SAVE_DATA = ?, g.SAVE_DATE = CURRENT_TIMESTAMP " +
                "WHEN NOT MATCHED THEN INSERT (SAVE_ID, PID, CHARID, SAVE_DATA, SAVE_DATE) VALUES (GAME_SAVE_SEQ.NEXTVAL, ?, ?, ?, CURRENT_TIMESTAMP)";
        try {
            connect();

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pId);
            pstmt.setInt(2, charId);
            pstmt.setString(3, saveData);
            pstmt.setString(4, pId);
            pstmt.setInt(5, charId);
            pstmt.setString(6, saveData);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("게임 저장 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }

    }

    // 게임 불러오기
    public String loadGame(String pId, int charId) {
        String saveData = null;
        String sql = "SELECT SAVE_DATA FROM GAME_SAVE WHERE PID = ? AND CHARID = ?";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pId);
            pstmt.setInt(2, charId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Clob clobData = rs.getClob("SAVE_DATA");
                saveData = clobData.getSubString(1, (int) clobData.length());
            }
        } catch (SQLException e) {
            System.out.println("게임 불러오기 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return saveData;
    }


    public int findCharacterIdByName(String charName) {
        int charId = -1;
        String sql = "SELECT CHARID FROM CHARACTERS WHERE CHARNAME = ?";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, charName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                charId = rs.getInt("CHARID");
            }
        } catch (SQLException e) {
            System.out.println("캐릭터 ID 검색 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return charId;
    }


    // 캐릭터 정보 불러오기 (HP 포함해서 수정중 디비 제발 넣어지라고오오오ㅗㅇ옥)
    public Characters getCharacterById(int charId) {
        Characters character = null;
        String sql = "SELECT CHARID, CHARNAME, HP FROM CHARACTERS WHERE CHARID = ?";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, charId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                character = new Characters();
                character.setCharId(rs.getInt("CHARID"));
                character.setCharName(rs.getString("CHARNAME"));
                character.setHP(rs.getInt("HP"));    // 체력 정보도 DB에서 가져옴
            }
        } catch (SQLException e) {
            System.out.println("캐릭터 정보 조회 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return character;
    }


    // 캐릭터 삭제 메소드 수정중
    public boolean deleteCharacter(int charId) {
        boolean result = false;

        String deleteInventoryItemsSql = "DELETE FROM INVENTORY_ITEMS WHERE INVENID = (SELECT INVENID FROM INVENTORY WHERE CHARID = ?)";
        String deleteInventorySql = "DELETE FROM INVENTORY WHERE CHARID = ?";
        String deleteSaveSql = "DELETE FROM GAME_SAVE WHERE CHARID = ?";
        String deleteCharacterSql = "DELETE FROM CHARACTERS WHERE CHARID = ?";

        try {
            connect();

            //  INVENTORY_ITEMS에서 데이터 삭제
            pstmt = con.prepareStatement(deleteInventoryItemsSql);
            pstmt.setInt(1, charId);
            pstmt.executeUpdate();

            // 인벤토리 삭제
            pstmt = con.prepareStatement(deleteInventorySql);
            pstmt.setInt(1, charId);
            pstmt.executeUpdate();

            // 게임 저장 데이터 삭제
            pstmt = con.prepareStatement(deleteSaveSql);
            pstmt.setInt(1, charId);
            pstmt.executeUpdate();

            // 캐릭터 삭제
            pstmt = con.prepareStatement(deleteCharacterSql);
            pstmt.setInt(1, charId);
            int rowsAffected = pstmt.executeUpdate();
            result = rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("캐릭터 삭제 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return result;
    }



    // 플레이어의 캐릭터 수를 반환하는 메소드
    public int getCharacterCountForPlayer(String pId) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM CHARACTERS WHERE PID = ?";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("캐릭터 수 조회 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return count;
    }

    /*
        // 아이템 획득, 인벤토리에 넣는 메소드
        public void addItemToInventory(int itemId, int charId) {
            try {
                connect();

                //con.setAutoCommit(false);
                con.setAutoCommit(true);

                String sql = "INSERT INTO INVENTORY VALUES (INVENTORY_SEQ.NEXTVAL, ?, ?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, charId);
                pstmt.setInt(2, itemId);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

     */

    // 아이템 획득 -> 인벤토리 추가
    public void addItemToInventory(int itemId, int charId) {
        int inventoryId = getInventoryIdForCharacter(charId); // 캐릭터의 인벤토리 ID를 가져오기
        if (inventoryId == -1) {                              // 인벤토리 ID가 없다면, 새로 생성
            inventoryId = createInventory(charId);            // 새 인벤토리 생성
        }

        try {
            connect();
            String sql = "INSERT INTO INVENTORY_ITEMS (INVENID, ITEMID, CHARID) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, inventoryId);  // 기존의 INVENID를 사용
            pstmt.setInt(2, itemId);        // 새 ITEMID 추가
            pstmt.setInt(3, charId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("아이템을 인벤토리에 추가하는 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
    }


    /*
    // 아이템 소비, 인벤토리에 삭제하는 메소드 (원본)
    public void useItemInInventory(int itemId, int charId) {
        try {
            connect();
            String sql = "DELETE FROM INVENTORY WHERE ITEMID = ? AND CHARID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, itemId);
            pstmt.setInt(2, charId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    */

    // 아이템 소비, 인벤토리에 삭제하는 메소드 수정중
    public void useItemInInventory(int itemId, int charId) {
        try {
            connect();
            // INVENTORY_ITEMS 테이블에서 ITEMID와 CHARID로 삭제했어용용
            String sql = "DELETE FROM INVENTORY_ITEMS WHERE ITEMID = ? AND INVENID = (SELECT INVENID FROM INVENTORY WHERE CHARID = ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, itemId);
            pstmt.setInt(2, charId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conClose();
        }
    }


    // 인벤토리 리스트 불러오기 메소드 (수정중)
    public List<String> viewInventoryList(int charId) {
        connect();
        List<String> inventoryList = new ArrayList<>();

        try {
            String sql = "SELECT ITEMS.INAME, ITEMS.ITYPE, ITEMS.IEFFECT " +
                    "FROM CHARACTERS " +
                    "JOIN INVENTORY ON INVENTORY.CHARID = CHARACTERS.CHARID " +
                    "JOIN INVENTORY_ITEMS ON INVENTORY.INVENID = INVENTORY_ITEMS.INVENID " +
                    "JOIN ITEMS ON INVENTORY_ITEMS.ITEMID = ITEMS.ITEMID " +
                    "WHERE CHARACTERS.CHARID = ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, charId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String iName = rs.getString("INAME");
                String iType = rs.getString("ITYPE");
                String iEffect = rs.getString("IEFFECT");
                inventoryList.add("[" + iName + " | " + iType + " | " + iEffect + "]");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conClose();
        }

        return inventoryList;
    }

    // 인벤토리 초기화(수정중)
    public void resetInventory(int charId) {
        try {
            connect();
            String sql = "DELETE FROM INVENTORY_ITEMS WHERE INVENID = (SELECT INVENID FROM INVENTORY WHERE CHARID = ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, charId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("저장된 인벤토리가 존재하지 않습니다.");
        } finally {
            conClose();
        }
    }


/*
    // 인벤토리 초기화 (원본)
    public void resetInventory(int charId) {
        try {
            connect();
            String sql = "DELETE FROM INVENTORY WHERE CHARID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, charId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("저장된 인벤토리가 존재하지 않습니다.");
        }
    }

 */

    /*
    // SQL 클래스에 추가할 인벤토리 삭제 메서드 (원본)
    public void deleteInventory(int charId) {
        String sql = "DELETE FROM INVENTORY WHERE CHARID = ?";
        try {
            connect();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, charId);
            pstmt.executeUpdate();
            System.out.println("인벤토리가 삭제되었습니다.");
        } catch (SQLException e) {
            System.out.println("인벤토리 삭제 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
    }
     */


    public void deleteInventory(int charId) {
        try {
            connect();
            // 1. INVENTORY_ITEMS에서 해당 캐릭터의 아이템 삭제
            String deleteItemsSql = "DELETE FROM INVENTORY_ITEMS WHERE INVENID = (SELECT INVENID FROM INVENTORY WHERE CHARID = ?)";
            pstmt = con.prepareStatement(deleteItemsSql);
            pstmt.setInt(1, charId);
            pstmt.executeUpdate();

            // 2. INVENTORY 테이블에서 해당 캐릭터의 인벤토리 삭제
            String deleteInventorySql = "DELETE FROM INVENTORY WHERE CHARID = ?";
            pstmt = con.prepareStatement(deleteInventorySql);
            pstmt.setInt(1, charId);
            pstmt.executeUpdate();

            System.out.println("인벤토리가 삭제되었습니다.");
        } catch (SQLException e) {
            System.out.println("인벤토리 삭제 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
    }


    // 캐릭터가 이미 가지고 있는 인벤토리 ID를 조회하는 메서드
    public int getInventoryIdForCharacter(int charId) {
        String sql = "SELECT INVENID FROM INVENTORY WHERE CHARID = ?";
        int invenId = -1;
        try {
            connect();  // 연결이 끊어진 경우 재연결 시도
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, charId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                invenId = rs.getInt("INVENID");  // 인벤토리 ID 반환
            }
        } catch (SQLException e) {
            System.out.println("인벤토리 ID 조회 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();  // 연결 해제
        }
        return invenId;  // 인벤토리 ID 반환 또는 -1
    }


    // 인벤토리 생성 수정중
    public int createInventory(int charId) {
        int inventoryId = getInventoryIdForCharacter(charId); // 기존 인벤토리 ID를 가져옴
        if (inventoryId != -1) {
            return inventoryId; // 이미 인벤토리가 존재하면 해당 인벤토리 ID 반환
        }

        try {
            String sql = "INSERT INTO INVENTORY (INVENID, CHARID) VALUES (INVENTORY_SEQ.NEXTVAL, ?)";
            connect();  // 데이터베이스 연결
            pstmt = con.prepareStatement(sql, new String[]{"INVENID"});
            pstmt.setInt(1, charId);  // 캐릭터 ID 설정
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                inventoryId = rs.getInt(1);  // 새로 생성된 INVENID 값 가져오기
            }
        } catch (SQLException e) {
            System.out.println("인벤토리 생성 중 오류 발생: " + e.getMessage());
        } finally {
            conClose();
        }
        return inventoryId;
    }
    // 회복 아이템 유무 확인
    public boolean checkItem(int charId) {
        boolean hasItem = false;
        connect();
        try {
            String sql = "SELECT ITYPE FROM INVENTORY_ITEMS " +
                    "JOIN ITEMS ON INVENTORY_ITEMS.ITEMID = ITEMS.ITEMID " +
                    "WHERE ITYPE = '회복' AND CHARID = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, charId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                hasItem = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hasItem;
    }
    public void deletePlayer(String pId) {

        try {

            connect();

            // 게임 저장 데이터 삭제
            String deleteGameSaveSql = "DELETE FROM GAME_SAVE WHERE PID = ?";
            pstmt = con.prepareStatement(deleteGameSaveSql);
            pstmt.setString(1, pId);
            pstmt.executeUpdate();

            // 인벤토리 아이템 데이터 삭제
            String deleteInventoryItemsSql = "DELETE FROM INVENTORY_ITEMS WHERE CHARID IN (SELECT CHARID FROM CHARACTERS WHERE PID = ?)";
            pstmt = con.prepareStatement(deleteInventoryItemsSql);
            pstmt.setString(1, pId);
            pstmt.executeUpdate();

            // 인벤토리 데이터 삭제
            String deleteInventorySql = "DELETE FROM INVENTORY WHERE CHARID IN (SELECT CHARID FROM CHARACTERS WHERE PID = ?)";
            pstmt = con.prepareStatement(deleteInventorySql);
            pstmt.setString(1, pId);
            pstmt.executeUpdate();

            // 캐릭터 삭제
            String deleteCharactersSql = "DELETE FROM CHARACTERS WHERE PID = ?";
            pstmt = con.prepareStatement(deleteCharactersSql);
            pstmt.setString(1, pId);
            pstmt.executeUpdate();

            // 플레이어 삭제 (드디어!)
            String deletePlayerSql = "DELETE FROM PLAYER WHERE PID = ?";
            pstmt = con.prepareStatement(deletePlayerSql);
            pstmt.setString(1, pId);
            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("계정이 삭제되었습니다.");
            }

        } catch (SQLException e) {
            System.out.println("계정 삭제 중 오류 발생 : " + e.getMessage());;
        }
    }


}

