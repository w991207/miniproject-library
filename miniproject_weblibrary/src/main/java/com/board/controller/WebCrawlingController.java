package com.board.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


@Controller
@RequestMapping("/webcrawl")
public class WebCrawlingController {

	@GetMapping("/extractText")
	public String extractText(Model model) {
	    System.out.println("웹크롤링 시작");
	    try {
	        // 웹 페이지 URL
	        String url = "https://munjang.or.kr/board.es?mid=a10706010000&bid=0023&list_no=8157&act=view&ord=B&nPage=1&c_type=A&c_page=";

	        // JSoup을 사용하여 웹 페이지의 내용 가져오기
	        Document document = Jsoup.connect(url).get();

	        // 원하는 태그 선택
	        Elements pElements = document.select("p");

	        int startIdx = 1;
	        int endIdx = Math.min(pElements.size(), 30);

	     // 모델에 추가할 때 변수명을 Thymeleaf와 일치하도록 수정
	        for (int index = startIdx; index <= endIdx; index++) {
	            Element currentPElem = pElements.get(index - 1); // index에서 1을 빼서 정확한 위치의 요소를 가져옴
	            String extractedText = currentPElem.text();
	            model.addAttribute("extractedText" + index, extractedText);
	            System.out.println("extractedText" + index + ": " + extractedText); // 추가한 로깅
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	        model.addAttribute("extractedTextError", "크롤링 중 오류가 발생했습니다.");
	    }
	    System.out.println("웹크롤링 끝");
	    return "book/bookQuiz";
	}
}
