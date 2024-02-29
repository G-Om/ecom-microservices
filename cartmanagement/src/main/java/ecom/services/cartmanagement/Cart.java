package ecom.services.cartmanagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private Long userId;

    @Column
    List<Long> productIdList;

    @Column
    private int total;

    @Column
    private Date lastEdited;

    @Enumerated
    @Column
    private CartStatus status;

    @Column
    private String shippingAddress;

}
