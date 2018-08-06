import java.util.Date;
public class Parameter {

	String parameterType;
	String parameterValue;
	Date timestamp;
	
	
	Parameter(String pt, Date ts, String pv){
		this.parameterType = pt;
		this.timestamp =ts;
		this.parameterValue = pv;
	}
	
}
