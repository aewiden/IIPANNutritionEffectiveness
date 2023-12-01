import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class inputWindow extends JFrame implements ActionListener, ChangeListener {
// Class variables for updating input and creating components
	String weight, height, ses, gen;
	int age;
	JLabel num = new JLabel("");
	JTextField heightField, weightField;
	JComboBox<String> cb, cb2;
	JPanel panel, resultPanel;
	JButton reset;
	JSlider slider;
	double rate;

public static void main(String[] args) {
	// Creates window
		inputWindow mp = new inputWindow("Pediatric Leukemia Survival Rate Predictor");
		mp.setVisible(true);
	    mp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mp.setSize(615, 265);
	    mp.setLocation(430, 100);
	}

	public inputWindow(String name) {
		// Creates GUI window
				super(name);
			    
			// Creates an adds panel for input
			// Box Layout Source: https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
			    panel = new JPanel();
			    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			    add(panel);
			    
			// Creates a reset button
			// Source: https://stackoverflow.com/questions/33305023/how-to-make-my-reset-button-work
			    reset = new JButton("Reset");
			    panel.add(reset);
			    reset.addActionListener(this);
			    
			// Labels the age slider
			    JLabel ageLbl = new JLabel("Select an age:");
			    ageLbl.setVisible(true);

			    panel.add(ageLbl);
			    
			// Creates and adds slider to select age 
			// Source: https://examples.javacodegeeks.com/desktop-java/swing/java-swing-slider-example/
			    slider = new JSlider(JSlider.HORIZONTAL, 2, 18, 10);
			    slider.setMajorTickSpacing(2);
			    slider.setMinorTickSpacing(1);
			    slider.setPaintTicks(true);
			    slider.setPaintLabels(true);
			    
			// Labels slider with age values
			    Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
			    for(int i = 2; i <= 18; i += 2) {
			    	position.put(i, new JLabel("" + i));
			    }
			    slider.setSnapToTicks(true);
			    slider.addChangeListener(this);
			    
			    panel.add(slider);
			
			// Labels the combo box for gender
			    JLabel genLbl = new JLabel("Select a gender:");
			    genLbl.setVisible(true);
			    panel.add(genLbl);
			    
			    String[] genderChoices = { "Male", "Female" };
			    cb2 = new JComboBox<String>(genderChoices);
			  
			    cb2.setVisible(true);
			    cb2.addActionListener(this);
			    panel.add(cb2);
			    gen = (String) cb2.getSelectedItem();
			    
			// Labels the combo box for race
			    JLabel sesLbl = new JLabel("Select a race/ethnicity:");
			    sesLbl.setVisible(true);
			    panel.add(sesLbl);
			    
			    String[] reChoices = { "White", "Black", "Hispanic"};
			// Creates drop down menu for race/ethnicity
			// Source: https://stackoverflow.com/questions/22506331/simple-dropdown-menu-in-java
			    cb = new JComboBox<String>(reChoices);

			    cb.setVisible(true);
			    cb.addActionListener(this);
			    panel.add(cb);
			    ses = (String) cb.getSelectedItem();
			    
			// Labels height field
			    JLabel hLbl = new JLabel("Enter height in inches:");
			    hLbl.setVisible(true);
			    panel.add(hLbl);
			    
			// Adds and creates text field for height
			// Source: https://www.codejava.net/java-se/swing/jtextfield-basic-tutorial-and-examples
			    heightField = new JTextField(3);
			    heightField.setText("0");
			    heightField.setColumns(10);
			    heightField.addActionListener(this);
			    
			    panel.add(heightField);

			    
			// Labels weight field
			    JLabel wLbl = new JLabel("Enter a weight in pounds:");
			    wLbl.setVisible(true);
			    panel.add(wLbl);
			 
			// Adds and creates text field for weight
			    weightField = new JTextField(3);
			    weightField.setText("0");
			    weightField.setColumns(10);
			    weightField.addActionListener(this);
			    
			    panel.add(weightField);
			   
			    
			// Creates label for final result
			JLabel result = new JLabel("Your predicted chance of survival (percentage) is: ");
			panel.add(result);
			
			// Creates bottom panel to display the resulting survival rate
			resultPanel = new JPanel();
			add(resultPanel, BorderLayout.SOUTH);
			resultPanel.add(num);
	}
	
	// Declaration of ActionListener method
	public void actionPerformed(ActionEvent e) {
	
		// When a new height or weight is inputted
		height = heightField.getText();
		// Catches exception is a number is not entered
		 try { Integer.parseInt(height.trim()); }
		    catch (NumberFormatException nfe) { System.out.println("Error"); }
		weight = weightField.getText();
		 try { Integer.parseInt(weight.trim()); }
		    catch (NumberFormatException nfe) { System.out.println("Error"); }
		
		 // When a new gender or race/ethnicity is selected
		gen = (String) cb2.getSelectedItem();
		ses = (String) cb.getSelectedItem();
		
		// Recalculate rate if anything is changed 
		finalCalculate();
		
		// Reset to base rate when input is changed
		if(e.getSource() == reset) {
			rate = survivalRate.BASE_RATE;
		}
		
		// Update the predicted percentage and clear the panel to display new rate
		num = new JLabel("" + rate);
		resultPanel.removeAll();
		resultPanel.add(num);
	}

	// Declaration of ChangeListener method
	public void stateChanged(ChangeEvent event) {
		// When age is changed
		age = slider.getValue();
		
		// Recalculate rate
		finalCalculate();
		
		// Update the saved rate for display
		num = new JLabel("" + rate);
	}
	
	// Calculates chance of survival using the data inputted in the GUI
	// Updates the class variable for rate
	public void finalCalculate() {
		// Creates BMI object using the textField data
		BMI bm = new BMI(Integer.parseInt(weight.trim()), Integer.parseInt(height.trim()));
		// Calculation and update
		rate = survivalRate.rateCalculator(age, bm, ses, gen.charAt(0));
	}
	
}
