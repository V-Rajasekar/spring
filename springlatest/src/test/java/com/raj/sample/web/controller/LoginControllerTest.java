package com.raj.sample.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.raj.spring.entity.Dept;
import com.raj.spring.repository.DepartmentRepository;
import com.raj.spring.web.controller.LoginController;
import com.raj.spring.web.model.UserBean;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

	@Mock
	private DepartmentRepository departmentRepositoryMock;

	@Mock
	private BindingResult bindingResult;

	@InjectMocks
	private LoginController loginController = new LoginController();

	@Test
	public void testLogin() {
		UserBean userBean = new UserBean();
		userBean.setUserId("355166");
		userBean.setPassword("Test");

		Dept dept = new Dept();
		dept.setDeptId(new BigDecimal(1));
		dept.setDeptName("Finance");
		when(departmentRepositoryMock.findOne(any(BigDecimal.class)))
				.thenReturn(dept);
		when(bindingResult.hasErrors()).thenReturn(false);

		ModelAndView modelAndView = loginController.login(userBean,
				bindingResult);
		assertEquals(modelAndView.getViewName(), "welcome");
	}
}
