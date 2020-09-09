package piece;

public class CavalrySoldier extends Piece {
	public CavalrySoldier(int no) {
		super(no);
		fullName = "CavalrySoldier";
		name = "C" + no;
		moveDistance = 3;
		moveDirection = 8;
		attackRange = 1;
		attackDirection = 8;
		power = 6;
		hp = 15;
	}

	@Override
	public void noticeMoveRange() {
		super.noticeMoveRange();
		System.out.println("      1    2    3    4    5    6    7");
		System.out.println("         天天天天天天天天天天天天天天天天天天天天天天天");
		for (int i = 0; i < 7; i++) {
			System.out.printf("%2d  |", i + 1);
			for (int j = 0; j < 7; j++) {
				if (i == 3 && j == 3) {
					System.out.print("unit|");
					continue;
				} else
					System.out.print(" o  |");
			}
			System.out.println();
			System.out.println("         天天天天天天天天天天天天天天天天天天天天天天天");
		}
	}

	@Override
	public void noticeAttackRange() {
		super.noticeAttackRange();
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
	public boolean moveCheck(int x, int y) {
		if (0 <= Math.abs(this.x - x) && Math.abs(this.x - x) <= 3 && 0 <= Math.abs(this.y - y)
				&& Math.abs(this.y - y) <= 3) {
			this.x = x;
			this.y = y;
			return true;
		}
		return false;
	}

	@Override
	public boolean attackCheck(int x, int y) {
		if (0 <= Math.abs(this.x - x) && Math.abs(this.x - x) <= 1 && 0 <= Math.abs(this.y - y)
				&& Math.abs(this.y - y) <= 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean detectEnemy(Piece[][] map, int x, int y) {
		boolean detected = false;
		for (int i = x - attackRange; i <= x + attackRange; i++) {
			for (int j = y - attackRange; j <= y + attackRange; j++) {
				if (i == x && j == y)
					continue;
				try {
					if (map[j][i] != null && map[j][i].playerNo != map[y][x].playerNo)
						detected = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
		return detected;
	}
}
