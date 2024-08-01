package com.grow.library.service;

import com.grow.library.model.Book;
import com.grow.library.model.Loan;
import com.grow.library.repository.BookRepository;
import com.grow.library.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Repository
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public List<Loan> getLoans() {
        return loanRepository.getLoans();
    }

    public List<Loan> getActiveLoans() {
        return getLoans().stream()
                .filter(Loan::isNotReturned)
                .toList();
    }
}
