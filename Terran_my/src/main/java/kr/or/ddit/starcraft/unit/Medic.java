package kr.or.ddit.starcraft.unit;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import kr.or.ddit.starcraft.tool.Weapon;

@Component
@Scope("prototype")
public class Medic extends FootSoldier {
	@Resource(name="injectorWeapon")
	private Weapon weapon;
	@Override
	public Weapon getWeapon() {
		return weapon;
	}
	@Override
	protected String walking() {
		return "시속 80km/s 로 걸어감";
	}

}
