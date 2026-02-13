package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

	@GetMapping({ "/", "/profile" })
	public String profile(Model model) {
		return renderProfile(model, true, false, false, false, null);
	}

	@GetMapping("/profile/email")
	public String profileEmail(Model model) {
		return renderProfile(model, false, true, false, false, null);
	}

	@GetMapping("/profile/github")
	public String profileGithub(Model model) {
		return renderProfile(model, false, false, true, false, null);
	}

	@GetMapping("/profile/tools")
	public String profileTools(Model model) {
		return renderProfile(model, false, false, false, true, null);
	}

	@GetMapping("/profile/tools/{num}")
	public String profileToolsTab(Model model, @PathVariable int num) {
		if (num < 1 || num > 10) {
			return "redirect:/profile/tools";
		}
		return renderProfile(model, false, false, false, true, num);
	}

	private String renderProfile(Model model, boolean activeName, boolean activeEmail,
			boolean activeGithub, boolean activeTools, Integer selectedTabNum) {
		model.addAttribute("name", "Samuel Eduardo Garcia Gomez");
		model.addAttribute("email", "a367651@gmail.com");
		model.addAttribute("github", "https://github.com/gaarax16");
		model.addAttribute("avatarUrl", "/images/foto.png");
		model.addAttribute("dropdownTabs", List.of(
				"Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5",
				"Tab 6", "Tab 7", "Tab 8", "Tab 9", "Tab 10"));
		model.addAttribute("activeName", activeName);
		model.addAttribute("activeEmail", activeEmail);
		model.addAttribute("activeGithub", activeGithub);
		model.addAttribute("activeTools", activeTools);
		model.addAttribute("selectedTabNum", selectedTabNum);
		return "profile";
	}
}
