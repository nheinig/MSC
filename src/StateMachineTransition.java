
public class StateMachineTransition {
	String gurard;
	
	StateMachineState source;
	StateMachineState destination;
	
	StateMachineTransition(StateMachineState s, StateMachineState d){
		this.source = s;
		this.destination = d;
	}
	
	
	//Method that checks if a StateMachineState is the source or destination of this transition
	boolean contains(StateMachineState sms) {
		if(sms == source|| sms == destination) {
			return true;
		} else {
			return false;
		}
	}
	
	}
