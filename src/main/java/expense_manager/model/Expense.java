package expense_manager.model;

import jakarta.persistence.*;
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

    // ✅ allow null OR handle properly
    @ManyToOne
    @JoinColumn(name = "paid_by", nullable = true)
    private Participant paidBy;
}