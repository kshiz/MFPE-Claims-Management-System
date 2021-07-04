package com.cognizant.controller;

import java.nio.file.ProviderNotFoundException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import com.cognizant.helper.ClaimStatusResult;
import com.cognizant.helper.ProviderResult;
import com.cognizant.helper.UserLoginCredential;
import com.cognizant.helper.UserToken;
import com.cognizant.helper.ViewBillResponse;
import com.cognizant.models.MemberSubmitClaim;
import com.cognizant.service.AuthService;
import com.cognizant.service.MemberPortalService;

import javassist.tools.web.BadHttpRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberPortalController {

	@Autowired
	private MemberPortalService memberPortalService;

	@Autowired
	private AuthService auth;

	@Autowired
	private RestTemplate restTemp;

	@ExceptionHandler(BadRequest.class)
	public String handleException2(BadRequest ex, Model model) {
		model.addAttribute("message", "Session Timed Out, Login Again");
		return "redirect:login";
	}

	HttpSession session = null;

	@RequestMapping("/")
	public String mainScreen(Model model, HttpSession session) {
		model.addAttribute("title", "Home");
		session.setAttribute("token", "Invalid");
		return "login";
	}



	@RequestMapping("/session-timed-out")
	public String timedOut(Model model) {
		model.addAttribute("message", "Session Timed Out, Login Again");
		return "login";
	}

	@RequestMapping("/login-retry")
	public String loginRetry(Model model) {
		model.addAttribute("message", "Invalid Credentials");
		return "login";
	}

	@RequestMapping("/login")
	public String LogIn() {
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(Model model, HttpSession session) {
		session.setAttribute("token", "Invalid");
		model.addAttribute("message", "Successfully Logged Out");
		return "login";
	}

	@RequestMapping(value = "/memberHome")
	public String MemberPort(Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		String valid = auth.validateToken(token);
		if (valid == "True") {
			return "MemberHome";
		}
		return "redirect:/session-timed-out";
	}

	@RequestMapping(value = "/memberHome", method = RequestMethod.POST)
	public String MemberPortal(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model, HttpSession session) {

		UserLoginCredential creds = new UserLoginCredential();
		creds.setPassword(password);
		creds.setUserid(username);

		// creds class contains id and password variables

		ResponseEntity<UserToken> response = null;

		try {
			response = restTemp.postForEntity("http://authorizationservice/api/auth/login", creds, UserToken.class);
		} catch (Exception e) {
			log.error("Invalid credentials");
			return "redirect:/login-retry";
		}

		UserToken userToken = (UserToken) response.getBody();

		log.debug("token{}:", userToken.getAuthToken());
		log.debug("userToken{}: ", userToken);

		session.setAttribute("token", userToken.getAuthToken());

		log.debug("session{}:", session.toString());

		model.addAttribute("title", "Member Home");
		return "MemberHome";

	}

	@RequestMapping("/showClaimPage")
	public String showClaimPage(Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		String valid = auth.validateToken(token);
		if (valid == "True") {
			model.addAttribute("title", "Submit Claim");
			MemberSubmitClaim memberSubmitClaim = new MemberSubmitClaim();
			model.addAttribute("memberSubmitClaim", memberSubmitClaim);
			return "submitClaim";
		}
		return "redirect:/session-timed-out";
	}

	@RequestMapping(value = "/submitClaim", method = RequestMethod.POST)
	public String submitClaim(@ModelAttribute MemberSubmitClaim memberSubmitClaim, Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		String valid = auth.validateToken(token);
		if (valid == "True") {
			log.info("");
			model.addAttribute("memberSubmitClaim", memberSubmitClaim);
			memberPortalService.memberSubmitClaim(memberSubmitClaim);
			return "redirect:/memberHome";
		}
		return "redirect:/session-timed-out";

	}

	@GetMapping("/showClaimSubmit")
	public String showClaimSubmit(Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		String valid = auth.validateToken(token);
		if (valid == "True") {

			return "showClaimStatusSubmit";
		}
		return "redirect:/session-timed-out";
	}

	@RequestMapping("/showProviderPolicySubmit")
	public String providerPolicySubmit(Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		String valid = auth.validateToken(token);
		if (valid == "True") {
			return "SubmitProviderDetails";
		}
		return "redirect:/session-timed-out";

	}

	@RequestMapping("/showViewBillPage")
	public String showViewBillPage(Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		String valid = auth.validateToken(token);
		if (valid == "True") {
			model.addAttribute("title", "Bill");
			return "viewBillSubmit";
		}
		return "redirect:/session-timed-out";

	}

	@RequestMapping(value = "/viewbill", method = RequestMethod.POST)
	public String viewBills(@RequestParam("memberId") String memberId, @RequestParam("policyId") String policyId,
			Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		String valid = auth.validateToken(token);

		if (valid == "True") {
			model.addAttribute("title", "View Bills");

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", token);
			HttpEntity<Boolean> requestEntity = new HttpEntity<>(null, headers);
			ResponseEntity<ViewBillResponse[]> response = restTemp.exchange(
					"http://memberservice/viewbill/" + memberId + "/" + policyId, HttpMethod.GET, requestEntity,
					ViewBillResponse[].class);

			ViewBillResponse[] res = response.getBody();
			model.addAttribute("listBills", res);
			return "viewBills";
		}
		return "redirect:/session-timed-out";

	}

	@RequestMapping(value = "/viewStatus", method = RequestMethod.POST)
	public String viewClaimSatus(@RequestParam("claimId") String claimId, @RequestParam("policyId") String policyId,
			@RequestParam("memberId") String memberId, Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		String valid = auth.validateToken(token);
		if (valid == "True") {

			try {
				model.addAttribute("title", "View Bills");

				ResponseEntity<ClaimStatusResult> response = restTemp.getForEntity(
						"http://claimservice/getClaimStatus/" + claimId + "/" + policyId + "/" + memberId,
						ClaimStatusResult.class);
				ClaimStatusResult res = response.getBody();
				model.addAttribute("claimRes", res);
				return "showClaimStatusResult";

			} catch (Exception e) {

				return "InvalidCredential";
			}
		}
		return "redirect:/session-timed-out";

	}

	@RequestMapping(value = "/viewProviderDetails", method = RequestMethod.POST)
	public String providerPolicyDetails(@RequestParam("policyId") String policyId, Model model, HttpSession session) {
		String token = (String) session.getAttribute("token");
		String valid = auth.validateToken(token);
		if (valid == "True") {
			try {
				ResponseEntity<ProviderResult> response = restTemp
						.getForEntity("http://policyservice/getChainOfProviders/" + policyId, ProviderResult.class);
				ProviderResult res = response.getBody();
				model.addAttribute("detailRes", res);
				return "showProviderResult";

			} catch (Exception e) {
				return "ProviderError";
			}
		}
		return "redirect:/session-timed-out";

	}
}
