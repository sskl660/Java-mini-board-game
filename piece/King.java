package piece;

public class King extends Piece {
	public King(int no) {
		super(no);
		fullName = "King";
		name = "K" + no;
		moveDistance = 1;
		moveDirection = 8;
		attackRange = 0;
		attackDirection = 0;
		power = 0;
		hp = 15;
	}

	@Override
	public void noticeMoveRange() {
		super.noticeMoveRange();
		System.out.println("      1    2    3");
		System.out.println("         天天天天天天天天天天");
		for (int i = 0; i < 3; i++) {
			System.out.printf("%2d  |", i + 1);
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					System.out.print("unit|");
					continue;
				} else
					System.out.print(" o  |");
			}
			System.out.println();
			System.out.println("         天天天天天天天天天天");
		}
	}

	@Override
	public void noticeAttackRange() {
		super.noticeAttackRange();
		System.out.println("This unit cannot attack.");
	}

	@Override
	public boolean moveCheck(int x, int y) {
		if (0 <= Math.abs(this.x - x) && Math.abs(this.x - x) <= 1 && 0 <= Math.abs(this.y - y)
				&& Math.abs(this.y - y) <= 1) {
			this.x = x;
			this.y = y;
			return true;
		}
		return false;
	}

	@Override
	public boolean attackCheck(int x, int y) {
		System.out.println("This unit cannot attack.");
		return false;
	}
}
