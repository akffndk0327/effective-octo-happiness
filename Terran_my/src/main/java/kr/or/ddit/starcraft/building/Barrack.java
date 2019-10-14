package kr.or.ddit.starcraft.building;

import javax.inject.Inject;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import kr.or.ddit.starcraft.unit.Firebat;
import kr.or.ddit.starcraft.unit.FootSoldier;
import kr.or.ddit.starcraft.unit.Ghost;
import kr.or.ddit.starcraft.unit.Marine;
import kr.or.ddit.starcraft.unit.Medic;

@Component
public class Barrack implements Trainnable {
	//***************
	@Inject
	ConfigurableApplicationContext container;
	//***********
	
	public FootSoldier trainingSoldier(SoldierType type) {
		FootSoldier soldier = null;
		switch (type) {
		case MARINE :
			soldier = container.getBean(Marine.class);
			break;
		case MEDIC :
			soldier = container.getBean(Medic.class);
			break;
		case FIREBAT :
			soldier = container.getBean(Firebat.class);
			break;
		case GHOST :
			soldier = container.getBean(Ghost.class);
			break;
			
		}
		return soldier;
	}

	@Override
	public FootSoldier[] trainingSoldiers(SoldierType type, int num) {
		FootSoldier[] soldiers = new FootSoldier[num];
		for(int i =0; i<num ; i++) {
			soldiers[i] = trainingSoldier(type);
		}
		return soldiers;
	}
	public FootSoldier[] generateMarine(int num) {
		return trainingSoldiers(SoldierType.MARINE, num);
		
	}
	public FootSoldier[] generateFirebat(int num) {
		return trainingSoldiers(SoldierType.FIREBAT, num);
		
	}
	public FootSoldier[] generateGhost(int num) {
		return trainingSoldiers(SoldierType.GHOST, num);
		
	}
	public FootSoldier[] generateMedic(int num) {
		return trainingSoldiers(SoldierType.MEDIC, num);
		
	}
	

}
