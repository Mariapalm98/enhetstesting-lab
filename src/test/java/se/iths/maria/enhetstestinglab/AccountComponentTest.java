package se.iths.maria.enhetstestinglab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountComponentTest {
    private AccountComponent accountComponent;

    @BeforeEach
    public void setUp() {
        accountComponent = new AccountComponent();
    }

    @Test
    public void testBalanceStart() {
        assertEquals(0, accountComponent.getBalance());
    }

    @Test
    public void testDeposits() {
        accountComponent.deposit(125);
        assertEquals(125, accountComponent.getBalance());
    }

    @Test
    public void testWithdraw() {
        accountComponent.deposit(125);
        accountComponent.withdraw(125);
        assertEquals(0, accountComponent.getBalance());

    }

    @Test
    public void testDepositAndWithdraw() {
        accountComponent.deposit(1000);
        accountComponent.deposit(500);
        accountComponent.withdraw(500);
        assertEquals(1000, accountComponent.getBalance());
    }
}
