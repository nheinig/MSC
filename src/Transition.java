
public class Transition {

	int sourceState;
	int destinationState;
	
	String guardType;
	String guardValue;
	
	Transition(int source, int destination, String type, String value){
		sourceState = source;
		destinationState = destination;
		guardType = type;
		guardValue = value;
	}

	
}
