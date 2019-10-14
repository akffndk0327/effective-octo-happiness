package kr.or.ddit.starcraft.unit;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import kr.or.ddit.starcraft.tool.NuclearWeapon;
import kr.or.ddit.starcraft.tool.Weapon;

@Component
@Scope("prototype")
public class Ghost extends FootSoldier {
	
	@Inject
	private NuclearWeapon weapon;
	
	@Override
	public Weapon getWeapon() {
		return weapon;
	}
	
	@Override
	protected String walking() {
		return "시속 50km/s 로 걸어감";
	}

}
