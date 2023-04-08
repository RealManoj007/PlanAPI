package in.plan.service;

import java.util.List;
import java.util.Map;

import in.plan.entity.Plan;

public interface PlanService {

	Map<Integer, String> getPlanCategies();
	
	public boolean savePlan(Plan plan);
	
	public List<Plan> getAllPlans();
	
	public Plan getPlanById(Integer planId);
	
	public boolean updatePlan(Plan plan);
	
	public boolean deletePlanById(Integer planId);
	
	public boolean planStatusChange(Integer id, String status); 
}
