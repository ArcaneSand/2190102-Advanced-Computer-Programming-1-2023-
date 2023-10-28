public interface TransactionManager {
    public boolean transferFunds(String senderWalletId, String receiverWalletId, double amount) throws InvalidTransactionException;
    public double getBalance(String walletId);
    public boolean isValidWallet(String walletId);

    public void test();
}
