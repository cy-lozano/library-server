package com.grow.library.notification;

import com.grow.library.model.Book;
import com.grow.library.model.Loan;
import com.grow.library.model.Member;
import com.grow.library.service.BookService;
import com.grow.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SmsNotificationObserver implements LoanObserver {
    private final BookService bookService;
    private final MemberService memberService;

    @Override
    public void update(Loan loan) {
        Book book = bookService.getBook(loan.getBookId());
        Member member = memberService.getMember(loan.getMemberId());
        log.info("Send an sms to member {} {} to return book {}, due date = {}",
                member.getFirstName(),
                member.getLastName(),
                book.getTitle(),
                loan.getDueDate());
        System.out.println();
        //send email notification
    }
}
