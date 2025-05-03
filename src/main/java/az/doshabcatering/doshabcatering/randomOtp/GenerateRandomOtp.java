package az.doshabcatering.doshabcatering.randomOtp;

import java.security.SecureRandom;

public class GenerateRandomOtp {

    public String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
