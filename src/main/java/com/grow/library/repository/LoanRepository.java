package com.grow.library.repository;

import com.grow.library.model.Loan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository {
    List<Loan> getLoans();
}
