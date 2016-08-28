package com.raj.spring.web.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.raj.spring.entity.Dept;
import com.raj.spring.repository.DepartmentRepository;
import com.raj.spring.web.model.UserBean;


/**
 * @author Rajasekar
 *
 */
@Controller
public class LoginController {
	Logger logger = LoggerFactory.getLogger(LoginController.class);


	@Autowired
	DepartmentRepository departmentRepository;
	
	
	
	public DepartmentRepository getDepartmentRepository() {
		return departmentRepository;
	}

	public void setDepartmentRepository(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("userBean") @Valid UserBean userBean, BindingResult errors) {
		ModelAndView modelAndView = new ModelAndView();
		if(errors.hasErrors()) {
			modelAndView.setViewName("login");
		}
		
		logger.debug("Login User Id {}", userBean.getUserId());
		logger.debug("Password: {}", userBean.getPassword());
		
		Dept dept = departmentRepository.findOne(new BigDecimal(1));
		logger.debug("Department name: " + dept.getDeptName());
		UserBean userbean = (UserBean)modelAndView.getModel().get("userBean");
		logger.debug("Model User Id {}", userBean.getUserId());
		modelAndView.setViewName("welcome");
		return modelAndView;
	}

	@RequestMapping("/welcome")
	public String showLoginForm(ModelMap modelMap) {
		String message = "Welcome To login Page";
		System.out.println("Welcome Eura !!!");
		logger.debug("Message:{}", message);
		UserBean userBean = new UserBean();
		modelMap.addAttribute("userBean", userBean);
		return "login";
	}
}
