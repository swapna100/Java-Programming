import java.util.Stack;
import java.beans.Expression;
import java.util.Scanner;


public class RPN {
	
	public static void calcRPN(String exp) throws Exception  {
		
		System.out.println("\nThe RPN you entered is : " +exp);
		if (exp.isEmpty()) {
			throw new Exception("Empty Expression !! need some valid expression with Postfix operators");
		}
		
		// replace all chars and split with space
		String preExpr = exp.replaceAll("[^\\^\\*\\+\\-\\d/\\s]","");
		String[] expression = preExpr.split("\\s");
		
		// Creates a stack data structure
		Stack<Double> stack = new Stack<Double>();
		
		for(String character : expression) {
			Double lastNum, secLastNum;
			
			//switch case to perform calculations 
			switch(character) {
			case "*" :
				lastNum = stack.pop();
				secLastNum = stack.pop();
				stack.push(lastNum * secLastNum);
				break;
			case "/" :
				lastNum = stack.pop();
				secLastNum = stack.pop();
				stack.push(secLastNum/lastNum);
				break;
			case "+" :
				lastNum = stack.pop();
				secLastNum = stack.pop();
				stack.push(secLastNum + lastNum);
				break;	
			case "-" :
				lastNum = stack.pop();
				secLastNum = stack.pop();
				stack.push(secLastNum - lastNum);
				break;	
			case "^" :
				lastNum = stack.pop();
				secLastNum = stack.pop();
				stack.push(Math.pow(secLastNum,lastNum ));
				break;
			default:
				stack.push(Double.parseDouble(character+""));
				}
			}
		     System.out.println("\n The Final answer: " +stack.pop()); 
	}

	public static void main(String[] args) {
		while(true) {
			Scanner input = new Scanner (System.in);
			System.out.println("Enter RPN Expression or \"quit\" to exit");
			String newLine = input.nextLine();
			
			if(newLine.equals("quit")) {
				System.out.println("You have exited the program");
				break;
			} else {
				try {
				 calcRPN(newLine);
				}
				catch(Exception ex) {
					System.out.println(ex.getMessage());
				}	
			 }
	   } 
	}
}
