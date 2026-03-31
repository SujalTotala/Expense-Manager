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

    public Expense addExpense(Expense expense) {
        return repository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }

    public Expense updateExpense(Long id, Expense expense) {
        expense.setId(id);
        return repository.save(expense);
    }
}