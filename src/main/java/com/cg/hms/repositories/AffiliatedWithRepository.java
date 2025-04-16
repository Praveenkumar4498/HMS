package com.cg.hms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hms.entities.AffiliatedWithId;
import com.cg.hms.entities.AffiliatedWith;
import com.cg.hms.entities.Department;
import com.cg.hms.entities.Physician;

@Repository
public interface AffiliatedWithRepository extends JpaRepository<AffiliatedWith,AffiliatedWithId> {
public List<AffiliatedWith> findByDepartment(Department dep);
@Query("SELECT a FROM AffiliatedWith a WHERE a.physician.employeeId = :physicianId")
public List<AffiliatedWith> findByPhysician(int physicianId);
@Query("SELECT COUNT(a) FROM AffiliatedWith a WHERE a.department.departmentId = :depid")
public Integer countByDepartment(int depid);

@Query("SELECT a.physician from AffiliatedWith a WHERE a.department.departmentId = :depid")
public List<Physician> findByDepId(int depid);
}
