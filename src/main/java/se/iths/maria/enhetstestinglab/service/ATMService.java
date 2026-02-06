package se.iths.maria.enhetstestinglab.service;

import org.springframework.stereotype.Service;
import se.iths.maria.enhetstestinglab.AccountComponent;
import se.iths.maria.enhetstestinglab.exception.InsufficientFundsException;
import se.iths.maria.enhetstestinglab.exception.InvalidAmountException;
import se.iths.maria.enhetstestinglab.exception.MaxWithdrawalExceededException;

@Service
public class ATMService {
    private static final int maxWithdrawAmount = 3000;

    private final AccountComponent accountComponent;

    public ATMService(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Du kan inte sätta in 0kr eller mindre");
        }

        accountComponent.deposit(amount);
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Du kan inte ta ut så litet belopp");
        }
        if (amount > maxWithdrawAmount) {
            throw new MaxWithdrawalExceededException("Max belopp för uttag är 3000kr försök at ut ett mindre belopp");
        }
        if (amount > accountComponent.getBalance()) {
            throw new InsufficientFundsException("Du har inte tillräckligt med pengar på kontot");
        }
        accountComponent.withdraw(amount);
    }

    public int getBalance() {
        return accountComponent.getBalance();
    }

}
