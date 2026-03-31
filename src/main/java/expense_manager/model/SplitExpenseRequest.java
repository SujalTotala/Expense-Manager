package expense_manager.model;

import java.util.List;

public class SplitExpenseRequest {

    private String title;
    private double amount;
    private String category;
    private Long paidById;
    private List<Long> participantIds;

    public String getTitle() { return title; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public Long getPaidById() { return paidById; }
    public List<Long> getParticipantIds() { return participantIds; }
}