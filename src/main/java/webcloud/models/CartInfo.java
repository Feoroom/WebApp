package webcloud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartInfo {
    private int orderNum;
    private UserPaymentInfo userPaymentInfo;
    private List<CartLineInfo> cartLines = new ArrayList<>();

    public CartLineInfo findLineById(Long id){
        return null;
    }
}
