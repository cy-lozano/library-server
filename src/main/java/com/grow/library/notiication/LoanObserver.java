package com.grow.library.notiication;

import com.grow.library.model.Loan;

public interface LoanObserver {
    void update(Loan loan);
}

