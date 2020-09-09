package piece;

public class Piece {
	public int playerNo;
	protected String name;
	protected String fullName;
	protected int moveDistance;
	protected int moveDirection;
	protected int attackRange;
	protected int attackDirection;
	protected int power;
	protected int hp;
	private int beforeHp;
	protected int x, y;

	Piece() {
	}

	Piece(int no) {
		playerNo = no;
	}

	public void randCoordinates(int xBoundary) {
		x = (int) (Math.random() * 4 + xBoundary);
		y = (int) (Math.random() * 11);
	}

	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getPower() {
		return power;
	}

	public int getHp() {
		return hp;
	}

	public int getBeforeHp() {
		return beforeHp;
	}

	public boolean checkPlayer(int playerNo) {
		if (this.playerNo == playerNo)
			return true;
		return false;
	}

	public void noticeMoveRange() {
		System.out.printf("=>The range of movement of %s.\n", fullName);
	}

	public void noticeAttackRange() {
		System.out.printf("=>The range of attack of %s.\n", fullName);
	}

	public boolean moveCheck(int x, int y) {
		return false;
	}

	public boolean attackCheck(int x, int y) {
		return false;
	}

	public boolean detectEnemy(Piece[][] map, int x, int y) {
		return false;
	}

	public boolean beDamaged(int damage) {
		beforeHp = hp;
		hp -= damage;
		if (hp <= 0)
			return true;
		return false;
	}

	public String toString() {
		return name;
	}
}
