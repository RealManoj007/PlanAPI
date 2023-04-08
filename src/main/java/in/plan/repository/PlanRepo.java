package in.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.plan.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
