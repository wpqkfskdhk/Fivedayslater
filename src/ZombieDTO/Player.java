package ZombieDTO;

public class Player {
    private int cNum;
    private int pNum;   // PNUM : NUMBER PRIMARY KEY, 고유번호(PK)로 넣었지만 삭제가능
    private String pId; // PID   : NVARCHAR2(20) UNIQUE, 플레이어 아이디, 고유번호 없으면 PK
    private String pPw; // PPW	 : NVARCHAR2(20), 플레이어 비번

    // 파라미터를 받는 생성자
    public Player(int pNum, String pId, String pPw) {
        this.pNum = pNum;
        this.pId = pId;
        this.pPw = pPw;
    }

    public int getcNum() {
        return cNum;
    }

    public void setcNum(int cNum) {
        this.cNum = cNum;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpPw() {
        return pPw;
    }

    public void setpPw(String pPw) {
        this.pPw = pPw;
    }

    @Override
    public String toString() {
        return "Player{" +
                "pIdNo=" + pNum +
                ", pId='" + pId + '\'' +
                ", pPw='" + pPw + '\'' +
                '}';
    }

}
