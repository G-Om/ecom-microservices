package ecom.services.usermanagement.util;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {


    private Long id;


    private Long userId;


    List<Long> productIdList;


    private int total;


    private Date lastEdited;

    private CartStatus status;
    private String shippingAddress;

}
