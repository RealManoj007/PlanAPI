package in.plan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.plan.entity.Plan;
import in.plan.props.AppConstant;
import in.plan.props.AppProperties;
import in.plan.service.PlanService;

@RestController
public class PlanRestController {

	private PlanService planService;
	
	private Map<String, String> props;
	
	PlanRestController(PlanService planService,AppProperties appProperties){
		this.planService=planService;
		this.props=appProperties.getMessages();
	}
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories(){
		Map<Integer, String> planCategies = planService.getPlanCategies();
		return ResponseEntity.ok(planCategies);
	}
	
	@PostMapping("/plan")
	public ResponseEntity<String>  savePlan(@RequestBody Plan plan){
		boolean savePlan = this.planService.savePlan(plan);
		//Map<String, String> props = appProps.getMessages();
		System.err.println("This is props from "+props);
		props.entrySet().forEach(e->System.out.println(e.getKey()+" : "+e.getValue()));
		return new ResponseEntity<String>(savePlan?props.get(
				AppConstant.PLAN_SAVE_SUCCESS):props.get(AppConstant.PLAN_SAVE_FAIL),HttpStatus.CREATED);	
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plan(){
		List<Plan> allPlans=this.planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}
	
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId){
		Plan planById = this.planService.getPlanById(planId);
		return new ResponseEntity<>(planById,HttpStatus.OK);
	}
	
	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
		boolean isUpdated=this.planService.updatePlan(plan);
		//<String, String> props = appProps.getMessages();
		String msg=isUpdated?props.get(
				AppConstant.PLAN_UPDATE_SUCCESS):props.get(AppConstant.PLAN_UPDATE_FAIL);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
		boolean isDeleted = this.planService.deletePlanById(planId);
		//Map<String, String> props = appProps.getMessages();
		String msg=isDeleted?props.get(AppConstant.PLAN_DELETE_SUCCESS):props.get(AppConstant.PLAN_DELETE_FAIL);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@PutMapping("/statuschange/{planId}/{status}")
	public ResponseEntity<String> statusChange(Integer planId, String status){
		boolean isStatusChanged=this.planService.planStatusChange(planId, status);
		//Map<String, String> props = appProps.getMessages();
		String msg=isStatusChanged?props.get(AppConstant.PLAN_STATUS_CHANGE_SUCCESS):props.get(AppConstant.PLAN_STATUS_CHANGE_FAIL);
		return new ResponseEntity<>(msg,HttpStatus.OK);	
	}
 }