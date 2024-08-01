package com.grow.library.notification;

import com.grow.library.model.Loan;
import com.grow.library.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    @Value("${notification.threshold:1}")
    private int notificationThreshold;

    private final LoanService loanService;

    @Scheduled(cron = "${notification.cron}")
    public void notifyUsersWithLoans() {
        loanService.getActiveLoans().stream()
                .filter(loan -> ChronoUnit.DAYS.between(LocalDate.now(), loan.getDueDate()) <= notificationThreshold)
                .forEach(Loan::notifyObservers);

        /*
            SELECT
                b.*,
                m.*
            FROM loan l
            INNER JOIN book b ON l.book_id = b.id
            INNER JOIN member m ON l.member_id = m.id
            WHERE l.due_date - CURRENT_DATE <= <threshold>
         */
    }

}
