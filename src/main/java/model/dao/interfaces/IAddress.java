package model.dao.interfaces;

import model.entities.Address;

public interface IAddress {

    Address register(Address address) throws Exception;
    Address Take(Address address) throws Exception;

}
