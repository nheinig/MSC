


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import arden.compiler.*;
import arden.runtime.*;

public class MLM extends ExecutionContext {
	//definition of file paths
	private static final String FILE_PERSONS = "Persons.class";
	private static final String MLM_PERSONS = "Persons.mlm";
	
	//Method to update the parameterValue of Persons.mlm
	public static void updateMLM(int parameterValue) {
		String fileName = FILE_PERSONS;
		String mlmName = MLM_PERSONS;

		System.out.println("Usage: java -jar execmlm.jar [compiled MLM class file] [MLM name]");
		System.out.println("Trying " + fileName + " " + mlmName + "!\n");

		
		File mlmfile = new File(fileName);
		if (!mlmfile.exists()) {
			System.out.println("ERROR: File " + mlmfile + " does not exist!");
			System.exit(3);
		}
		
		MedicalLogicModule mlm = null;			
		try {
			mlm = new CompiledMlm(mlmfile, "Persons");
		} catch (IOException e) {
			System.out.println("ERROR: Could not load MLM " + mlmName + " from file " + mlmfile + "!");
			System.exit(1);
		}
		
		MLM context = new MLM();
		ArdenValue[] arguments = null;
		ArdenValue[] result = null;
		
		//try to run the mlm
		try {
			result = mlm.run(context, arguments);
			System.out.println(result);
		} catch (InvocationTargetException e) {
			System.out.println("ERROR: Could not execute MLM " + mlmName + "!");
			System.exit(2);
		}
		if (result != null && result.length > 0) {
			System.out.println("Return Value: " + result[0].toString());
		} else {
			System.out.println("There was no return value.");
		}
					
		
		
	}
	
}
