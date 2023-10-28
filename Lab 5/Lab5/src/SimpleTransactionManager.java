class InvalidTransactionException extends Exception{
    InvalidTransactionException(String message){
        super(message);
    }
}

public class SimpleTransactionManager implements TransactionManager{

    private String[] wallets = new String[1000];
    private String[] transactions = new String[10000];    
    private int walletCount;
    private int transactionCount;

    public SimpleTransactionManager(String[] wallets){
        for(int i = 0; i < wallets.length; i++){
            this.wallets[i] = wallets[i];
            walletCount++;
        }
        
    }

    public void test(){
        System.out.println(wallets[0]);
    }

    @Override
    public boolean transferFunds(String senderWalletId, String receiverWalletId, double amount) throws InvalidTransactionException {
        if(!isValidWallet(senderWalletId)) throw new InvalidTransactionException("Sender wallet does not exist.");
        if(getBalance(senderWalletId) < amount && !senderWalletId.equals(wallets[0])) throw new IllegalArgumentException("Not enough balance in the source wallet.");

        if(!isValidWallet(receiverWalletId)){
            wallets[walletCount] = receiverWalletId;
            walletCount++;
        }

        transactions[transactionCount] = String.format("%s|%s|%f",senderWalletId,receiverWalletId,amount);
        transactionCount++;

        return true;
    }

    @Override
    public double getBalance(String walletId) {
        if (!isValidWallet(walletId)) {
            throw new IllegalArgumentException("Invalid wallet ID.");
        }
  
        double balance = 0.0;
        for (int i = 0; i < transactionCount; i++) {
            String[] parts = transactions[i].split("\\|");
            if (parts[0].equals(walletId)) {
                balance -= Double.parseDouble(parts[2]);
            }
            if (parts[1].equals(walletId)) {
                balance += Double.parseDouble(parts[2]);
            }
        }
  
        return balance;
    }

    @Override
    public boolean isValidWallet(String walletId) {
        for(int i = 0; i < wallets.length; i++) {
            if(wallets[i]!= null && wallets[i].equals(walletId)) return true;
        }
        return false;
    }
    
}
