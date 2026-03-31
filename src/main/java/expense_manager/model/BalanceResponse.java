package expense_manager.model;

public class BalanceResponse {

    private String fromParticipant;
    private String toParticipant;
    private double amount;

    public BalanceResponse(String fromParticipant, String toParticipant, double amount) {
        this.fromParticipant = fromParticipant;
        this.toParticipant = toParticipant;
        this.amount = amount;
    }

    public String getFromParticipant() {
        return fromParticipant;
    }

    public String getToParticipant() {
        return toParticipant;
    }

    public double getAmount() {
        return amount;
    }
}