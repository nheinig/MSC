import arden.runtime.ArdenBoolean;
import arden.runtime.ArdenNull;
import arden.runtime.ArdenNumber;
import arden.runtime.ArdenString;
import arden.runtime.ArdenValue;
import arden.runtime.BinaryOperator;
import arden.runtime.DatabaseQuery;
import arden.runtime.ExecutionContext;
import arden.runtime.ExpressionHelpers;
import arden.runtime.LibraryMetadata;
import arden.runtime.MaintenanceMetadata;
import arden.runtime.MedicalLogicModule;
import arden.runtime.MedicalLogicModuleImplementation;
import arden.runtime.events.EmptyEvokeSlot;
import arden.runtime.events.EvokeEvent;
import java.util.Date;


import arden.runtime.ExecutionContext;
import arden.runtime.MedicalLogicModuleImplementation;

public class Persons extends MedicalLogicModuleImplementation
{
	private static final LibraryMetadata format$2 = new LibraryMetadata("Demostration of different Arden Syntax features", null, new String[] { "software", "testing", "demonstration" }, "1. Gietzelt M, Goltz U, Grunwald D, Lochau M, Marschollek M, Song B, Wolf KH. \r\n\t\t   Arden2Bytecode: a one-pass Arden Syntax compiler for service-oriented \r\n\t\t   decision support systems based on the OSGi platform.\r\n\t\t   Comput Methods Programs Biomed. 2012 May;106(2):114-25.", "URL \"Arden2Bytecode Webpage\", 'http://www.plri.de/arden2bytecode.html';");
	private static final MaintenanceMetadata format$1 = new MaintenanceMetadata("Demonstration of Arden Synctax features", "personsMLM", "2.5", "1.0", "Uni Oldenburg", "Nils Heinig", null, new Date(1346796000000L), "testing");
	  private EvokeEvent event; 
	  private static final ArdenString $literal4 = new ArdenString("Persons in the room: ");
	  private String stdout;
	  private ArdenValue persons;
	  private static final ArdenNumber $literal0 = new ArdenNumber(1.0D);
	  private ArdenValue personsThreshold;
	    
	  public Persons() {}
	  
	  public Persons(ExecutionContext paramExecutionContext, MedicalLogicModule paramMedicalLogicModule, ArdenValue[] paramArrayOfArdenValue) {
		  for (;;) { 
			 // persons = 
			  personsThreshold = $literal0;
			  stdout = "stdout";    		 
			  personsThreshold = ArdenNull.INSTANCE; 
	    }
	  }
	  

	
	  
	  public boolean logic(ExecutionContext paramExecutionContext)
	  {
	    return BinaryOperator.GT.run(persons, personsThreshold).isTrue();
	  }
	  
	  public ArdenValue[] action(ExecutionContext paramExecutionContext) {
	      paramExecutionContext.write(ExpressionHelpers.concat($literal4, persons), stdout);
	      return new ArdenValue[] { persons };
	  }
	  
	  public EvokeEvent getEvokeEvent(ExecutionContext paramExecutionContext)
	  {
	    if (event != null) {
	      return event;
	    }
	    return this.event = new EmptyEvokeSlot();
	  }

	  public double getPriority()
	  {
	    return 50.0D;
	  }
	  
	  public MaintenanceMetadata getMaintenanceMetadata()
	  {
	    return format$1;
	  }
	  
	  public LibraryMetadata getLibraryMetadata()
	  {
	    return format$2;
	  }
	}
