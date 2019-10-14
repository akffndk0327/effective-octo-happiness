package kr.or.ddit.starcraft.tool;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class RfileGun extends AbstractWeapon {

	public String getName() {
		return "라이플건";
	}
	
	@Override
	public int damageLevel() {
		return 4;
	}
}
