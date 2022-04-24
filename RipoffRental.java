package com.company;
import java.util.Scanner;

/**
 * This class represents the RipoffRental object that has a Bookshelf array that holds Book objects.
 * It contains the main method, where all the program takes in a requested action from the user and
 * performs its corresponding functions listed in the menu.
 *
 * @author  Tracy Yip
 */
public class RipoffRental
{
    private static Bookshelf[] shelves = new Bookshelf[3];//A BookShelf array that has a capacity of 3 shelves.
    private static int count = 0; // A variable used to count the number of books the current bookshelf.
    private static int currentShelf =0; // A variable used to indicate the index of the current shelf in the shelves array

    // Invariants:
    // The count variable will always count the number of books in the current bookshelf
    // The currentShelf will always be in the range of 0-2

    /**
     * This is the main method of the class where the user can interact with the program.
     * Based on selected option,the program will perform the corresponding action listed
     * in the menu. The functions update the bookshelves in the "shelves" array.
     */
    public static void main(String[] args)
    {
        shelves[0]=new Bookshelf();//Initiates a new Bookshelf object in the zero index of "shelves" array
        shelves[1]=new Bookshelf();//Initiates a new Bookshelf object in the first index of "shelves" array
        shelves[2]=new Bookshelf();//Initiates a new Bookshelf object in the second index of "shelves" array
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Jack's aMAzin' Textbook Rentals, highest price guaranteed!");
        printMenu();
        System.out.println("Please select an option:");
        String x = scan.nextLine();
        if(!check(x))
        {
            System.out.println("Option does not exist. Try Again. Please enter an option");
            x = scan.nextLine();
        }
        while(!x.equals("Q") && !x.equals("q"))
        {
            // Adds a new book object at the given index into the bookshelf
            if(x.equals("A") || x.equals("a"))
            {
                optionA(scan);
            }

            //Swaps the positions of two book objects
            else if (x.equals("S") || x.equals("s"))
            {
                optionS(scan);
            }

            //Updates the borrower field in the book object
            else if (x.equals("L") || x.equals("l"))
            {
                optionL(scan);
            }

            //Removes the book at the given index
            else if (x.equals("R") || x.equals("r"))
            {
                optionR(scan);
            }

            //Duplicates the book at the given index and adds a desired position in the shelf
            else if (x.equals("D") || x.equals("d"))
            {
                optionD(scan);
            }

            //Change the current shelf to the one user want to look at
            else if (x.equals("C") || x.equals("c"))
            {
                optionC(scan);
            }

            //Overwrites the selected bookshelf with a copy of the current bookshelf
            else if (x.equals("O") || x.equals("o"))
            {
                optionO(scan);
            }

            // Checks to see if two bookshelves are equal
            else if (x.equals("E") || x.equals("e"))
            {
                optionE(scan);
            }

            // Prints out the table of book objects
            else if (x.equals("P") || x.equals("p"))
            {
                optionP();
            }
            printMenu();// Prints out the menu

            // Asks the user to enter another option
            System.out.println("Please select an option:");
            x = scan.nextLine();
            if(!check(x))
            {
                System.out.println("Option does not exist. Try Again. Please enter an option:");
                x = scan.nextLine();
            }
        }
        System.out.println("Goodbye!");//If the user decides to quit the program
        System.exit(0);
    }

    /**
     * This method prints the menu of options and functions the user can perform on the bookshelf objects.
     */
    public static void printMenu()
    {
        System.out.println("Menu:\n" + "     A) Add Book\n" + "     S) Swap Books\n"
            + "    " + " L) Loan Book\n" + "     R) Remove Book\n" +  "     " +
            "D) Duplicate Book\n" + "     " + "C) Change Shelf\n" +
            "     O) Overwrite shelf with clone of current shelf\n"+ "     "  +
            "E) Check if two shelves are equal\n"  + "     "
            + "P) Print current bookshelf\n" +"     Q) Quit");
    }

    /**
     * This method creates a new book object using the user input for the title, author, and condition.
     * The book object added at an inputted index of the bookshelf.
     *
     * @param scan
     *     A scanner that takes in the user inputs
     */
    public static void optionA(Scanner scan) {
        try {
            System.out.println("Please enter a title: ");
            String title = scan.nextLine();
            System.out.println("Please enter an author: ");
            String author = scan.nextLine();
            System.out.println("Please enter condition(1-5):");
            int condition = conditionCheck(scan.nextInt(), scan);
            System.out.println("Please enter position on shelf: ");
            int place = scan.nextInt();
            scan.nextLine();

            //Checks if the user entered a valid index
            if (place <= 0) {
                System.out.println("You have entered an invalid index. Please enter another position.");
                place = scan.nextInt();
                scan.nextLine();
            }
            Book one = new Book(title, author, condition);
            shelves[currentShelf].addBook(place, one);
            count++;
            System.out.println("Book added!");

        }

        //Checks if the user enters an index is greater than count+1
        catch (IllegalArgumentException |ArrayIndexOutOfBoundsException w) {
            System.out.println(w.getMessage());
            optionA(scan);
        }

        //Checks if the shelf is full
        catch (FullShelfException x)
        {
            System.out.println(x.getMessage());
        }
    }

    /**
     * This method is called on when the user wants to swap the positions of two books in a bookshelf.
     *
     * @param scan
     *    The scanner that is used to take in user inputs
     */
    public static void optionS(Scanner scan)
    {
        try
        {
            System.out.println("Please enter an index");
            int index1 = scan.nextInt();
            scan.nextLine();
            System.out.println("Please enter another index: ");
            int index2 = scan.nextInt();
            scan.nextLine();
            shelves[currentShelf].swapBooks(index1, index2);
            System.out.println(shelves[currentShelf].getBook(index2).getTitle() +
                " has swapped with "+ shelves[currentShelf].getBook(index1).getTitle()+ ".");
        }

        //Checks if the given indexes are out of bounds
        catch (ArrayIndexOutOfBoundsException q)
        {
            System.out.println(q.getMessage());
            optionS(scan);
        }
    }

    /**
     * This method is called on when the user loans out a book and
     * the borrower field of the book object needs to be updated.
     * The condition of the book is also updated in this method.
     *
     * @param scan
     *    The scanner that is used to take in user inputs
     */
    public static void optionL(Scanner scan)
    {
        try
        {
            System.out.println("Please enter an index:");
            int index = scan.nextInt();
            scan.nextLine();
            System.out.println("Please enter a recipient: ");
            String borrower = scan.nextLine();
            System.out.println("Please enter condition(1-5): ");
            int condition= conditionCheck(scan.nextInt(),scan);
            scan.nextLine();
            shelves[currentShelf].getBook(index).setBorrower(borrower);
            shelves[currentShelf].getBook(index).setCondition(condition);
            System.out.println(shelves[currentShelf].getBook(index).getTitle()+
                " has been loaned to "+ borrower+ ".");
        }

        //Checks if the index entered by the user is out of bounds
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e.getMessage());
            optionL(scan);
        }
    }

    /**
     * This method is called on when the user removes a book from the bookshelf.
     *
     * @param scan
     *    The scanner that is used to take in user inputs
     */
    public static void optionR(Scanner scan)
    {
        try
        {
            System.out.println("Please enter an index:");
            int index = scan.nextInt();
            scan.nextLine();
            count--;
            shelves[currentShelf].removeBook(index);
        }

        //Checks if the given index is out of bounds or if the shelf is empty
        catch (ArrayIndexOutOfBoundsException z)
        {
            System.out.println(z.getMessage());
            optionR(scan);
        }

        //Checks if the shelf is empty
        catch (EmptyShelfException a)
        {
            System.out.println(a.getMessage());
        }
    }

    /**
     * This method is called on when the user duplicates a book object in the bookshelf.
     * The index of the book that is getting copied and the index in which the
     * copied book is being placed are both inputted by the user.
     *
     * @param scan
     *    The scanner that is used to take in user inputs
     */
    public static void optionD(Scanner scan)
    {
        try
        {
            System.out.println("Please enter a source index: ");
            int index = scan.nextInt();
            scan.nextLine();

            //checks if there is a book object at the index that can get copied
            if (shelves[currentShelf].getBook(index)==null)
            {
                System.out.println("There is no book at that index. Please enter another index");
                index = scan.nextInt();
                scan.nextLine();
            }
            System.out.println("Please enter a destination index: ");
            int dest = scan.nextInt();
            scan.nextLine();

            //Checks if adding the book at the desired index will create a hole in the array
            if(dest>count+1)
            {
                System.out.println("You will create a space in the bookshelf with that index." +
                    " Please enter a new destination index:");
                dest = scan.nextInt();
                scan.nextLine();
                shelves[currentShelf].addBook(dest,(Book) shelves[currentShelf].getBook(index).clone());
                System.out.println("A new copy of " + shelves[currentShelf].getBook(index).getTitle()
                    + " is in index "+ dest + ".");
            }
            else
            {
                shelves[currentShelf].addBook(dest,(Book)shelves[currentShelf].getBook(index).clone());
                System.out.println("A new copy of " + shelves[currentShelf].getBook(index).getTitle()
                    + " is in index "+ dest);
            }
        }

        //Checks if the user entered an index that is too high or if the index is out of bounds
        catch ( IllegalArgumentException | ArrayIndexOutOfBoundsException p)
        {
            System.out.println(p.getMessage());
            optionD(scan);
        }

        //Checks if the shelf is full
        catch (FullShelfException u)
        {
            System.out.println(u.getMessage());
        }
    }

    /**
     * This method is called on when the user decides to look at a different bookshelf.
     *
     * @param scan
     *    The scanner that is used to take in user inputs
     */
    public static void optionC(Scanner scan)
    {
        System.out.println("Please select shelf to look at: ");
        String s = scan.nextLine();
        currentShelf = assign(s);
        count=shelves[currentShelf].numBooks();
        System.out.println("Shelf " + s.toUpperCase() + " is Selected.");
    }

    /**
     * This method is called on when the user overwrites a bookshelf
     * with a copy of another bookshelf.
     *
     * @param scan
     *    The scanner that is used to take in user inputs
     */
    public static void optionO(Scanner scan)
    {
        System.out.println("Please select shelf to overwrite with the :");
        String selected = scan.nextLine();
        int temp = assign(selected);
        shelves[temp] = (Bookshelf) shelves[currentShelf].clone();
        System.out.println("Shelf " + selected.toUpperCase() +
            " is overwritten with a copy of Shelf " + assign(currentShelf));
    }

    /**
     * This method is called on when the user wants to determine whether two bookshelves are equal.
     * They are equivalent when both bookshelves have the same book object in the same order.
     *
     * @param scan
     *    The scanner that is used to take in user inputs
     */
    public static void optionE(Scanner scan)
    {
        System.out.println("Please select a shelf: ");
        String shelf1 = scan.nextLine();
        System.out.println("Please select another shelf:");
        String shelf2 = scan.nextLine();
        if (shelves[assign(shelf1)].equals(shelves[assign(shelf2)]))
        {
            System.out.println("The shelves are equal.");
        }
        else
        {
            System.out.println("The shelves are not equal.");
        }
    }

    /**
     * This method is called on when the user wants to print out the formatted table
     * to display the properties of the book objects in the bookshelf.
     */
    public static void optionP()
    {
        System.out.println("Bookshelf " +assign(currentShelf)+ ":");
        System.out.println(shelves[currentShelf].toString());
    }
    /**
     * This method assigns the index to the bookshelf based on the letter of the bookshelf (a, b or c) the
     * user entered. The bookshelf letter are not case-sensitive.
     *
     * @param input
     *    The letter of the bookshelf
     * @return
     *    The corresponding index of the bookshelf in the shelves array
     */
    public static int assign(String input)
    {
        if (input.equals("A") || input.equals("a"))
        {
            return 0;
        }
        else if (input.equals("B") || input.equals("b"))
        {
            return 1;
        }
        else if (input.equals("C") || input.equals("c"))
        {
            return 2;
        }
        return -1;
    }

    /**
     * This method assigns the letter to the bookshelf based on its index in the "shelves" array.
     *
     * @param in
     *    The index of the bookshelf in the array
     * @return
     *    The corresponding letter of the bookshelf
     */
    public static String assign(int in)
    {
        if(in==0)
        {
            return "A";
        }
        else if (in==1)
        {
            return "B";
        }
        else if (in==2)
        {
            return "C";
        }
        return "ERROR";
    }

    /**
     * This method checks if the letter of the option entered by the user is in the menu.
     * The letters are not case-sensitive.
     *
     * @param in
     *    The string entered by the user when choosing an option on the menu
     * @return
     *    A boolean that indicates whether the string entered by the user is a valid option on the menu
     */
    public static boolean check(String in)
    {
        String [] options={"A","a","S","s","L","l","R","r","D","d","C","c","O","o","E","e","P","p","Q","q"};
        boolean check=false;
        for(int i=0; i< options.length; i++)
        {
            if(options[i].equals(in))
            {
                check=true;
                break;
            }
        }
        return check;
    }

    /**
     * This method checks if the condition inputted by the user is a valid condition.
     *
     * @param in
     *    The integer for book condition that is entered by the user
     * @param scan
     *    The scanner used to take in user input
     * @return
     *    It returns the original condition entered it falls within the range of 1-5.
     *    If the condition is not valid, it returns the new condition entered by the user.
     */
    public static int conditionCheck(int in, Scanner scan)
    {
        if(!(in>=1 &&in<=5))
        {
            System.out.println("Condition invalid. Please enter condition(1-5):");
        }
        else
        {
            return in ;
        }
        return scan.nextInt() ;
    }
}
