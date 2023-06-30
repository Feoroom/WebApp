package webcloud.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPaymentInfo {
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phoneNumber;
}
