import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public Library(List<Book> books, List<Member> members) {
        this.books = (books != null) ? books : new ArrayList<>();
        this.members = (members != null) ? members : new ArrayList<>();
    }

    // Add a book to the library collection
    public void addBook(Book book) {
        if (book != null && !books.contains(book)) {
            books.add(book);
        }
    }

    // Register a new member
    public void registerMember(Member member) {
        if (member != null && !members.contains(member)) {
            members.add(member);
        }
    }

    /**
     * Lend a book to a member.
     * Business rule: A book may be on at most one active loan at any time.
     * @return true if the loan was successful, false if rejected.
     */
    public boolean lendBook(Book book, Member member) {
        if (book == null || member == null) {
            System.out.println("Error: Invalid book or member.");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Rejected: \"" + book.getTitle()
                    + "\" is already on loan and cannot be lent again.");
            return false;
        }
        if (!books.contains(book)) {
            System.out.println("Error: Book is not in this library's collection.");
            return false;
        }
        if (!members.contains(member)) {
            System.out.println("Error: Member is not registered in this library.");
            return false;
        }

        Loan loan = new Loan(member, book);
        book.setAvailable(false);
        member.addLoan(loan);
        System.out.println("Success: \"" + book.getTitle() + "\" lent to " + member.getName() + ".");
        return true;
    }

    /**
     * Return a book that was previously lent to a member.
     * @return true if the return was successful, false if the book was not on loan.
     */
    public boolean returnBook(Book book, Member member) {
        if (book == null || member == null) {
            System.out.println("Error: Invalid book or member.");
            return false;
        }

        Loan loanToReturn = null;
        for (Loan loan : member.getLoans()) {
            if (loan.getBook().equals(book)) {
                loanToReturn = loan;
                break;
            }
        }

        if (loanToReturn == null) {
            System.out.println("Error: " + member.getName()
                    + " does not have an active loan for \"" + book.getTitle() + "\".");
            return false;
        }

        book.setAvailable(true);
        member.removeLoan(loanToReturn);
        System.out.println("Success: \"" + book.getTitle() + "\" returned by " + member.getName() + ".");
        return true;
    }

    /**
     * Search for books by title (case-insensitive partial match).
     * @return list of matching books
     */
    public List<Book> searchByTitle(String title) {
        List<Book> results = new ArrayList<>();
        if (title == null) return results;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    // Getters
    public List<Book> getBooks() { return books; }
    public List<Member> getMembers() { return members; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== LIBRARY STATUS =====\n");
        sb.append("Books (").append(books.size()).append("):\n");
        for (Book b : books) {
            sb.append("  ").append(b).append("\n");
        }
        sb.append("Members (").append(members.size()).append("):\n");
        for (Member m : members) {
            sb.append("  ").append(m).append("\n");
            for (Loan l : m.getLoans()) {
                sb.append("    -> ").append(l).append("\n");
            }
        }
        sb.append("==========================");
        return sb.toString();
    }
}
