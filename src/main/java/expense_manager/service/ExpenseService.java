package expense_manager.service;

import expense_manager.model.Expense;
import expense_manager.repository.ExpenseRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    // ✅ Add
    public Expense addExpense(Expense expense) {
        return repository.save(expense);
    }

    // ✅ Get all
    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    // ✅ Delete
    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }

    // ✅ Update (FIXED 🔥)
    public Expense updateExpense(Long id, Expense updated) {

        Expense existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        existing.setTitle(updated.getTitle());
        existing.setAmount(updated.getAmount());
        existing.setCategory(updated.getCategory());
        existing.setPaidBy(updated.getPaidBy());

        return repository.save(existing);
    }
}