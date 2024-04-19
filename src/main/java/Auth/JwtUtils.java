package Auth;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

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

    public boolean verifyJwtAuthentication() {
        String[] jwtSegments = getJwtSegmentsFromAuthHeader();

        if (jwtSegments != null && jwtSegments.length == 3) {
            String header = new String(Base64.getUrlDecoder().decode(jwtSegments[0]));
            String payload = new String(Base64.getUrlDecoder().decode(jwtSegments[1]));
//            System.out.println(header);
//            System.out.println(payload);

            if (isJwtExpired(payload)) return false;

            String signature = jwtSegments[2];
            String base64UrlHeaderAndPayload = jwtSegments[0] + "." + jwtSegments[1];
            String calculatedSignature = generateJwtSignature(base64UrlHeaderAndPayload);

            if (!signature.equals(calculatedSignature)) {
                System.out.println("JWT signature verification failed as the signature is not matching");
                return false;
            }
            return true;
        } else {
            System.out.println("Invalid authorization header");
            return false;
        }
    }

    private String[] getJwtSegmentsFromAuthHeader() {
        String[] jwtSegments = null;
        if (jwtToken != null)
            jwtSegments = jwtToken.split("\\.");

        return jwtSegments;
    }

    public boolean isJwtExpired(String payload) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(payload).getAsJsonObject();
        String exp = jsonObject.get("exp").getAsString();

        // Convert to Instant
        Instant instant = Instant.ofEpochSecond(Long.parseLong(exp));

        Date expiration = Date.from(instant);
        Date now = new Date();

        if (expiration != null && expiration.before(now)) {
            return true;
        } else {
            return false;
        }
    }

    public JSONObject getAuthPayload() {
        String[] jwtSegments = getJwtSegmentsFromAuthHeader();

        return new JSONObject(new String(Base64.getUrlDecoder().decode(jwtSegments[1])));
    }

    public String getJWTRole(String payload) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(payload).getAsJsonObject();
        String role = jsonObject.get("role").getAsString();
        System.out.println("Role: " + role);
        return role;
    }


}
