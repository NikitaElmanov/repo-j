package org.arpit.java2blog.controller;


import java.util.List;

import org.arpit.java2blog.model.Country;
import org.arpit.java2blog.model.Flag;
import org.arpit.java2blog.model.StaticShape;
import org.arpit.java2blog.service.CountryService;
import org.arpit.java2blog.service.FlagService;
import org.arpit.java2blog.service.StaticShapeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CountryController {
	
	@Autowired
	CountryService countryService;
	@Autowired
	FlagService flagService;
    @Autowired
    StaticShapeService staticShapeService;
	
	@RequestMapping(value = "/getAllCountries", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCountries(Model model) {
		
		List<Country> listOfCountries = countryService.getAllCountries();
		List<StaticShape> listOfStaticShapes = staticShapeService.getAllStaticShapes();
		List<Flag> listOfFlags = flagService.getAllFlags();

		model.addAttribute("country", new Country());
		model.addAttribute("listOfCountries", listOfCountries);
		model.addAttribute("listOfFlags", listOfFlags);

		return "countryDetails";
	}

	@RequestMapping(value = "/i", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCountryById() {
		return "index";
	}

	@RequestMapping(value = "/addCountry", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addCountry(@ModelAttribute("country") Country country) {

		if(country.getId()==0)
		{
			countryService.addCountry(country);
		}
		else
		{
			countryService.updateCountry(country);
		}
		
		return "redirect:/getAllCountries";
	}

	@RequestMapping(value = "/updateCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String updateCountry(@PathVariable("id") int id,Model model) {
		model.addAttribute("country", this.countryService.getCountry(id));
		model.addAttribute("listOfCountries", this.countryService.getAllCountries());
		return "countryDetails";
	}

	@RequestMapping(value = "/deleteCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteCountry(@PathVariable("id") int id) {
		countryService.deleteCountry(id);
	 	return "redirect:/getAllCountries";

	}	
}
