package kr.or.ddit.starcraft.building;

import kr.or.ddit.starcraft.unit.FootSoldier;

public interface Trainnable {	
	public FootSoldier trainingSoldier(SoldierType type);
	public FootSoldier[] trainingSoldiers(SoldierType type, int num);
}

