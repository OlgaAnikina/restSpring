package com.netcracker.dao;

import com.netcracker.model.Buyer;

import java.util.List;

public interface IBuyerDAO {
    void saveBuyer(Buyer entity);

    List<Buyer> findAllBuyers();

    Buyer findBuyerById(int id);

    void deleteBuyerById(int id);

    void updateBuyer(Buyer buyer);

}
