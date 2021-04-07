// Throughout this project, the use of data structures are not permitted such as methods like .split and .toCharArray


import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
// More packages may be imported in the space below

class Main {
  public static void main(String[] args) {

    File f = new File("File");
    System.out.println(f.getAbsolutePath());
    
    // Please do not edit any of these variables
    Scanner reader = new Scanner(System.in);
    String userInput, enterCustomerOption, generateCustomerOption, exitCondition;
    enterCustomerOption = "1";
    generateCustomerOption = "2";
    exitCondition = "9";

    // More variables for the main may be declared in the space below

    String data = "ID,First Name,Last Name,City,Postal Code,Credit Card\n";
      int customerId = 1;

    do{

      printMenu();                                    // Printing out the main menu
      userInput = reader.nextLine();                  // User selection from the menu

      if (userInput.equals(enterCustomerOption)){

        // Only the line below may be editted based on the parameter list and how you design the method return
		    // Any necessary variables may be added to this if section, but nowhere else in the code
        String customer = enterCustomerInfo
        (customerId);
        data = data.concat(customer).concat("\n");
        customerId = customerId + 1;                
      }
      else if (userInput.equals(generateCustomerOption)) {

        // Only the line below may be editted based on the parameter list and how you design the method return
        generateCustomerDataFile(data);
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
   public static String enterCustomerInfo(int customerId) {
	   
	//Initializing Scanner inside enterCustomerInfo method to take user inputs
      	Scanner reader= new Scanner(System.in);

        //Asknig for information and prompting user to enter info
        String firstName, lastName, city, postalCode, creditCard;

        //Promts user to input their first name
        System.out.println("What is your FIRST name?");
        firstName = reader.nextLine();
        
        //Prompts user to input their last name
        System.out.println("What is your LAST name?");
        lastName = reader.nextLine();

        //Promts user to input city of residence.
        System.out.println("What is your city name?");
        city = reader.nextLine();

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
        		.concat(postalCode).concat(",");
        		//.concat(creditCard).concat(",");
        return customer;
   }

    public static boolean validatePostalCode(String postalCode){
    	if(postalCode.length() < 3) {
    		// postal must be at least 3 or more characters
            System.out.println("Invalid Postal Code: Postal must be at least 3 or more characters");
        	return false;
    	}
      
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
    public static void generateCustomerDataFile(String data){
      Scanner reader = new Scanner(System.in);
        boolean shouldWrite;
        File file;
        do {
	        System.out.print("Enter Output File Location : ");
	        String fileLocation = reader.nextLine();
	
	        System.out.print("Enter Output File Name : ");
	        String fileName = reader.nextLine();
	        
	        shouldWrite=true;
	        file = new File(fileLocation, fileName);
	        if(file.exists()) {
	        	System.out.print("File already exists, do you want to use the same file? (y/n) ");
	        	if (!reader.nextLine().startsWith("y"))
	            {
	        		shouldWrite= false;
	            }
	        }
        } while (!shouldWrite);

        try {
			PrintWriter out = new PrintWriter(file);
			out.println(data);
			out.close();
		} catch (Exception e) {
    		System.out.println("An error occurred.");
			e.printStackTrace();
    }
		}
    public static boolean matchPostalCode(String postalCode, String data) {
		String codeFromFile = data.substring(0,3);
		for(int i=0;i<3;i++) {
			char fileChar = codeFromFile.charAt(i);
			char postalCodeChar = postalCode.charAt(i);
			if(fileChar!=postalCodeChar) {
				return false;
			}
		}
		return true;
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/
  } 
  public static int charToInt(char charValue) {
		int integerValue = Integer.parseInt(charValue + "");
		return integerValue;
	}
 }
