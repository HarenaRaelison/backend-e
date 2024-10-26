package com.example.ereserve.Controllers;

import org.springframework.web.bind.annotation.RestController;
import com.example.ereserve.Entity.Contract;
import com.example.ereserve.Services.Interface.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/contracts")
public class ContractController {

    @Autowired
    private ContractService contractService;

    // Endpoint pour créer un contrat
    @PostMapping("/create")
    public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
        Contract newContract = contractService.contract(contract);
        return ResponseEntity.ok(newContract);
    }

    // Endpoint pour mettre à jour un contrat
    @PutMapping("/update/{id}")
    public ResponseEntity<Contract> updateContract(
            @PathVariable("id") Long idContract,
            @RequestBody Contract contract) {
        Contract updatedContract = contractService.updateContract(idContract, contract);
        return ResponseEntity.ok(updatedContract);
    }
}
