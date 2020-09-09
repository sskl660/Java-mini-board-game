package main;

import nation.Nation;
import piece.Archer;
import piece.CavalrySoldier;
import piece.Infantry;
import piece.King;
import piece.Piece;

public class Player {
	private static int no = 0;
	int playerNo;
	private String name = "Player";
	private Nation nation;

	Player(Nation nation) {
		no++;
		playerNo = no;
		this.nation = nation;
		name += no;
	}

	Piece[][] arrangePieces(Piece[][] map) {
		if (playerNo == 1) {
			map[4][0] = new King(playerNo);
			map[4][0].setCoordinates(0, 4);
		}
		if (playerNo == 2) {
			map[4][10] = new King(playerNo);
			map[4][10].setCoordinates(10, 4);
		}

		int xBoundary = 0;
		if (playerNo == 2)
			xBoundary += 7;

		int cnt = 0;
		while (true) {
			Archer temp = new Archer(playerNo);
			temp.randCoordinates(xBoundary);
			if (map[temp.getY()][temp.getX()] == null) {
				map[temp.getY()][temp.getX()] = temp;
				cnt++;
			}
			if (cnt == nation.getArcherNum()) {
				cnt = 0;
				break;
			}
		}

		while (true) {
			Infantry temp = new Infantry(playerNo);
			temp.randCoordinates(xBoundary);
			if (map[temp.getY()][temp.getX()] == null) {
				map[temp.getY()][temp.getX()] = temp;
				cnt++;
			}
			if (cnt == nation.getInfantryNum()) {
				cnt = 0;
				break;
			}
		}

		while (true) {
			CavalrySoldier temp = new CavalrySoldier(playerNo);
			temp.randCoordinates(xBoundary);
			if (map[temp.getY()][temp.getX()] == null) {
				map[temp.getY()][temp.getX()] = temp;
				cnt++;
			}
			if (cnt == nation.getCavalrySoldierNum())
				break;
		}
		return map;
	}

	public String toString() {
		return name;
	}
}
