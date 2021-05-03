package com.cgi.dentistapp.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.service.DentistVisitService;

import javassist.NotFoundException;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

	@Autowired
	private DentistVisitService dentistVisitService;


	List<String> timeList = Arrays.asList
										   ("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "13:00", "13:30", "14:00",
												   "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30");

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/appointments").setViewName("appointments");
	}

	//------------------------------------------------------------------CREATE
	@GetMapping("/registration")
	public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model) {
		List<String> dentists = Arrays.asList("Popova I.K.", "Nosova L.L.", "Ivanov A.N.");
		model.addAttribute("timeList", timeList);
		model.addAttribute("dentists", dentists);
		return "create";
	}

	@PostMapping("/registration")
	public String postRegisterForm(DentistVisitDTO dentistVisitDTO) {
		dentistVisitService.addVisit(dentistVisitDTO);
		return "redirect:/appointments";
	}

	//------------------------------------------------------------------RETRIEVE
	@GetMapping("/appointments")
	public String findAll(Model model) {
		List<DentistVisitDTO> appointments = dentistVisitService.listAppointments();
		model.addAttribute("appointments", appointments);
		return "appointments";
	}

	//------------------------------------------------------------------UPDATE
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable Long id, Model model) throws NotFoundException {
		final DentistVisitDTO visitDTO = dentistVisitService.findById(id);
		if (visitDTO == null) {
			throw new NotFoundException("Visit with ID = " + id + "doesn't not exist");
		}
		model.addAttribute("visit", visitDTO);
		model.addAttribute("timeList", timeList);

		return "update";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id,
						 DentistVisitDTO visitDTO,
						 BindingResult bindingResult,
						 Model model) {
		if (bindingResult.hasErrors()) {
			visitDTO.setId(id);
			return "update";
		}
		dentistVisitService.updateTime(visitDTO);

		return "redirect:/appointments";
	}

	//------------------------------------------------------------------DELETE
	@GetMapping("/delete/{id}")
	public String deleteAppointment(@PathVariable Long id) {
		dentistVisitService.deleteVisit(id);
		return "redirect:/appointments";
	}
}
