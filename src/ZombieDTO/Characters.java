package ZombieDTO;

import static Util.PrintUtil.gameOver;

public class Characters {
    private int charId;         // CHARID   : NUMBER PRIMARY KEY, 캐릭터 ID (고유 ID PK)
    private String charName;    // CHARNAME : NVARCHAR2(50) NOT NULL, 캐릭터 이름
    private int HP;             // HP		: NUMBER NOT NULL, 체력/피로도
    private int invenId;        // INVENID 	: NUMBER, FOREIGN KEY (INVENID) REFERENCES INVENTORY(INVENID) 인벤토리 ID (외래키),
    private String pId;         // PID		: NVARCHAR2(20), FOREIGN KEY (PID) REFERENCES PLAYER(PID)

    // 기본 생성자
    public Characters() {
    }

    // 매개변수 포함 생성자
    public Characters(int charId, String pId, String charName, int HP, int invenId) {
        this.charId = charId;
        this.pId = pId;
        this.charName = charName;
        this.HP = HP;
        this.invenId = invenId;
    }


    // 피로도(HP) 증가 메소드
    public void increaseHP(int amount) {
        this.HP += amount;
        if (this.HP >= 10) {
            System.out.println("피로도(HP)가 10에 도달하여 게임이 종료됩니다.");
            gameOver();  // 피로도가 10 이상이면 게임 종료
        } else {
            System.out.println("현재 피로도(HP): " + this.HP);
        }
    }

    // 게임 오버 처리 메소드
    private void gameOver() {
        System.out.println("게임 오버! 더 이상 진행할 수 없습니다.");
        // 게임 종료 처리 추가
        System.exit(0);
    }

    public int getCharId() {
        return charId;
    }

    public void setCharId(int charId) {
        this.charId = charId;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getInvenId() {
        return invenId;
    }

    public void setInvenId(int invenId) {
        this.invenId = invenId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "Characters{" +
                "charId=" + charId +
                ", charName='" + charName + '\'' +
                ", HP=" + HP +
                ", invenId=" + invenId +
                ", pId='" + pId + '\'' +
                '}';
    }
}
