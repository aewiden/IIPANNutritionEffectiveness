
import org.apache.commons.math3.distribution.*;

public class BMI {

	public static double bmiValue; 
	
	// BMI contructor that takes weight in pounds and height in inches
	public BMI(double weight, double height) {
		// Converts pounds to kilograms
		double w = weight/2.205;
		
		// Converts inches to meters	
		double h = height/39.37;
		
		// Calculates BMI using the formula weight(kg)/(height(m)^2)
		double BMI = w/(Math.pow(h, 2));
		
		bmiValue = BMI;
	}
	
	public String toString() {
		return "BMI: " + bmiValue;
	}
	// There is no pattern in this data and I cannot find a more efficient way to copy the data from the spreadsheet
	public double getZScore(int age, char s) {
		// z-score calculation from LMS values for males ages 2-18
		if(s == 'M') {
			if(age == 2) { return zFormula(-2.01118107, 16.575027675, 0.080592465); }
			else if(age == 3) { return zFormula(-1.442628957, 16.038758958, 0.0729895508); }
			else if(age == 4) { return zFormula(-1.653732283, 15.653051916, 0.071730167); }
			else if(age == 5) { return zFormula(-2.542781541, 15.430032072, 0.0755221037); }
			else if(age == 6) { return zFormula(-3.182893018, 15.379908859, 0.0823697413); }
			else if(age == 7) { return zFormula(-3.32687018, 15.496374654, 0.0913231621); }
			else if(age == 8) { return zFormula(-3.199158184, 15.755133465, 0.1012586429); }
			else if(age == 9) { return zFormula(-2.989164598, 16.131185315, 0.1109554781); }
			else if(age == 10) { return zFormula(-2.781736856, 16.603086612, 0.1194757852); }
			else if(age == 11) { return zFormula(-2.603872392, 17.152183022, 0.1262571467); }
			else if(age == 12) { return zFormula(-2.458272656, 17.761620938, 0.1310761867); }
			else if(age == 13) { return zFormula(-2.338549576, 18.415740092, 0.1339794518); }
			else if(age == 14) { return zFormula(-2.235491842, 19.099695568, 0.1352124688); }
			else if(age == 15) { return zFormula(-2.140160305, 19.799124201, 0.1351584088); }
			else if(age == 16) { return zFormula(-2.046748156, 20.499678935, 0.1342909159); }
			else if(age == 17) { return zFormula(-1.956271141, 21.186378131, 0.1331463832); }
			else return zFormula(-1.879823505, 21.843038191, 0.1323204265); 
		}
		// z-score calculation from LMS values for females ages 2-18
			if(age == 2) { return zFormula(-0.98660853, 16.42339664, 0.085451785); }
			else if(age == 3) { return zFormula(-2.003296102, 15.7452564, 0.0788768946); }
			else if(age == 4) { return zFormula(-2.962580386, 15.653051916, 0.0784784485); }
			else if(age == 5) { return zFormula(-3.344047622, 15.15543107, 0.0836594775); }
			else if(age == 6) { return zFormula(-3.245937506, 15.20441335, 0.0929080476); }
			else if(age == 7) { return zFormula(-2.952930993, 15.42816819, 0.1043321392); }
			else if(age == 8) { return zFormula(-2.641343436, 15.79143062, 0.1161952181); }
			else if(age == 9) { return zFormula(-2.379756861, 16.26284277, 0.1271695453); }
			else if(age == 10) { return zFormula(-2.184580762, 16.81367689, 0.1363852755); }
			else if(age == 11) { return zFormula(-2.053484173, 17.4171909, 0.143389972); }
			else if(age == 12) { return zFormula(-1.979359041, 18.04838216, 0.1480728906); }
			else if(age == 13) { return zFormula(-1.954973011, 18.68395801, 0.1505842702); }
			else if(age == 14) { return zFormula(-1.973763307, 19.30243131, 0.1512669735); }
			else if(age == 15) { return zFormula(-2.028911755, 19.88429066, 0.1506087878); }
			else if(age == 16) { return zFormula(-2.111428194, 20.41218911, 0.1492173194); }
			else if(age == 17) { return zFormula(-2.207681525, 20.87105449, 0.1478150781); }
			else return zFormula(-2.29732427, 21.24797262, 0.147248467);
	}
	
	// Calculates the z-score of a child's age using CDC the LMS method
	public double zFormula(double l, double m, double s) {
		return (Math.pow((bmiValue/m), l) - 1) / (l * s);
	}
	
	// Converts z-score to percentile
	// Source: https://gist.github.com/katzider/8583bd5bb432f063afc6
	public double getPercentile(double zScore)
	{
		double percentile = 0;

		NormalDistribution dist = new NormalDistribution();
		percentile = dist.cumulativeProbability(zScore) * 100;
		return percentile;
	}
	
} 
