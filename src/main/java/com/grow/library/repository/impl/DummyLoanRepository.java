package com.grow.library.repository.impl;

import com.grow.library.model.Book;
import com.grow.library.model.Loan;
import com.grow.library.model.Member;
import com.grow.library.notiication.LoanObserver;
import com.grow.library.repository.BookRepository;
import com.grow.library.repository.LoanRepository;
import com.grow.library.repository.MemberRepository;
import net.datafaker.Faker;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class DummyLoanRepository implements LoanRepository {
    private Map<Long, Loan> loans = new HashMap<>();

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final List<LoanObserver> observers;

    public DummyLoanRepository(BookRepository bookRepository, MemberRepository memberRepository, List<LoanObserver> observers) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.observers = observers;
        initLoans();
    }

    private void initLoans() {
        Faker faker = new Faker();
        List<Book> books = bookRepository.getBooks();
        List<Member> members = memberRepository.getMembers();

        int id = 1;
        for (Book book : books) {
            for (int i = 1; i <= book.getCopies() - book.getAvailableCopies(); i++) {
                Loan loan = new Loan();
                loan.setId((long) id);
                loan.setBookId(book.getId());
                loan.setMemberId((long) faker.number().numberBetween(1, members.size() + 1));
                loan.setLoanDate(faker.date().past(10, TimeUnit.DAYS).toLocalDateTime().toLocalDate());
                loan.setDueDate(faker.date().between(faker.date().past(3, TimeUnit.DAYS),
                        faker.date().future(5, TimeUnit.DAYS)).toLocalDateTime().toLocalDate());
                loan.setFine(loan.isOverdue() ? BigDecimal.valueOf(faker.number().randomDouble(2, 1, 100)) : BigDecimal.ZERO);
                loan.setObservers(observers);
                loans.put(loan.getId(), loan);
                id++;
            }
        }
    }

    @Override
    public List<Loan> getLoans() {
        return loans.values().stream().toList();
    }
}
