package com.grow.library.model;

import com.grow.library.notiication.LoanObserver;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Loan {

    private Long id;
    private Long bookId;
    private Long memberId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
    private BigDecimal fine;
    private List<LoanObserver> observers = new ArrayList<>();

    public boolean isNotReturned() {
        return loanDate != null && returnDate == null;
    }

    public boolean isOverdue() {
        return dueDate != null && dueDate.isAfter(LocalDate.now());
    }

    public void notifyObservers() {
        for (LoanObserver observer : observers) {
            observer.update(this);
        }
    }
}
