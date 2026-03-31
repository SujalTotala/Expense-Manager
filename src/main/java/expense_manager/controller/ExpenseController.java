package expense_manager.controller;

import expense_manager.model.*;
import expense_manager.repository.ExpenseRepository;
import org.springframework.web.bind.annotation.*;
import expense_manager.repository.ExpenseShareRepository;
import expense_manager.repository.ParticipantRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
    private final ExpenseShareRepository expenseShareRepository;
    private final ParticipantRepository participantRepository;

    public ExpenseController(ExpenseRepository expenseRepository,
                             ExpenseShareRepository expenseShareRepository,
                             ParticipantRepository participantRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseShareRepository = expenseShareRepository;
        this.participantRepository = participantRepository;
    }

    @PostMapping("/split")
    public Expense splitExpense(@RequestBody SplitExpenseRequest request) {

        Expense expense = new Expense();
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());

        Participant payer = participantRepository
                .findById(request.getPaidById())
                .orElseThrow();

        expense.setPaidBy(payer);

        Expense savedExpense = expenseRepository.save(expense);

        double splitAmount = request.getAmount() / request.getParticipantIds().size();

        for (Long participantId : request.getParticipantIds()) {

            Participant participant = participantRepository
                    .findById(participantId)
                    .orElseThrow();

            ExpenseShare share = new ExpenseShare();
            share.setExpense(savedExpense);
            share.setParticipant(participant);
            share.setAmountOwed(splitAmount);

            expenseShareRepository.save(share);
        }

        return savedExpense;
    }

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        expense.setId(id);
        return expenseRepository.save(expense);
    }

    @GetMapping("/balances")
    public List<BalanceResponse> getBalances() {

        List<ExpenseShare> shares = expenseShareRepository.findAll();
        List<BalanceResponse> balances = new ArrayList<>();

        for (ExpenseShare share : shares) {

            Expense expense = share.getExpense();
            Participant payer = expense.getPaidBy();
            Participant participant = share.getParticipant();

            if (!payer.getId().equals(participant.getId())) {
                balances.add(new BalanceResponse(
                        participant.getName(),
                        payer.getName(),
                        share.getAmountOwed()
                ));
            }
        }

        return balances;
    }
}