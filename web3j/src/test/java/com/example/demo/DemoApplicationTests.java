package com.example.demo;

import com.example.contract.FundRaising;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class DemoApplicationTests {
    Web3j web3j = Web3j.build(new HttpService());
    @Test
    void sss() throws IOException {
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
        String clientVersion = web3ClientVersion.getWeb3ClientVersion();

        System.out.println("clientVersion : " + clientVersion);
    }

    @Test
    public void getEthClientVersionASync() throws Exception
    {
        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().sendAsync().get();
        System.out.println("clientVersionASync : " + web3ClientVersion.getWeb3ClientVersion());
    }

    public EthBlockNumber getBlockNumber() throws Exception{  // 현재 블록 번호
        EthBlockNumber result = new EthBlockNumber();
        result = this.web3j.ethBlockNumber()
                .sendAsync()
                .get();
        return result;
    }

    public EthAccounts getEthAccounts() throws ExecutionException, InterruptedException {
        EthAccounts result = new EthAccounts();
        result = this.web3j.ethAccounts()
                .sendAsync()
                .get();
        return result;

    }

    @Test
    public void test() throws Exception
    {
        System.out.println("계정 정보 : + "+ getEthAccounts().getAccounts());
        System.out.println("현재 블록 정보 : " + getBlockNumber().getBlockNumber());
        //새 지갑 생성
        //WalletUtils.generateNewWalletFile("1234", new File("C:\\Users\\multicampus\\Desktop\\project\\blockchain\\account"), true);
        //

        Credentials credentials = WalletUtils.loadCredentials("1234", "C:\\Users\\multicampus\\Desktop\\project\\blockchain\\account\\admin.wallet");
        //Credentials credentials = WalletUtils.loadCredentials("1234", "C:\\Users\\multicampus\\Desktop\\project\\blockchain\\account\\UTC--2022-09-07T16-38-37.430843300Z--090f91f86eb888d11609f59cd4cfcef49520e7db.json");
        //Credentials.create("개인키")

        System.out.println("credentials : " + credentials);
        System.out.println("credentials address : " + credentials.getAddress());
        // 계약 배포
        ContractGasProvider gasProvider = new DefaultGasProvider();

        FastRawTransactionManager manager = new FastRawTransactionManager( // 이게 뭐랑가
                web3j,
                credentials,
                new PollingTransactionReceiptProcessor(web3j, 3000, 3)
        );
        //FundRaising contract = FundRaising.load(credentials.getAddress(),web3j , credentials , gasProvider);;
        FundRaising contract = FundRaising.load(credentials.getAddress(),web3j , credentials , gasProvider);
        BigInteger GAS_LIMIT = BigInteger.valueOf(9_000_000);
        BigInteger GAS_PRICE = BigInteger.valueOf(4_100_000_000L);



        Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
        Transfer transfer = new Transfer(web3, new RawTransactionManager(web3 , credentials , 921));
        System.out.println("transation : " + web3j.ethGetTransactionReceipt("0x33f6df569ee1911219c30590038de9221c856de987b07c6953d954ef0dc44ff5").send().getTransactionReceipt());

        Optional<org.web3j.protocol.core.methods.response.Transaction> t = web3j.ethGetTransactionByHash("0x876c534ddbca08ac6e4e990052349546c84e2840f1f020344283bdca70b5ea30").send().getTransaction();



        System.out.println(t.get().getValue());

        System.out.println(transfer.sendFunds("0xf32ac93d2a067ad49671216bdb7317741fdaea9a",BigDecimal.valueOf(34400), Convert.Unit.WEI).send());

        /////////////////
        credentials = WalletUtils.loadCredentials("1234", "C:\\Users\\multicampus\\Desktop\\project\\blockchain\\account\\admin.wallet");

        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                nonce, BigInteger.valueOf(1000), BigInteger.valueOf(10000), "0xf87cc28f62d85931eb4e67759e51280e93b91ca9", BigInteger.valueOf(10000));

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
        String transactionHash = ethSendTransaction.getTransactionHash();

        System.out.println("되나 " + transactionHash);

    }

}
