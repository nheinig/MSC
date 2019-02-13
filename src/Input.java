import java.sql.Timestamp;

public class Input {

	String inputType;
	float inputValue;
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	Input(String pt, float iv, Timestamp ts) {
		this.inputType = pt;
		this.inputValue = iv;
		this.timestamp = ts;
	}

}
