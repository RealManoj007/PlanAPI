package in.plan.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.plan.entity.Plan;
import in.plan.entity.PlanCategory;
import in.plan.props.AppProperties;
import in.plan.repository.PlanCategoryRepo;
import in.plan.repository.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private PlanCategoryRepo planCategoryRepo;
	
	@Autowired
	private AppProperties appProps;

	@Override
	public Map<Integer, String> getPlanCategies() {
		List<PlanCategory> categories  =this.planCategoryRepo.findAll();
		
		Map<Integer, String> categoryMap=new HashMap<>();
		
		categories.forEach(category-> 
			categoryMap.put(category.getCategoryId(), category.getCategoryName())
		);
		
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan save = planRepo.save(plan);
		return save.getPlanId()!=null?true:false;
	}

	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> findById = this.planRepo.findById(planId);
		return findById.isPresent()?findById.get():null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan plan2 = this.planRepo.save(plan);
		return plan2!=null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status=false;
		try {
			this.planRepo.deleteById(planId);
			status=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer id, String status) {
		System.out.println(" Id is "+id+" Status is "+status);
		Optional<Plan> findById = this.planRepo.findById(id);
		System.out.println(findById);
		if(findById.isPresent()) {
			Plan plan=findById.get();
			plan.setActiveSw(status);
			this.planRepo.save(plan);
			System.out.println(plan.toString());
			return true;
		}
		return false;
	}

}
