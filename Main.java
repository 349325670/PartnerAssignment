// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray


import java.util.Scanner;
// More packages may be imported in the space below

class Main {
  public static void main(String[] args) {

    
    // Please do not edit any of these variables
    Scanner reader = new Scanner(System.in);
    String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
    enterCustomerOption = "1";
    generateCustomerOption = "2";
    exitCondition = "9";

    // More variables for the main may be declared in the space below


    do{

      printMenu();                                    // Printing out the main menu
      userInput = reader.nextLine();                  // User selection from the menu

      if (userInput.equals(enterCustomerOption)){

        // Only the line below may be editted based on the parameter list and how you design the method return
		    // Any necessary variables may be added to this if section, but nowhere else in the code
        enterCustomerInfo();
      }
      else if (userInput.equals(generateCustomerOption)) {

        // Only the line below may be editted based on the parameter list and how you design the method return
        generateCustomerDataFile();
      }
      else{
        System.out.println("Please type in a valid option (A number from 1-9)");
        }

        } while (!userInput.equals(exitCondition));         // Exits once the user types 
        
        reader.close();
        System.out.println("Program Terminated");
    }
    public static void printMenu(){
        System.out.println("Customer and Sales System\n"
        .concat("1. Enter Customer Information\n")
        .concat("2. Generate Customer data file\n")
        .concat("3. Report on total Sales (Not done in this part)\n")
        .concat("4. Check for fraud in sales data (Not done in this part)\n")
        .concat("9. Quit\n")
        .concat("Enter menu option (1-9)\n")
        );
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
   public static void enterCustomerInfo() {
	   
	//Initializing Scanner inside enterCustomerInfo method to take user inputs
      	Scanner reader= new Scanner(System.in);

        //Asknig for information and prompting user to enter info

        //Promts user to input their first name
        System.out.println("What is your FIRST name?");
        String firstName=reader.nextLine();
        
        //Prompts user to input their last name
        System.out.println("What is your LAST name?");
        String lastName= reader.nextLine();

        //Promts user to input city of residence.
        System.out.println("What is your city name?");
        String city= reader.nextLine();

        //Promts user to input postal Code
        System.out.println("What is your postal code?");
        String postalCode= reader.nextLine();

        //Promts to take input for card number
        System.out.println("What is your credit card number?");
        String creditCard = reader.nextLine();

        //I am not taking input below here. Instead I will take the input in the main method right after this method has been called.
   }
    

    public static boolean validatePostalCode(String postalCode){
      if(postalCode.length() < 3) {
    		// postal must be at least 3 or more characters
            System.out.println("Invalid Postal Code: Postal must be at least 3 or more characters");
        	return false;
    	}
      boolean isValidPostalCode;
        do {
            System.out.print("Enter Postal Code (3 or more characters) : ");
            postalCode = reader.nextLine();
            isValidPostalCode = validatePostalCode(postalCode);        	
        } while (!isValidPostalCode);

        String customer = (customerId+"").concat(",")
            .concat(firstName).concat(",")
        		.concat(lastName).concat(",")
        		.concat(city).concat(",")
        		.concat(postalCode).concat(",")
        		.concat(creditCard).concat(",");
        		//.concat(customerId + "");
    	
        return customer;

        try {
    		File pcFile = new File("postal_codes.csv");
    		Scanner reader = new Scanner(pcFile);
    		// skip first line with field names
    		if(reader.hasNextLine()) {
    			reader.nextLine();
    		}
    		while (reader.hasNextLine()) {
    			String data = reader.nextLine();
    			if(data.length()>0) {
    				boolean matched = matchPostalCode(postalCode, data);
    				if(matched) {
    					return true;
    				}
    			}
    		}
    		reader.close();
    	} catch (Exception e) {
    		System.out.println("An error occurred.");
    		e.printStackTrace();
    	}
        System.out.println("Invalid Postal Code: Postal code must be from postal_codes.csv file");
    	return false;
    }

    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void validateCreditCard(){
    }
    /*
    * This method may be edited to achieve the task however you like.
    * The method may not nesessarily be a void return type
    * This method may also be broken down further depending on your algorithm
    */
    public static void generateCustomerDataFile(){
    }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
    
 }
