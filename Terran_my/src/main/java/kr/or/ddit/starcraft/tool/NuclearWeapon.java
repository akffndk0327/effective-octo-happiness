package kr.or.ddit.starcraft.tool;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
public class NuclearWeapon extends AbstractWeapon {

	public String getName() {
		return "핵무기";
	}
	
	@Override
	public int damageLevel() {
		return 400;
	}
}