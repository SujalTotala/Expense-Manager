package expense_manager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "expense_shares")
public class ExpenseShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amountOwed;

    // ✅ Many shares → one expense
    @ManyToOne
    @JoinColumn(name = "expense_id", nullable = false)
    private Expense expense;

    // ✅ Many shares → one participant
    @ManyToOne
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participant;

    // ✅ Default constructor
    public ExpenseShare() {}

    // ✅ Getters & Setters
    public Long getId() {
        return id;
    }

    public double getAmountOwed() {
        return amountOwed;
    }

    public void setAmountOwed(double amountOwed) {
        this.amountOwed = amountOwed;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}