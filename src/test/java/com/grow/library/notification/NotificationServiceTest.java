package com.grow.library.notification;

import com.grow.library.model.Loan;
import com.grow.library.service.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private LoanService loanService;

    @Test
    public void testNotifyUsersWithLoans() {
        LocalDate now = LocalDate.now();
        //not notify
        Loan loan1 = mock(Loan.class);
        when(loan1.getDueDate()).thenReturn(now.plusDays(3));

        //notify
        Loan loan2 = mock(Loan.class);
        when(loan2.getDueDate()).thenReturn(now);

        when(loanService.getActiveLoans()).thenReturn(List.of(loan1, loan2));

        notificationService.notifyUsersWithLoans();

        verify(loanService, times(1)).getActiveLoans();
        verify(loan1, times(0)).notifyObservers();
        verify(loan2, times(1)).notifyObservers();
    }
}
