public class LibraryDemo {
    public static void main(String[] args) {
        // Create the library
        Library library = new Library();

        // Create books (using both constructors)
        Book book1 = new Book("978-0-13-468599-1", "Java Programming", "John Smith");
        Book book2 = new Book("978-0-13-468599-2", "Data Structures");
        Book book3 = new Book("978-0-13-468599-3", "Object Oriented Design", "Jane Doe");

        // Add books to library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Create members
        Member member1 = new Member("M001", "Alice Johnson");
        Member member2 = new Member("M002", "Bob Williams");

        // Register members
        library.registerMember(member1);
        library.registerMember(member2);

        // Print initial state
        System.out.println("--- INITIAL STATE ---");
        System.out.println(library);
        System.out.println();

        // Perform lend operations
        System.out.println("--- LEND OPERATIONS ---");
        library.lendBook(book1, member1);  // Success
        library.lendBook(book2, member2);  // Success
        library.lendBook(book1, member2);  // Rejected: book1 already on loan
        System.out.println();

        // Print state after lending
        System.out.println("--- STATE AFTER LENDING ---");
        System.out.println(library);
        System.out.println();

        // Perform return operations
        System.out.println("--- RETURN OPERATIONS ---");
        library.returnBook(book1, member1);  // Success
        library.lendBook(book1, member2);    // Now succeeds since book1 is returned
        System.out.println();

        // Print final state
        System.out.println("--- FINAL STATE ---");
        System.out.println(library);
        System.out.println();

        // Demonstrate search
        System.out.println("--- SEARCH RESULTS for 'Java' ---");
        for (Book found : library.searchByTitle("Java")) {
            System.out.println("  Found: " + found);
        }
    }
}
