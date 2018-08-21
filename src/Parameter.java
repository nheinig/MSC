import java.sql.Timestamp;

public class Parameter {

	String parameterType;
	String parameterValue;
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
	
	Parameter(String pt, Timestamp ts, String pv){
		this.parameterType = pt;
		this.timestamp = ts;
		this.parameterValue = pv;
	}
	
}
