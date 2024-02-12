package Auth;

import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

public class JwtUtils {
    private String jwtToken;
    private static final String SECRET_KEY = System.getenv("SECRET_KEY");
    private JSONObject payload;

    public JwtUtils(JSONObject payload) {
        this.payload = payload;
    }

    public JwtUtils(String token) {
        this.jwtToken = token;
    }

    public String generateJwt() {
        String base64UrlHeader = this.generateJwtHeader();
        String base64UrlPayload = this.generateJwtPayload();

        String base64UrlHeaderAndPayload = base64UrlHeader + "." + base64UrlPayload;
        String signature = generateJwtSignature(base64UrlHeaderAndPayload);

        String jwt = base64UrlHeaderAndPayload + "." + signature;
//        System.out.println("JWT token is generated\n");
//        System.out.println(jwt);

        return jwt;
    }

    private String generateJwtHeader() {
        // Creating the header
        JSONObject header = new JSONObject();
        header.put("alg", "HS256");
        header.put("typ", "JWT");  // abc2.s23d.isg3

        return Base64.getUrlEncoder().withoutPadding().encodeToString(header.toString().getBytes());
    }

    private String generateJwtPayload() {
        // Adding issued time (iat)
        Instant issuedAt = Instant.now();
        this.payload.put("iat", issuedAt.getEpochSecond());

        // Adding expiration time (exp), e.g., 24 hour from now
        Instant expirationTime = issuedAt.plus(24, ChronoUnit.HOURS);
        this.payload.put("exp", expirationTime.getEpochSecond());

        return Base64.getUrlEncoder().withoutPadding().encodeToString(this.payload.toString().getBytes());
    }

    private String generateJwtSignature(String base64UrlHeaderAndPayload) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

            SecretKeySpec secret_key = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            return Base64.getUrlEncoder().withoutPadding().encodeToString(sha256_HMAC.doFinal(base64UrlHeaderAndPayload.getBytes()));
        } catch (Exception e) {
            System.out.println("Failed to calculate HMAC: " + e.getMessage());
            throw new RuntimeException();
        }
    }


}
