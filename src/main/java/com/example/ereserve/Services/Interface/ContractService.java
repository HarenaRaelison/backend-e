package com.example.ereserve.Services.Interface;

import com.example.ereserve.Entity.Contract;

public interface ContractService {
    Contract contract(Contract contract);
    Contract updateContract(Long idContract,Contract contract);

}
