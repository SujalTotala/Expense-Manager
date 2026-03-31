package expense_manager.model;

import jakarta.persistence.*;
import expense_manager.model.Participant;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Double amount;
    private String category;

    @ManyToOne
    @JoinColumn(name = "paid_by")
    private Participant paidBy;
}