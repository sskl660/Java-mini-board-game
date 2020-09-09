package piece;

public class Archer extends Piece {
	public Archer(int no) {
		super(no);
		fullName = "Archer";
		name = "A" + no;
		moveDistance = 2;
		moveDirection = 4;
		attackRange = 4;
		attackDirection = 4;
		power = 3;
		hp = 12;
	}

	@Override
	public void noticeMoveRange() {
		super.noticeMoveRange();
		System.out.println("      1    2    3    4    5");
		System.out.println("         �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
		for (int i = 0; i < 5; i++) {
			System.out.printf("%2d  |", i + 1);
			for (int j = 0; j < 5; j++) {
				if (i == 2 && j == 2) {
					System.out.print("unit|");
					continue;
				}
				if (i == 2) {
					System.out.print(" o  |");
					continue;
				}
				if (j == 2)
					System.out.print(" o  |");
				else
					System.out.print("    |");
			}
			System.out.println();
			System.out.println("         �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
		}
	}

	@Override
	public void noticeAttackRange() {
		super.noticeAttackRange();
		System.out.println("      1    2    3    4    5    6    7    8    9");
		System.out.println("         �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
		for (int i = 0; i < 9; i++) {
			System.out.printf("%2d  |", i + 1);
			for (int j = 0; j < 9; j++) {
				if (i == 4 && j == 4) {
					System.out.print("unit|");
					continue;
				}
				if (i == 4) {
					System.out.print(" o  |");
					continue;
				}
				if (j == 4)
					System.out.print(" o  |");
				else
					System.out.print("    |");
			}
			System.out.println();
			System.out.println("         �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�");
		}
	}

	@Override
	public boolean moveCheck(int x, int y) {
		if (0 <= Math.abs(this.x - x) && Math.abs(this.x - x) <= 2 && this.y == y) {
			this.x = x;
			this.y = y;
			return true;
		}
		if (0 <= Math.abs(this.y - y) && Math.abs(this.y - y) <= 2 && this.x == x) {
			this.x = x;
			this.y = y;
			return true;
		}
		return false;
	}

	@Override
	public boolean attackCheck(int x, int y) {
		if (0 <= Math.abs(this.x - x) && Math.abs(this.x - x) <= 4 && this.y == y) {
			return true;
		}
		if (0 <= Math.abs(this.y - y) && Math.abs(this.y - y) <= 4 && this.x == x) {
			return true;
		}
		return false;
	}

	@Override
	public boolean detectEnemy(Piece[][] map, int x, int y) {

		boolean detected = false;

		for (int i = y - attackRange; i <= y + attackRange; i++) {
			if (i == y)
				continue;
			try {
				if (map[i][x] != null && map[i][x].playerNo != map[y][x].playerNo) {
					detected = true;
					return detected;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}

		}

		for (int i = x - attackRange; i <= x + attackRange; i++) {
			if (i == x)
				continue;
			try {
				if (map[y][i] != null && map[y][i].playerNo != map[y][x].playerNo) {
					detected = true;
					return detected;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}

		return detected;

	}
}
