package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Registro;
import com.example.demo.model.Usuario;
import com.example.demo.service.RegistroService;

@Controller
public class ProfileController {

	private final RegistroService registroService;

	public ProfileController(RegistroService registroService) {
		this.registroService = registroService;
	}

	/** true = tema negro (oscuro) por defecto, false = tema blanco (claro) por defecto */
	private static final boolean DEFAULT_DARK_THEME = true;

	private boolean getDarkTheme(String theme, HttpSession session) {
		if (theme != null) {
			session.setAttribute("theme", theme);
		}
		Object saved = session.getAttribute("theme");
		if (saved == null) {
			return DEFAULT_DARK_THEME;
		}
		return !"light".equals(saved);
	}

	@GetMapping({ "/", "/profile" })
	public String profile(Model model, @RequestParam(required = false) String theme, HttpSession session, HttpServletRequest request) {
		boolean darkTheme = getDarkTheme(theme, session);
		return renderProfile(model, true, false, false, false, null, darkTheme, request);
	}

	@GetMapping("/profile/email")
	public String profileEmail(Model model, @RequestParam(required = false) String theme, HttpSession session, HttpServletRequest request) {
		boolean darkTheme = getDarkTheme(theme, session);
		return renderProfile(model, false, true, false, false, null, darkTheme, request);
	}

	@GetMapping("/profile/github")
	public String profileGithub(Model model, @RequestParam(required = false) String theme, HttpSession session, HttpServletRequest request) {
		boolean darkTheme = getDarkTheme(theme, session);
		return renderProfile(model, false, false, true, false, null, darkTheme, request);
	}

	@GetMapping("/profile/tools")
	public String profileTools(Model model, @RequestParam(required = false) String theme, HttpSession session, HttpServletRequest request) {
		boolean darkTheme = getDarkTheme(theme, session);
		return renderProfile(model, false, false, false, true, null, darkTheme, request);
	}

	@GetMapping("/profile/tools/{num}")
	public String profileToolsTab(Model model, @PathVariable int num,
			@RequestParam(required = false) String theme, HttpSession session, HttpServletRequest request) {
		List<Registro> registros = registroService.obtenerRegistros();
		if (num < 1 || num > registros.size()) {
			return "redirect:/profile/tools";
		}
		boolean darkTheme = getDarkTheme(theme, session);
		return renderProfile(model, false, false, false, true, num, darkTheme, request);
	}

	private String renderProfile(Model model, boolean activeName, boolean activeEmail,
			boolean activeGithub, boolean activeTools, Integer selectedTabNum, boolean darkTheme, HttpServletRequest request) {
		String currentPath = request != null ? request.getRequestURI() : "/profile";
		model.addAttribute("currentPath", currentPath);

		// Objeto usuario: todos los datos desde el servidor (luego pueden venir de BD)
		Usuario usuario = obtenerUsuario();
		model.addAttribute("usuario", usuario);

		// Lista dinámica según cantidad de registros
		List<Registro> registros = registroService.obtenerRegistros();
		model.addAttribute("registros", registros);
		List<String> dropdownTabs = registros.stream()
				.map(Registro::getEtiqueta)
				.collect(Collectors.toList());
		model.addAttribute("dropdownTabs", dropdownTabs);
		model.addAttribute("cantidadRegistros", registros.size());

		model.addAttribute("activeName", activeName);
		model.addAttribute("activeEmail", activeEmail);
		model.addAttribute("activeGithub", activeGithub);
		model.addAttribute("activeTools", activeTools);
		model.addAttribute("selectedTabNum", selectedTabNum);
		model.addAttribute("darkTheme", darkTheme);
		// Etiquetas del navbar desde el servidor (no escritas a mano en HTML)
		model.addAttribute("labelNombre", "Nombre");
		model.addAttribute("labelEmail", "Email");
		model.addAttribute("labelGithub", "Github");
		model.addAttribute("labelDropdown", "Dropdown");
		model.addAttribute("tituloPagina", "Perfil");
		return "profile";
	}

	/**
	 * Datos del usuario desde el servidor (después puede venir de BD o sesión).
	 */
	private Usuario obtenerUsuario() {
		Usuario u = new Usuario();
		u.setNombre("Samuel Eduardo Garcia Gomez");
		u.setEmail("a367651@gmail.com");
		u.setGithub("https://github.com/gaarax16");
		u.setAvatarUrl("/images/foto.png");
		return u;
	}
}
