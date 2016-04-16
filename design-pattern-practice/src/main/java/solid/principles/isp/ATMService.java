package solid.principles.isp;

public class ATMService implements LoginService, WithdrawnService {

    @Override
    public boolean authenticate(int pin) {
        return false;
    }

    @Override
    public double withdrawn() {
        return 0;
    }
}
