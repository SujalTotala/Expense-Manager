package expense_manager.model;

import java.util.List;

public class SplitExpenseRequest {

    private String title;
    private double amount;
    private String category;
    private Long paidById;
    private List<Long> participantIds;

    // ✅ Default constructor (IMPORTANT for Spring)
    public SplitExpenseRequest() {}

    // ✅ Getters
    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public Long getPaidById() {
        return paidById;
    }

    public List<Long> getParticipantIds() {
        return participantIds;
    }

    // ✅ Setters (VERY IMPORTANT 🔥)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPaidById(Long paidById) {
        this.paidById = paidById;
    }

    public void setParticipantIds(List<Long> participantIds) {
        this.participantIds = participantIds;
    }
}