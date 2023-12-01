public class survivalRate {

	// The avergage rate of survival for nonobese patients
	public static final double BASE_RATE = 77;
	
	// Calculating survival rate based on age, BMI, race/ethnicity (accounting for trends in socioeconomic status), and sex
	public static double rateCalculator(int age, BMI bmi, String ethnicity, char s) {
		// Setting a new variable equal to the base rate to modify
		double adjustedRate = 77;
		
		// Lowering survival rate based on BMI, >95 being obese, weighted based on relative risk
		if(bmi.getPercentile(bmi.getZScore(age, s)) > 95.0) { adjustedRate = (0.92 * adjustedRate); }
		// Lowering survival rate based on BMI >85 but <95 being overweight, weighted based on relative risk
		else if(bmi.getPercentile(bmi.getZScore(age, s)) > 85.0) { adjustedRate = (0.94 * adjustedRate); }
		
		// Adjusting survival rate based on sex, male
		if(s == 'M') { adjustedRate = (0.973 * adjustedRate); }
		// Adjustment for female
		else { adjustedRate = (0.96 * adjustedRate); }
		
		// Adjusting survival rate based on race/ethnicity, weighted based on hazard risk
		// These calculations account for trends in socioeconomic status in races/ethnicities
		if(ethnicity.equals("White")) { adjustedRate = (1.012 * adjustedRate); }
		else if(ethnicity.equals("Hispanic")) { adjustedRate = (0.937 * adjustedRate); }
		else if(ethnicity.equals("Black")) { adjustedRate = (0.957 * adjustedRate); }
		
		// Adjustive survival rate based on age, from 2-18
		if(age < 7) { adjustedRate = (0.978 * adjustedRate);  }
		else if (age < 10 && age >= 7) { adjustedRate = (1.015 * adjustedRate); }
		else if (age < 19 && age >= 10) { adjustedRate = (0.952 * adjustedRate); }
	
		return adjustedRate;
	}

}
