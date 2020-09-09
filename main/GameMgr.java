// 해당 프로그램은 좌표값이 어느정도 오차가 존재하는 오류,
// 같은 팀의 유닛도 공격할 수 있는 오류가 발견 되었습니다.
package main;

import java.util.Scanner;

import nation.Japan;
import nation.Korea;
import piece.King;
import piece.Piece;

public class GameMgr {
	Scanner scan = new Scanner(System.in);
	Piece[][] map = new Piece[11][11];
	Player player1, player2;

	void doit() {
		System.out.println("==========Simple War Game==========");
		System.out.println("========Made by 오승수, 김태현========");
		System.out.print("Select Player1's Nation\n1. Korea\n2. Japan\n=> ");
		player1 = selectNation();
		arrangePieces(player1);
		System.out.print("Select Player2's Nation\n1. Korea\n2. Japan\n=> ");
		player2 = selectNation();
		arrangePieces(player2);
		printMap();
		while (true) {
			chooseAction(player1);
			if (checkWinner(player1, player2))
				return;
			chooseAction(player2);
			if (checkWinner(player1, player2))
				return;
		}
	}

	Player selectNation() {
		Player p;
		while (true) {
			int input = scan.nextInt();
			switch (input) {
			case 1:
				p = new Player(new Korea());
				System.out.println(p + "'s Nation is Korea\n");
				return p;
			case 2:
				p = new Player(new Japan());
				System.out.println(p + "'s Nation is Japan\n");
				return p;
			default:
				System.out.println("You entered it incorrectly. Please re-enter it.");
				continue;
			}
		}
	}

	void arrangePieces(Player p) {
		map = p.arrangePieces(map);
	}

	void printMap() {
		System.out.println("      1    2    3    4    5    6    7    8    9    10   11");
		System.out.println("        ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		for (int i = 0; i < map.length; i++) {
			System.out.printf("%2d  |", i + 1);
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == null)
					System.out.print("    |");
				else
					System.out.print(" " + map[i][j] + " |");
			}
			System.out.println();
			System.out.println("        ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		}
	}

	void chooseAction(Player p) {
		int x, y;
		while (true) {
			System.out.println(p + "'s turn");
			System.out.print("1. Move\n2. Attack\n3. Change Turn\n=> ");
			int input = scan.nextInt();
			System.out.println();
			switch (input) {
			case 1:
				System.out.println("Select Unit's Coordinates (x, y)");
				x = scan.nextInt() - 1;
				y = scan.nextInt() - 1;
				System.out.println();
				if (map[y][x] == null) {
					System.out.println("Unit does not exist at that point.\n");
					continue;
				}
				if (!map[y][x].checkPlayer(p.playerNo)) {
					System.out.println("It's not " + p + "'s Unit.\n");
					continue;
				}
				System.out.printf("You choose %s in (%d, %d)\n", map[y][x], x + 1, y + 1);
				while (true) {
					System.out.println("Input Move Direction(new x, new y)");
					map[y][x].noticeMoveRange();
					System.out.print("=>");
					int newX = scan.nextInt() - 1;
					int newY = scan.nextInt() - 1;
					System.out.println();
					if (!map[y][x].moveCheck(newX, newY)) {
						System.out.println("Please check moveRange of this unit.\n");
						continue;
					}
					if (map[newY][newX] != null) {
						System.out.println("Other Unit already exist at that point.\n");
						continue;
					}
					map[newY][newX] = map[y][x];
					map[y][x] = null;
					printMap();
					return;
				}
			case 2:
				System.out.println("Select Unit's Coordinates (x, y)");
				x = scan.nextInt() - 1;
				y = scan.nextInt() - 1;
				System.out.println();
				if (map[y][x] == null) {
					System.out.println("Unit does not exist at that point.\n");
					continue;
				}
				if (!map[y][x].checkPlayer(p.playerNo)) {
					System.out.println("It's not " + p + "'s Unit.\n");
					continue;
				}
				System.out.printf("You choose %s in (%d, %d)\n", map[y][x], x + 1, y + 1);
				if (!map[y][x].detectEnemy(map, x, y)) {
					System.out.println("The enemy not detected from attack range of this unit.");
					System.out.println("You have to move first, or you have to hand over the turn.\n");
					continue;
				}
				while (true) {
					System.out.println("Please select a target. (x, y)");
					map[y][x].noticeAttackRange();
					System.out.print("=>");
					int attackX = scan.nextInt() - 1;
					int attackY = scan.nextInt() - 1;
					System.out.println();
					if (!map[y][x].attackCheck(attackX, attackY)) {
						System.out.println("Please check attackRange of this unit.\n");
						continue;
					}
					if (map[attackY][attackX] == null) {
						System.out.println("Unit does not exist at that point.\n");
						continue;
					}
					if (map[attackY][attackX].beDamaged(map[y][x].getPower())) {
						System.out.printf("(%d, %d) located %s is killed by (%d, %d) located %s!\n", attackX + 1,
								attackY + 1, map[attackY][attackX], x + 1, y + 1, map[y][x]);
						map[attackY][attackX] = null;
					} else {
						System.out.printf("(%d, %d) located %s's hp changed from %d to %d.\n", attackX + 1, attackY + 1,
								map[attackY][attackX], map[attackY][attackX].getBeforeHp(),
								map[attackY][attackX].getHp());
					}
					printMap();
					return;
				}
			case 3:
				System.out.println(p + " hand over the turn to other player.\n");
				return;
			default:
				System.out.println("You entered it incorrectly. Please re-enter it.\n");
				continue;
			}
		}
	}

	boolean checkWinner(Player p1, Player p2) {
		boolean checkKing1 = false;
		boolean checkKing2 = false;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] instanceof King && map[i][j].playerNo == 1) {
					checkKing1 = true;
				}
				if (map[i][j] instanceof King && map[i][j].playerNo == 2) {
					checkKing2 = true;
				}
			}
		}
		if (!checkKing1) {
			System.out.println(p2 + " is Winner!");
			return true;
		}
		if (!checkKing2) {
			System.out.println(p1 + " is Winner!");
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		GameMgr pro = new GameMgr();
		pro.doit();
	}
}
