import java.util.ArrayList;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private List<Loan> loans;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.loans = new ArrayList<>();
    }

    public Member(String memberId, String name, List<Loan> loans) {
        this.memberId = memberId;
        this.name = name;
        this.loans = (loans != null) ? loans : new ArrayList<>();
    }

    // Getters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public List<Loan> getLoans() { return loans; }

    // Setters
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public void setName(String name) { this.name = name; }

    // Add a loan to this member's list
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    // Remove a loan from this member's list
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    @Override
    public String toString() {
        return "Member[ID=" + memberId + ", Name=" + name + ", ActiveLoans=" + loans.size() + "]";
    }
}
