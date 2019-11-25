import java.util.*;
import java.sql.*;

public class ebookstore {

 static Scanner input = new Scanner(System.in);
 public static void main(String[] args) {

  boolean loopMenu = true;
  int choice;
  while (loopMenu) {
   System.out.println("Enter your choice (1) to (5) from the Menu List");
   System.out.println("***********************************************");
   System.out.println("1) Enter book. ");
   System.out.println("2) Update book. ");
   System.out.println("3) Delete book. ");
   System.out.println("4) Search book. ");
   System.out.println("5) Exit ");

   choice = input.nextInt();
   switch (choice) {

    case 1:
     System.out.println("You have selected option 1:Enter the details of the book\n");
     String Result = insertBooks();
     System.out.println(Result);
     break;

    case 2:
     System.out.println("You have selected option 2 : Update the details of the book");
     String Result1 = updateBooks();
     System.out.println(Result1);
     break;

    case 3:
     System.out.println("You have selected option 3 : Delete the details of the book");
      String Res = deleteBooks();
     System.out.println(Res);
     break;

    case 4:
     input.nextLine();
     String res = "";
     System.out.println("You have selected option 4 : Search any book");
     System.out.println("Do you want to see all books : enter * else  enter 1");
     String entry = input.nextLine();
     if (entry.equals("*")) {
      res= selectAllBooks();
      System.out.println(res);
     } 
     else if (entry.equals("1")) {
    	res = SelectBook(); 
        System.out.println(res);
     }
     break;

    case 5:
     System.out.println("You have selected option 5 : Exit from the Menu");
     loopMenu = false;
     break;

    default:
     System.out.println("This is not a valid Menu Option! Please Select Another");
     break;
   }
  }
 }

private static String SelectBook() {
	String result1 = ""; 
	try {
	    Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "mypass");
	    //String selectSQL1 = "select * from books where id = ?";
	    String selectSQL = "select * from books where id = 3003";
	    //System.out.println("\nselect Query: " + selectSQL1);
	    Statement stmnt = conn1.createStatement();
	   // PreparedStatement stmnt1 = conn1.prepareStatement(selectSQL1);
	   // System.out.println("Enter the ID number of the books to search");
	   // int id = input.nextInt();
	   // stmnt1.setInt(1, id);

	    ResultSet rset1 = stmnt.executeQuery(selectSQL);
	    while (rset1.next()) {
	     result1 = rset1.getInt("id") + ", " + rset1.getString("Title") + "," +
	      rset1.getString("Author") + ", " +
	      rset1.getInt("Qty");
	     }
	   } catch (SQLException e) {
	    e.printStackTrace();
	   }
	return result1;
}

private static String selectAllBooks() {
	String[] str = new String[100];
	String result = "";
	
	try {
	    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "mypass");
	    String selectAll = "select * from books";
	    System.out.println("\nselect Query: " + selectAll);
	    Statement stmt = conn.createStatement();
	    // executeQuery() and return in ResultSet obj
	    ResultSet rset = stmt.executeQuery(selectAll);
	    for(int i = 0; i< 100 ; i++) {
	    while (rset.next()) {
	    	
	     str[i] =rset.getInt("id") + ", " +
	      rset.getString("Title") + "," +
	      rset.getString("Author") + ", " +
	      rset.getInt("Qty")+ "\n";
	     result += str[i];
	    }
	    
	  }
	   
   } catch (SQLException e) {
	    e.printStackTrace();
	   }
	return result;
}

private static String deleteBooks() {
	String result = "";
	try {
		   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "mypass");
		   String deleteSql = "delete from books where id = ?";
		   PreparedStatement stmt = conn.prepareStatement(deleteSql);
		   System.out.println("Enter the ID number of the book to delete");
		   int idnum = input.nextInt();
		   input.nextLine();
		   stmt.setInt(1, idnum);
		   int rowsDeleted = stmt.executeUpdate();
		   if (rowsDeleted > 0) {
		     result ="An existing book was deleted successfully";
		   }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
	return "\n"+result;
}
private static String updateBooks() {
	String result = "";
	try {
		   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "mypass");
		   String UpdateSQL = " update books set Title = ?, Author = ?, Qty = ? where id = ?";
		   PreparedStatement stmt = conn.prepareStatement(UpdateSQL);
		   System.out.println("Enter the ID number of the book");
		   int idnum = input.nextInt();
		   input.nextLine(); // Consume newline left-over
		   System.out.println("Enter the Title of the book");
		   String titleOfBook = input.nextLine();
		   System.out.println("Enter the Author name of the book");
		   String nameOfAuthor = input.nextLine();

		   System.out.println("Enter the Total Quantity of the books in number");
		   int total = input.nextInt();
		   stmt.setInt(4, idnum);
		   stmt.setString(1, titleOfBook);
		   stmt.setString(2, nameOfAuthor);
		   stmt.setInt(3, total);
		   int rowsupdated = stmt.executeUpdate();
		   if (rowsupdated > 0) {
		    result = "An existing book was updated successfully";
		   }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
	return "\n" +result;
}

private static String insertBooks() {
	String result = ""; 
	try {
		   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "mypass");
		   String insertSQL = " insert into books values(?,?,?,?)";
		   PreparedStatement stmt = conn.prepareStatement(insertSQL);
		   System.out.println("Enter the ID number of the book");
		   int idnum = input.nextInt();
		   input.nextLine(); // Consume newline left-over
		   System.out.println("Enter the Title of the book");
		   String titleOfBook = input.nextLine();
		   System.out.println();
		   System.out.println("Enter the Author name of the book");
		   String nameOfAuthor = input.nextLine();
		   System.out.println("Enter the Total Quantity of the books in number");
		   int total = input.nextInt();
		   stmt.setInt(1, idnum);
		   stmt.setString(2, titleOfBook);
		   stmt.setString(3, nameOfAuthor);
		   stmt.setInt(4, total);
		   int rowsinserted = stmt.executeUpdate();
		   if (rowsinserted > 0) {
		    result = "A new book was inserted successfully";
		    }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
	 return "\n"+result;
 }
}