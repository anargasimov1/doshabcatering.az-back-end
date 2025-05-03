package az.doshabcatering.doshabcatering;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

public class GenerateSecretKey {

    @Test
    public void secretKey(){
        SecretKey key = Jwts.SIG.HS512.key().build();
        String encoderKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println("Secret = " +encoderKey);
    }
}