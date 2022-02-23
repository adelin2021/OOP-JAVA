import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChainBlockImplTest {
    private Chainblock chainBlock;
    private List<Transaction> transactions;

    @Before
    public void prepare() {
        //Logic that is executed before each test
        this.chainBlock = new ChainblockImpl();
        this.transactions = new ArrayList<>();
        prepareTransactions();
    }

    private void prepareTransactions() {
        Transaction transaction = new TransactionImpl(0, TransactionStatus.SUCCESSFUL, "Pesho", "Toshko", 10.20);
        Transaction transaction1 = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Pesho", "Toshko", 9.00);
        Transaction transaction2 = new TransactionImpl(2, TransactionStatus.UNAUTHORIZED, "Sasho", "Pesho", 11.0);
        Transaction transaction3 = new TransactionImpl(3, TransactionStatus.FAILED, "Tasho", "Toshko", 12.20);
        this.transactions.add(transaction);
        this.transactions.add(transaction1);
        this.transactions.add(transaction2);
        this.transactions.add(transaction3);
    }

    private void fillChainBlockWithTransactions() {
        this.transactions.forEach(existingTransaction -> this.chainBlock.add(existingTransaction));
    }


    //contains(Transaction) -> return true, if transaction exists
    @Test
    public void testContainsReturnTrue() {
        Transaction transaction = transactions.get(0);
        chainBlock.add(transaction);
        Assert.assertTrue(this.chainBlock.contains(transaction));
    }

    //contains -> return false if transaction does not exist
    @Test
    public void testContainsReturnFalse() {
        Transaction transaction = transactions.get(0);
        Assert.assertFalse(this.chainBlock.contains(transaction));
    }

    //containsById -> return true if transaction exists
    @Test
    public void testContainsByIdReturnsTrue() {
        Transaction transaction = this.transactions.get(0);
        this.chainBlock.add(transaction);
        Assert.assertTrue(this.chainBlock.contains(0));
    }

    //containsById -> return false if transaction does not exist
    @Test
    public void testContainsByIdReturnsFalse() {
        Assert.assertFalse(this.chainBlock.contains(0));
    }

    //add -> Transaction added successfully
    @Test
    public void testAddCorrectTransactionSuccess() {
        //Добавяме транзакция 0
        this.chainBlock.add(transactions.get(0));
        //Проверяваме дали е добавена успешно
        Assert.assertEquals(1, this.chainBlock.getCount());

        //Добавяме транзакция 1
        this.chainBlock.add(transactions.get(1));
        //Проверяваме дали е добавена успешно
        Assert.assertEquals(2, this.chainBlock.getCount());
    }

    //add -> Transaction will not be added
    @Test
    public void testAddTransactionFail() {
        Transaction transaction = this.transactions.get(0);
        this.chainBlock.add(transaction);
        this.chainBlock.add(transaction);
        Assert.assertEquals(1, this.chainBlock.getCount());
    }

    //Test if count method works properly
    @Test
    public void testGetCount() {
        Transaction transaction = this.transactions.get(0);
        Transaction transaction1 = this.transactions.get(1);

        Assert.assertEquals(0, chainBlock.getCount());
        this.chainBlock.add(transaction);
        Assert.assertEquals(1, chainBlock.getCount());
        this.chainBlock.add(transaction1);
        Assert.assertEquals(2, chainBlock.getCount());
    }

    //change transaction status -> throw exception if transaction does not exist
    @Test(expected = IllegalArgumentException.class) //Задължавам те ако не хвърлиш този ексепшън, теста да не мине
    public void testChangeInvalidTransactionStatus() { // Какви compile time ексепшъни можеш да хвърлиш
        this.chainBlock.changeTransactionStatus(100, TransactionStatus.ABORTED);
    }

    //change transaction status -> change transaction status
    @Test
    public void testChangeTransactionStatusSuccess() {
        Transaction transaction = this.transactions.get(0); // SUCCESS
        Assert.assertNotEquals(TransactionStatus.UNAUTHORIZED, transaction.getStatus());

        //Добавяме я в чейнблока
        this.chainBlock.add(transaction);
        //Сменяме и статуса
        this.chainBlock.changeTransactionStatus(0, TransactionStatus.UNAUTHORIZED);

        //Проверяваме дали статусът и е сменен
        Assert.assertEquals(TransactionStatus.UNAUTHORIZED, this.chainBlock.getById(0).getStatus());

    }

    //getById -> Хвърляме ексепшън когато транзакцията липсва
    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdWithWrongId() {
        this.chainBlock.getById(100);
    }

    //getById -> Връщаме транзакцията
    @Test
    public void testGetByIdSuccess() {
        fillChainBlockWithTransactions();
        Transaction actualTransaction = this.chainBlock.getById(0);
        Assert.assertEquals(0, actualTransaction.getId());
    }

    //removeTransactionById -> Нямаме такава транзакция и хвърляме ексепшън
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionWithInvalidId() {
        fillChainBlockWithTransactions();
        this.chainBlock.removeTransactionById(100);
    }

    //removeTransactionById -> премахваме транзакцията
    @Test
    public void removeTransactionByIdSuccess() {
        fillChainBlockWithTransactions();
        this.chainBlock.removeTransactionById(0);
        Assert.assertEquals(3, this.chainBlock.getCount());
        Assert.assertFalse(this.chainBlock.contains(0));
    }

    //getByTransactionStatus -> no such transactions -> throw exception
    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusWithWrongStatus() {
        fillChainBlockWithTransactions();
        this.chainBlock.getByTransactionStatus(TransactionStatus.ABORTED);
    }

    //getByTransactionStatus -> return transactions sorted by amount in descending order
    @Test
    public void testGetByTransactionStatusSuccess() {
        this.transactions.sort(Comparator.comparing(Transaction::getAmount).reversed());
        fillChainBlockWithTransactions();

        List<Transaction> successfulSortedTransactions = this.transactions
                                                                .stream()
                                                                .filter(transaction -> transaction.getStatus().equals(TransactionStatus.SUCCESSFUL))
                                                                .collect(Collectors.toList());
        Iterable<Transaction> actualSortedTransactions = this.chainBlock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        List<Transaction> actualSortedTransactionsList = new ArrayList<>();
        actualSortedTransactions.forEach(actualSortedTransactionsList::add);


        Assert.assertEquals(successfulSortedTransactions, actualSortedTransactionsList);
    }

    //getAllInAmountRange -> Връщаме само транзакции, които са в рейнжда
    @Test
    public void getAllInAmountRangeSuccess(){
        fillChainBlockWithTransactions();
        Iterable<Transaction> resultTransactions = this.chainBlock.getAllInAmountRange(10, 12);
        for (Transaction resultTransaction : resultTransactions) {
            Assert.assertTrue(resultTransaction.getAmount() >= 10);
            Assert.assertTrue(resultTransaction.getAmount() <= 12);
        }

    }

    //getAllInAmountRange -> връщаме празен списък
    @Test
    public void getAllInAmountRangeEmptyResult(){
        fillChainBlockWithTransactions();
        Iterable<Transaction> resultTransactions = this.chainBlock.getAllInAmountRange(100, 200);
        List<Transaction> resultList = new ArrayList<>();
        resultTransactions.forEach(resultList::add);
        Assert.assertTrue(resultList.isEmpty());
    }
}
