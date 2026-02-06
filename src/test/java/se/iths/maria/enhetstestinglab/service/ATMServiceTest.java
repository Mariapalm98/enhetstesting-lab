package se.iths.maria.enhetstestinglab.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.maria.enhetstestinglab.AccountComponent;
import se.iths.maria.enhetstestinglab.exception.InsufficientFundsException;
import se.iths.maria.enhetstestinglab.exception.InvalidAmountException;
import se.iths.maria.enhetstestinglab.exception.MaxWithdrawalExceededException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ATMServiceTest {
    @InjectMocks
    private ATMService atmService;

    @Mock
    private AccountComponent accountComponent;

    @Test
    public void testInvalidAmountException() {

        assertThrows(InvalidAmountException.class, () -> atmService.deposit(-1));
    }

    @Test
    public void testMaxWithdrawalExceededException() {

        assertThrows(MaxWithdrawalExceededException.class, () -> atmService.withdraw(5000));
    }

    @Test
    public void testInsufficientFundsException() {
        when(accountComponent.getBalance()).thenReturn(250);

        assertThrows(InsufficientFundsException.class, () -> atmService.withdraw(350));
    }

    @Test
    public void depositTest() {

        atmService.deposit(660);
        verify(accountComponent).deposit(660);
    }

    @Test
    public void withdrawTest() {
        when(accountComponent.getBalance()).thenReturn(1000);
        
        atmService.withdraw(500);
        verify(accountComponent).withdraw(500);
    }

    @Test
    public void getBalanceTest() {
        when(accountComponent.getBalance()).thenReturn(0);

        int balance = atmService.getBalance();

        assertEquals(0, balance);
    }
}