package in.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.plan.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer>{

}
