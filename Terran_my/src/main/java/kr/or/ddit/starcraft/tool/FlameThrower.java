package kr.or.ddit.starcraft.tool;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") //주입할때만 생성한다.,
public class FlameThrower extends AbstractWeapon {

	public String getName() {
		return "화염방사기";
	}
	
	@Override
	public int damageLevel() {
		return 40;
	}
}
