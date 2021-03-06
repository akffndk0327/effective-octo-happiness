package kr.or.ddit.starcraft.unit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import kr.or.ddit.starcraft.tool.RfileGun;
import kr.or.ddit.starcraft.tool.Weapon;

@Component
@Scope("prototype")
public class Marine extends FootSoldier {
	@Resource(type=RfileGun.class)
	private Weapon weapon;
	
	@Override
	public Weapon getWeapon() {
		return weapon;
	}
	@Override
	protected String walking() {
		return "시속 100km/s 로 걸어감";
	}

}