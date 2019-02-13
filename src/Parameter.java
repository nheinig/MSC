import java.sql.Timestamp;

public class Parameter {

	String parameterType;
	String parameterValue;
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String parameterState;

	Parameter(String pt, Timestamp ts, String pv, String ps) {
		this.parameterType = pt;
		this.timestamp = ts;
		this.parameterValue = pv;
		this.parameterState = ps;
	}

}
