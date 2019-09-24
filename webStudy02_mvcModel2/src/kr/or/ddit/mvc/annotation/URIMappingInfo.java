package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

public class URIMappingInfo {
	private URIMappingCondition mappingContion;
	private Object commandHandler; //commandhandler타입의 object
	private Method handlerMethod ;
	
	public URIMappingInfo(URIMappingCondition mappingContion, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingContion = mappingContion;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commandHandler == null) ? 0 : commandHandler.hashCode());
		result = prime * result + ((handlerMethod == null) ? 0 : handlerMethod.hashCode());
		result = prime * result + ((mappingContion == null) ? 0 : mappingContion.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		URIMappingInfo other = (URIMappingInfo) obj;
		if (commandHandler == null) {
			if (other.commandHandler != null)
				return false;
		} else if (!commandHandler.equals(other.commandHandler))
			return false;
		if (handlerMethod == null) {
			if (other.handlerMethod != null)
				return false;
		} else if (!handlerMethod.equals(other.handlerMethod))
			return false;
		if (mappingContion == null) {
			if (other.mappingContion != null)
				return false;
		} else if (!mappingContion.equals(other.mappingContion))
			return false;
		return true;
	}


	//get : 변경불가능하게 하려고 
	public URIMappingCondition getMappingContion() {
		return mappingContion;
	}

	public Object getCommandHandler() {
		return commandHandler;
	}

	public Method getHandlerMethod() {
		return handlerMethod;
	}
	
	
}
