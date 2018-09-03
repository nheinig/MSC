import java.util.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
 
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class ConfigurationUI {

	static private ArrayList<String> parameterStrings = new ArrayList<String>();
	static JComboBox<String> parameterCB1 = new JComboBox<String>();
	static JComboBox<String> parameterCB2 = new JComboBox<String>();
	
	//create labels for inputs
	static JLabel ruleNameLabel = new JLabel("Rule name: ");
	static JLabel parameterLabel1 = new JLabel("Parameter 1: ");		
	static JLabel parameterLabel2 = new JLabel("Parameter 2: ");
	
	//create a text field for the rule name
	JTextField ruleNameTF = new JTextField("Enter rule name here.");
	
	//create a button to finish the creation of a new rule
	JButton finishButton = new JButton("Create Rule");
	
	//create Boxes for input values
	Box box = Box.createVerticalBox();
	Box nameBox = Box.createHorizontalBox();
	Box paramBox1 = Box.createHorizontalBox();
	Box paramBox2 = Box.createHorizontalBox();		
	
	

	//Constructor
	ConfigurationUI(ArrayList<String> listOfAvailableParameters){
		updateParameterStrings(listOfAvailableParameters);
	}
	
	//Method that sets the Values of the ComboBox
	void setParameterComboBox() {
		for(int i = 0; i < parameterStrings.size(); i++) {
			parameterCB1.addItem(parameterStrings.get(i));
			parameterCB2.addItem(parameterStrings.get(i));
		}
	}
	
	//Method updates parameterStrings
	void updateParameterStrings(ArrayList<String> al) {
		for(int i = 0; i < al.size(); i++) {
			if(!parameterStrings.contains(al.get(i))) {
				parameterStrings.add(al.get(i));
			}
		}
	}
	
	void createNewRule() throws Exception{
		// create an empty source file
        File sourceFile = File.createTempFile("Hello", ".java");
        sourceFile.deleteOnExit();
 
        // generate the source code, using the source filename as the class name
        String classname = sourceFile.getName().split("\\.")[0];
        String sourceCode = "public class " + classname + "{ public void hello() { System.out.print(\"Hello world\");}}";
 
        // write the source code into the source file
        FileWriter writer = new FileWriter(sourceFile);
        writer.write(sourceCode);
        writer.close();
        /*
        // compile the source file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        File parentDirectory = sourceFile.getParentFile();
        fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(parentDirectory));
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile));
        compiler.getTask(null, fileManager, null, null, null, compilationUnits).call();
        fileManager.close();
        
        // load the compiled class
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { parentDirectory.toURI().toURL() });
        Class<?> helloClass = classLoader.loadClass(classname);
        
        // call a method on the loaded class
        Method helloMethod = helloClass.getDeclaredMethod("hello");
        helloMethod.invoke(helloClass.newInstance());
*/
	}
	
	//Method to create the Configuration UI
	public void createConfigurationUI() {		
		JFrame ui = new JFrame("Inference Engine");
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setParameterComboBox();
		
		//adding components to their boxes
		nameBox.add(ruleNameLabel);
		nameBox.add(ruleNameTF);
		
		paramBox1.add(parameterLabel1);
		paramBox1.add(parameterCB1);
		
		paramBox2.add(parameterLabel2);
		paramBox2.add(parameterCB2);
		
		box.add(nameBox);
		box.add(paramBox1);
		box.add(paramBox2);
		
		box.add(finishButton);
		finishButton.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
		        try {
					createNewRule();
					System.out.println("Rule was " + ruleNameTF.getText() + " created!");
				} catch (Exception e1) {
					System.out.println("Rule could not be created!");
					e1.printStackTrace();
				}
		    }
		});

		ui.add(box);
			
		ui.setSize(400, 400);
		ui.pack();
		ui.setVisible(true);
	}	
	
}
