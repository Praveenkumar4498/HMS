package com.cg.hms.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hms.entities.Nurse;
import com.cg.hms.exceptions.DuplicateEntryException;
import com.cg.hms.exceptions.NoRecordsException;
import com.cg.hms.exceptions.NoSuchElementException;
import com.cg.hms.repositories.NurseRepository;
@Service
public class NurseServiceImpl implements NurseService {

    private NurseRepository nurseRepository;
    
    private static final String NOSUCH = "No nurse with the given id is not available";

    @Autowired
    public void setNurseRepository(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

 

    @Override
    public Nurse saveNurse(Nurse nurse) {
    	 if(nurseRepository.findById(nurse.getEmployeeId()).isEmpty())
    	    return nurseRepository.save(nurse);
    	 throw new DuplicateEntryException("Record with the given id is already existed");
         
    }

 

    @Override
    public List<Nurse> getallNurses() {
         List<Nurse> nurse = nurseRepository.findAll();
         if(nurse.isEmpty())
            throw new NoRecordsException("No records found in the table");
         return nurse;

    }

 

    @Override
    public Nurse getDetailOfNurseByemployeeId(Integer employeeId) {
        return nurseRepository.findById(employeeId).orElseThrow(()-> new NoSuchElementException(NOSUCH));
    }

 

    @Override
    public String getPositionOfNurseByemployeeId(Integer employeeId) {
        return nurseRepository.findById(employeeId).orElseThrow(()-> new NoSuchElementException(NOSUCH)).getPosition();
    }


 

    @Override
    public Boolean nurseIsRegisteredOrNot(Integer employeeId) {
         return nurseRepository.findById(employeeId).orElseThrow(()-> new NoSuchElementException(NOSUCH)).isRegistered();
    }

 

    @Override
    public Nurse updateValueOfregistred(Integer employeeId, Boolean register) {
         Nurse nurse = nurseRepository.findById(employeeId).orElseThrow(()-> new NoSuchElementException(NOSUCH));
         nurse.setRegistered(register);
         return nurseRepository.save(nurse);
        }
    @Override
    public Nurse updateValueOfSSN(Integer employeeId, Integer ssn) {
         Nurse nurse = nurseRepository.findById(employeeId).orElseThrow(()-> new NoSuchElementException(NOSUCH));
         nurse.setSsn(ssn);
         return nurseRepository.save(nurse);
            
        }
    }
