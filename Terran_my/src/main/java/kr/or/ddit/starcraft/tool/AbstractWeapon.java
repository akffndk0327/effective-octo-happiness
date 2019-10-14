package kr.or.ddit.starcraft.tool;


public abstract class AbstractWeapon implements Weapon{

	public abstract String getName(); 
	public abstract int damageLevel(); 
	
	public final String damage() {
		return getName()+"으로 공격!! "+damageLevel()+"데미지를 입힘.";
	}

}
