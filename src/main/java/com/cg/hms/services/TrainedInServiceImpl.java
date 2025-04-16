package com.cg.hms.services;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 

import com.cg.hms.entities.Physician;
import com.cg.hms.entities.Procedures;
import com.cg.hms.entities.TrainedIn;
import com.cg.hms.exceptions.NoEntryException;
import com.cg.hms.exceptions.NoSuchElementException;
import com.cg.hms.repositories.PhysicianRepository;
import com.cg.hms.repositories.ProcedureRepository;
import com.cg.hms.repositories.TrainedInRepository;

 

@Service
public class TrainedInServiceImpl implements TrainedInService {

    private TrainedInRepository trainedinRepository;

    private PhysicianRepository physicianRepository;

    private ProcedureRepository procedureRepository;
    @Autowired
    public void setTrainedinRepository(TrainedInRepository trainedinRepository) {
        this.trainedinRepository = trainedinRepository;
    }
    @Autowired
    public void setPhyRepo(PhysicianRepository physicianRepository) {
        this.physicianRepository = physicianRepository;
    }
    @Autowired
    public void setProrepo(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

 

    @Override
    public List<Procedures> getTreatmentsByPhysicianId(int physicianId) {
        Physician physician = physicianRepository.findById(physicianId).orElseThrow(()-> new NoSuchElementException("no physician found with the given id"));
        List<TrainedIn> trainedIn = trainedinRepository.findByPhysician(physician);
        if(trainedIn.isEmpty())
            throw new NoEntryException("No treatments found for given physician");
        return trainedIn.stream().map(x->x.getTreatment()).collect(Collectors.toList());
    }

 

    @Override
    public List<Physician> getPhysiciansByTreatmentId(int treatmentId) {
        Procedures treatment = procedureRepository.findById(treatmentId).orElseThrow(()-> new NoSuchElementException("no treatment found for the given id"));
        List<TrainedIn> trainedIn = trainedinRepository.findByTreatment(treatment);
        if(trainedIn.isEmpty())
            throw new NoEntryException("No record found in the table for given treament");
        return trainedIn.stream().map(x->x.getPhysician()).collect(Collectors.toList());

    }

 

    
    @Override
    public boolean updateCertificationExpiryDate(int physicianId, int procedureId, String expiryDate) {
        Physician phy = physicianRepository.findById(physicianId).orElseThrow(()-> new NoSuchElementException("No physician available with the given id"));
        List<TrainedIn> trainedIn = trainedinRepository.findByPhysician(phy);
        trainedIn.stream().filter(x->x.getTreatment().getCode()==procedureId).forEach(x->x.setCertificationExpires(expiryDate));
           return true;
        }

 

    @Override
    public void saveCertification(TrainedIn certification) {
        trainedinRepository.save(certification);

    }

}