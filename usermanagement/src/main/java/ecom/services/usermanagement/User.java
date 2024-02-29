package ecom.services.usermanagement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name = "username")
    private String name;

    private String password;

    private String email;

    private String role;

    private boolean membership;

    @Column(name = "create_time")
    private Date time_created;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Cart cart;

//    @OneToMany(mappedBy = "user")
//    private Set<Orders> orders = new HashSet<>();

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "favourites",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )

//    private Set<Product> favourites = new HashSet<>();

}