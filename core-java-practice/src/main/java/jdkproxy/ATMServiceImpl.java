package jdkproxy;

public class ATMServiceImpl implements ATMService {

    @Override
    public double checkBalance() {
        System.out.println("Returning balance for th user");
        return 133.330;
    }

}
