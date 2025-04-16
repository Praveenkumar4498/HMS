package com.cg.hms;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

 

import com.cg.hms.entities.Nurse;
import com.cg.hms.entities.Procedures;
import com.cg.hms.entities.Physician;
import com.cg.hms.exceptions.DuplicateEntryException;
import com.cg.hms.exceptions.NoEntryException;
import com.cg.hms.exceptions.NoRecordsException;
import com.cg.hms.exceptions.NoSuchElementException;
import com.cg.hms.services.AffiliatedServiceImpl;
import com.cg.hms.services.AppointmentServiceImpl;
import com.cg.hms.services.DepartmentServiceImpl;
import com.cg.hms.services.NurseServiceImpl;
import com.cg.hms.services.PatientServiceImpl;
import com.cg.hms.services.PhysicianServiceImpl;
import com.cg.hms.services.ProcedureServiceImpl;
import com.cg.hms.services.TrainedInServiceImpl;

 


@SpringBootTest
class HmsApplicationTests {
private NurseServiceImpl service;
private PhysicianServiceImpl physicianService;
private ProcedureServiceImpl procedureService;
private PatientServiceImpl patientService;
private DepartmentServiceImpl departmentService;
private AppointmentServiceImpl appointmentService;
private AffiliatedServiceImpl affiliatedService;
private TrainedInServiceImpl trainedInService;
@Autowired
public void setService(NurseServiceImpl service) {
    this.service = service;
}
@Autowired
public void setPhysicianService(PhysicianServiceImpl physicianService) {
    this.physicianService = physicianService;
}
@Autowired
public void setProcedureService(ProcedureServiceImpl procedureService) {
    this.procedureService = procedureService;
}
@Autowired
public void setPatientService(PatientServiceImpl patientService) {
    this.patientService = patientService;
}
@Autowired
public void setDepartmentService(DepartmentServiceImpl departmentService) {
    this.departmentService = departmentService;
}
@Autowired
public void setAppointmentService(AppointmentServiceImpl appointmentService) {
    this.appointmentService = appointmentService;
}
@Autowired
public void setAffiliatedService(AffiliatedServiceImpl affiliatedService) {
    this.affiliatedService = affiliatedService;
}
@Autowired
public void setTrainedInService(TrainedInServiceImpl trainedInService) {
    this.trainedInService = trainedInService;
}

 

    @Test
    void getAllNurses() {
    assertDoesNotThrow(()->service.getallNurses());
    }
    @Test
    void saveNurse() {
        Nurse nurse = new Nurse(695,"sunaina","HeadNurse",true,120);
        assertDoesNotThrow(()-> service.saveNurse(nurse));

    }
    @Test
    void nurseSaveException() {
        Nurse nurse = new Nurse(681,"sunaina","HeadNurse",true,120);
        assertThrows(DuplicateEntryException.class,()->{service.saveNurse(nurse);
    });
    }
        @Test
        void getPhysicianByEmpId() {
        assertDoesNotThrow(()->physicianService.getPhysicianByEmpid(1));
        }
        @Test
        void savePhysician() {
            Physician physician = new Physician(176,"praveen","engineer",200);
            assertDoesNotThrow(()-> physicianService.savePhysician(physician));

        }
        @Test
        void physicianByNameException() {

            assertThrows(NoSuchElementException.class,()->{physicianService.getPhysicianDetailsByName("ram");
        });

        }
            @Test
            void getCostOfProcedureByEmpId() {
            assertDoesNotThrow(()-> procedureService.getCostOfProcedureById(1));
            }
            @Test
            void saveProcedure() {
                Procedures procedure = new Procedures(198,"surgery",100.0f);
                assertDoesNotThrow(()-> procedureService.saveProcedure(procedure));

            }
            @Test
            void getCostOfProcedureByEmpIdException() {

                assertThrows(NoSuchElementException.class,()->{procedureService.getCostOfProcedureById(200);
            });
            }
            @Test
            void getAllPatients() {
            assertDoesNotThrow(()-> patientService.getallPatients());
            }
            @Test
            void getInsuranceOfPatientByEmpIdException() {

                assertThrows(NoSuchElementException.class,()->{patientService.getInsuranceOfPatient(100);
            });
            }
            @Test
            void getAllDepartments() {
            assertDoesNotThrow(()-> departmentService.getallDepartments());
            }
            @Test
            void getDepartmentByEmpIdException() {

                assertThrows(NoSuchElementException.class,()->{departmentService.getDepartmentDetailByDeptId(100);
            });
            }
            @Test
            void getAllAppointments() {
            assertDoesNotThrow(()-> departmentService.getallDepartments());
            }

 

            @Test
            void getAppointmentByEmpIdException() {

                assertThrows(NoSuchElementException.class,()->{appointmentService.getExaminationRoomByAppointmentId(100);
            });
            }
            @Test
            void getAppointmentByEmpId() {

                assertDoesNotThrow(()-> appointmentService.getExaminationRoomByAppointmentId(13216584));
            }
            @Test
            void getPrimaryAffiliationException() {

                assertThrows(NoEntryException.class,()->{affiliatedService.getPrimaryAffiliation(100);
            });
            }
            @Test
            void getPrimaryAffiliationByEmpId() {

                assertDoesNotThrow(()-> affiliatedService.getPrimaryAffiliation(4));
            }
            @Test
            void getTreatmentException() {

                assertThrows(NoSuchElementException.class,()->{trainedInService.getTreatmentsByPhysicianId(98);
            });
            }
            @Test
            void getTreatmentByPhysicianId() {

                assertDoesNotThrow(()-> trainedInService.getTreatmentsByPhysicianId(3));
            }

 


}