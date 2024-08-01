package com.grow.library.notification;

import com.grow.library.model.Loan;

public interface LoanObserver {
    void update(Loan loan);
}

