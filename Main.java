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
        boolean loop= false;

        //Asknig for information and prompting user to enter info
        String firstName, lastName, city, postalCode, creditCardNum;

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
	   
	   
	      //Promts to take input for card number
        do{
        System.out.print("What is your credit card number? ");
        creditCardNum = reader.nextLine();
        loop= validateCreditCard(creditCardNum);
        }while(loop == false);

        String customer = (customerId+"").concat(",")
            .concat(firstName).concat(",")
        		.concat(lastName).concat(",")
        		.concat(city).concat(",")
        		.concat(postalCode).concat(",")
        		.concat(creditCardNum);
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

    
    public static boolean validateCreditCard(String creditCard){

      //Initializing Scanner in this method.
      Scanner reader = new Scanner(System.in);

      //Finding out length by using .length() method
      int cardLen= creditCard.length();

      //Creating a while loop which forces user to input a card number which is 9 in length. If its less than 9 user will be forced to re-input.

      while (cardLen<9){
        System.out.println("Credit Card is invalid. It should be atleast 9 characters. Please try again.");
        creditCard= reader.nextLine();
        cardLen= creditCard.length();
      }

      //Step 1: Reverse the order of digits I used a simple 'for' loop to reverse the string. 
      String reverseCreditCard= "";
      for (int i=cardLen-1; i>=0; i--){
        reverseCreditCard= reverseCreditCard+creditCard.charAt(i);
      }

      //Step 2: Performing a partial sum of all the odd numbers. Giving sum1 a value to perform partial sums. (digits 1, 3, 5...)
      
      int sum1=0;
      for (int i=0; i<cardLen; i=i+2){
        int oddCreditCardInts= charToInt(reverseCreditCard.charAt(i));
        sum1=sum1+oddCreditCardInts;
      }

      //Step number 3: Take the second, fourth ... and every other even digits  after they have been reversed. 
    	//	a.	Multiply each digit by two (double) and sum the digits, 
    	// if the answer is greater than 9 then add the 2 digits to form partial sums for the even digits. 
    	//  b.	Sum the partial sums of the even digits to form sum2

      int evenCreditCardInts=0;
      int sum2=0;
       for (int i=1; i<cardLen; i=i+2){
         evenCreditCardInts= charToInt(reverseCreditCard.charAt(i));

         //Multiply even digits by 2...
          int sum2Digit2x= evenCreditCardInts*2;

        //Over here I did very simple maths that Java uses. Basically I divided the number that was greater than 9 by 10. What this will do give me a whole digit since it was not a double and was an int instead. And then I did the modulus(%) which gave me the remainder and added those two. Eg. If the number was 16, I did 16/10=1 and 16%10=6 and added 1+6=7 So 7 is my newDigit.

        int finalSum2Digit;
        if (sum2Digit2x>9){
          finalSum2Digit=(sum2Digit2x/10)+(sum2Digit2x%10);
        }
        else{
          //This means if the number is less than 9, the final digit will be the digit it initially was. There will be no partial sum. 
          finalSum2Digit= sum2Digit2x;
        }
        sum2= sum2+ finalSum2Digit;
       }
       
        
         //Final Total sum of both sum1 and sum2. This total sum is the sum used to for checking whehter it ends in 0 or not. 
      int totalSum= sum1+sum2;

        //For the last Step about checking whether it ends with a 0, I again used very simple math. I know that if it ends with a zero, the number has to be divided by 10 and remainder should be 0. Eg 70%10=0; but 47%10=7; So I again used the modulus fuction to figure out whether it ends with a zero or no.

        int remainderValue= totalSum%10;
        
        if(remainderValue==0){
          System.out.println("*****Success*****");
          return true;
        }
        else{
          System.out.println("*****Fail*****");
          return false;
        }


    }
    
   
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
  }
    /*******************************************************************
    *       ADDITIONAL METHODS MAY BE ADDED BELOW IF NECESSARY         *
    *******************************************************************/

    //I created this extra method to change my char value to int value to do mathematical operations with the number which is required for validation purposes
  
  public static int charToInt(char charValue) {
		int integerValue = Integer.parseInt(charValue + "");
		return integerValue;
	}
}