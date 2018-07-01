package com.example.marafet.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long sum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private String currency;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new LinkedList<>();


    public Account() {
    }

    public Account(long sum, String currency, User user) {
        this.sum = sum;
        this.currency = currency;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    //    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private CustomUser customUser;
//
//    @ManyToOne
//    @JoinColumn(name = "profile_id")
//    private Profile profile;
//
//    @ManyToOne
//    @JoinColumn(name = "currency_id")
//    private Currency currency;
//


}
