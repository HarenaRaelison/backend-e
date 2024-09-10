package com.example.ereserve.Services.implement;

import com.example.ereserve.Entity.Contract;
import com.example.ereserve.Repository.ContractRepository;
import com.example.ereserve.Services.Interface.ContractService;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;

    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract contract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Long idContract, Contract contract) {
        return contractRepository.findById(idContract)
                .map(
                        p -> {
                            p.setEndDate(p.getEndDate());
                            p.setStart(p.getStart());

                            return contractRepository.save(p);
                        }
                ).orElseThrow(()->new RuntimeException("Update Error"));
    }
}
