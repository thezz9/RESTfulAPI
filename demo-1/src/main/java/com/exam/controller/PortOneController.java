package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.PortVO;
import com.example.project.ProjectService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/Port")
@Controller
public class PortOneController {

	PortOneController(){
		System.out.println("===> PortOneController 생성자");
	}
	
	@Autowired
	private ProjectService  mPServ;
	
	@GetMapping("/PortOneStart")
	public String PortOneStart( Model model ,  PortVO vo ) {
		System.out.println("===> PortOneStart 매핑확인 ");
		
		 vo.setGname("김태익 코딩학원");
		 vo.setGymnum(90001);
		 model.addAttribute("gym", vo);
		 
		 vo.setMmail("thezz9@naver.com");
		 vo.setMname("장대산");
		 vo.setMphone("010-1234-1234");
		 vo.setMembernum("90001");
		 
		 model.addAttribute("member", vo);
		 
		 vo.setDataName("1개월 이용권");
		 vo.setDataGoodsnum("N12333");
		 vo.setDataPrice(100);
		 model.addAttribute("data", vo);
		 
		return "/PortOne/index";
	}
	

    @PostMapping("/insertMPay")
    @ResponseBody
    public String insertMPay(@RequestBody PortVO mpay, HttpSession session, RedirectAttributes rttr){
    	  System.out.println("===> Controller insertMPay()");
        
        // 리턴값으로 view = "payment?mpaynum=" + mpay.getMpaynum();
        // 즉 저장하고 결제 완료 페이지로 넘어 간다. 
        String view = mPServ.insertMPay(mpay, session, rttr);  
        return view;
    }

    @GetMapping("/payment")
    public String paymentContents(String mpaynum, HttpSession session, Model model){
    	  System.out.println("===> Controller paymentContents()");
    	  // 테이터 베이스에 결제 정보를 저장 후  주문정보와 헬스장 정보를 리턴한다. 
        model.addAttribute("mv", mPServ.paymentContents(mpaynum, session));
        return "PortOne/getBoard.html";
    }
        
        
}
