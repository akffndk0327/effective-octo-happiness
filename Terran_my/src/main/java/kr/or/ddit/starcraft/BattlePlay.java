package kr.or.ddit.starcraft;

import javax.inject.Inject;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import kr.or.ddit.starcraft.building.Barrack;

@Service
public class BattlePlay {
	@Inject
	private Barrack barrack;
	@Inject
	private ConfigurableApplicationContext container;
	
	public void setBarrack(Barrack barrack) {
		this.barrack = barrack;
	}
	
	public void play() {
		
	}
	
	public static void main(String[] args) {
	
		
	}
}
