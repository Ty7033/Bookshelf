package com.company;

/**
 * This class represents the Bookshelf object that holds an array of Book objects.
 * There are several functions in this class that can be used to rearrange the
 * book objects in the array.
 *
 *  @author  Tracy Yip
 */
public class Bookshelf
{
    private Book[] books;//An array of book objects
    private int count;//An instance variable used to count the number of books in the array
    public final int CAPACITY=20;//The maximum number of book objects that should be in the array

    // Invariants:
    // The bookshelf will always hold a maximum of 20 books(books = new Book [20])
    // The count variable will always count the number of books currently in the books array

    /**
     * This is a constructor used to create a Bookshelf object.
     * It sets the capacity of the books array to 20
     * and count to 0.
     */
    public Bookshelf()
    {
        books =new Book[CAPACITY];
        count=0;
    }

    /**
     * This is a method that notes the number of books in the array.
     *
     * @return
     *    The total number of books in the bookshelf
     */
    public int numBooks()
    {
        return count;
    }

    /**
     * This method returns the book at a given index in the parameter.
     *
     * @param index
     *    The position of the book in the bookshelf
     * @return
     *    The book object at the index given in the parameter
     * @throws ArrayIndexOutOfBoundsException
     *    When the user enters an index that is less than 1 or greater than 21
     */
    public Book getBook(int index) throws ArrayIndexOutOfBoundsException
    {
        if(isOutOfBounds(index))
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        return books[index-1];
    }

    /**
     * This method removes the book at a given index in the parameter.
     *
     * @param index
     *    The position of the book that needs to be removed in the bookshelf
     * @return
     *    The book removed at the index
     * @throws ArrayIndexOutOfBoundsException
     *    When the index is negative or greater than 21
     * @throws EmptyShelfException
     *    When there are no books in the shelf (count=0)
     */
    public Book removeBook(int index) throws ArrayIndexOutOfBoundsException, EmptyShelfException
    {
        if(isOutOfBounds(index))
        {
            throw new ArrayIndexOutOfBoundsException("You have entered an invalid index. " +
                "Please try again.");
        }
        if(count==0)
        {
            throw new EmptyShelfException();
        }
        Book temp= books[index-1];
        for (int i=index ; i<books.length-1; i++ ) {
            books[i-1]=books[i];
        }
        count--;
        return temp;
    }

    /**
     * This method adds a new Book object into the books array.
     *
     * @param index
     *    The position in the shelf that the new book should be added
     * @param book
     *    The book object that is added to the array
     * @throws IllegalArgumentException
     *    When the user enters an index is greater than count+1 and would create a hole in the array
     * @throws FullShelfException
     *    When the shelf has reached the capacity of 20 and cannot fit another book object
     */
    public void addBook(int index, Book book) throws IllegalArgumentException, FullShelfException, ArrayIndexOutOfBoundsException
    {
        if(count + 1 > 20)
        {
            throw new FullShelfException();
        }
        if(index > CAPACITY)
        {
            throw new ArrayIndexOutOfBoundsException("You have exceeded the capacity of the shelf. Please start again.");
        }
        if(index > count+1)
        {
            throw new IllegalArgumentException("You will create a hole in the bookshelf. Please start again.");
        }
        count++;
        for(int i=books.length-2;i>=index-1; i-- )
        {
            books[i+1]=books[i];
        }
        books[index-1]=book;
    }

    /**
     * This method swaps the positions of two book objects in the books array.
     *
     * @param index1
     *    The position of the first book object to be swapped
     * @param index2
     *    The position of the second book object to be swapped
     * @throws ArrayIndexOutOfBoundsException
     *    When the user inputs an index that is less than 1 or greater than 20
     */
    public void swapBooks(int index1, int index2) throws ArrayIndexOutOfBoundsException
    {
        if(isOutOfBounds(index1)|| isOutOfBounds(index2))
        {
            throw new ArrayIndexOutOfBoundsException("You have entered an invalid index. " +
                "Please try again from the beginning");
        }
        Book temp = books[index1 - 1];
        books[index1 - 1] = books[index2 - 1];
        books[index2 - 1] = temp;
    }

    /**
     * This method makes a copy of all the book objects in the books array.
     * It creates a new Bookshelf object called "clonedBook" that stores a copy
     * of the current bookshelf. Only the title, author, and condition of the book
     * objects are copied into the new bookshelf object.
     *
     * @return
     *    The cloned version of the bookshelf
     */
    public Object clone()
    {
        Bookshelf newShelf=new Bookshelf();
        for(int i=0; i < count; i++)
        {
            Book clonedBook = (Book) books[i].clone();
            try
            {
                newShelf.addBook(i+1, clonedBook);
            }
            catch (IllegalArgumentException | FullShelfException | ArrayIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }
        }
        return newShelf;
    }

    /**
     * This method checks whether a bookshelf object is equal to another bookshelf
     * object.It first checks if the object in the parameter is an instance of the
     * Bookshelf class and whether the number of books in both bookshelves are equal.
     * If true,a new Bookshelf that typecasts the object passed in the parameter is created.
     * The books in the bookshelves are compared to see if the book objects in each index of
     * the array are equal.
     *
     * @param o
     *    The bookshelf object that is being compared to the first bookshelf
     * @return
     *    A boolean called equal (true or false) that indicates if the bookshelves are equal
     */
    public boolean equals(Object o)
    {
        if(o instanceof Bookshelf && count == ((Bookshelf) o).count)
        {
            Bookshelf copy=(Bookshelf)o;
            boolean equal=true;
            for(int i=0; i< count; i++)
            {
                if (!copy.books[i].equals(books[i]))
                {
                    equal=false;
                    break;
                }
            }
            return equal;
        }
        else
        {
            return false;
        }
    }

    /**
     * This method organizes the bookshelf into a table format with columns Spot,
     * Title, Author, Condition, and Borrower. Each row in the table holds the
     * properties of the book object that are neatly lined with the labeled columns.
     *
     * @return
     *    A string that holds the table of bookshelf contents
     */
    public String toString()
    {
        System.out.printf("%-4s %-52s\t %-40s\t %-20s\t %-30s", "SPOT", "TITLE", "AUTHOR","CONDITION","BORROWER");
        System.out.println();
        System.out.println("------------------------------------------------------------" +
            "------------------------------------------------------------------------------" +
            "-----");
        int i=0;
        String output="";
        for(int a=0; a<count; a++)
        {
            i++;
            output+=i +".    "+  books[a].toString() + "\n";
        }
        return output;
    }

    /**
     * This method checks if the inputted index is less than one or greater than the array capacity.
     *
     * @param index
     *    A position in the "shelves" array
     * @return
     *    A boolean that indicates whether the index is out of bounds (index<1 or index>capacity)
     */
    public boolean isOutOfBounds(int index)
    {
        if(index<1 || index>CAPACITY)
        {
            return true;
        }
        return false;
    }
}
