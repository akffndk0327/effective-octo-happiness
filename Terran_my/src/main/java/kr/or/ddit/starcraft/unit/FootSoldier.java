package kr.or.ddit.starcraft.unit;

import kr.or.ddit.starcraft.tool.Weapon;

public abstract class FootSoldier {
	public abstract Weapon getWeapon();
	protected abstract String walking();
	protected String withWeapon() {
		return getWeapon().damage();
	}
	public final void attack() {
		System.out.println(getClass().getSimpleName()+", "+walking());
		System.out.println(withWeapon());
	}
}

