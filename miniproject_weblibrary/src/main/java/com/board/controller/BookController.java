package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import jakarta.servlet.http.HttpServletRequest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.MalformedURLException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartRequest;
import org.xml.sax.SAXException;

import com.board.command.BookInsertCommad;
import com.board.command.ReserveBookCommand;
import com.board.command.returnCommand;
import com.board.dtos.BookDto;
import com.board.service.FileService;
import com.board.service.BookService;


@Controller
@RequestMapping(value = "/book")
public class BookController {

   @Autowired
   private BookService bookService;
   
   @Autowired
   private FileService fileService;
   
   
   @GetMapping(value = "/bookList")
	public String bookList(Model model, HttpServletRequest request) {
		System.out.println("책목록 보기");
		List<BookDto> list = bookService.getAllList();
		request.getSession().setAttribute("ldto", list);
		model.addAttribute("list", list);
		
		return "book/bookList";
	}

//   @GetMapping(value = "/selectList")
//	public String SelectList(Model model, String title) {
//		System.out.println("검색목록 보기");
//		List<BookDto> list = bookService.getSelectList(title);
//		model.addAttribute("list", list);
//		return "book/bookList";
//	}
   
   @GetMapping(value = "select")
   public String SelectList() {
	   return "book/selectList";
   }
   
   @ResponseBody
    @GetMapping(value = "/selectList")
	public ResponseEntity<List<Map<String, String>>> SelectList(Model model, String selectWord) throws IOException, ParserConfigurationException, SAXException, ParserConfigurationException  {
		System.out.println("검색목록 보기");
		HttpURLConnection conn = null;
        List<Map<String, String>> resultList = new ArrayList<>();
		
		try {
			URL url = new URL("http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?"
					+ "Query=" + selectWord
					+ "&QueryType=Title"
					+ "&TTbkey=ttbljt29791600001");
			
			conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/xml");
            conn.setDoOutput(true);
			
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(conn.getInputStream());

            // Extract data from XML
            NodeList itemList = doc.getElementsByTagName("item");
            
            for (int i = 0; i < itemList.getLength(); i++) {
                Node itemNode = itemList.item(i);
                Map<String, String>map = new HashMap<>();   
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
                    String author = itemElement.getElementsByTagName("author").item(0).getTextContent();
                    String publisher = itemElement.getElementsByTagName("publisher").item(0).getTextContent();
                    map.put("title",title);
                    map.put("author",author);
                    map.put("publisher",publisher);
                    resultList.add(map);
                }
            }
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            return new ResponseEntity<>(resultList, headers, HttpStatus.OK);

		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			if (conn != null) {
                conn.disconnect();
            }
		}
		
//		return "book/bookList";
	}
   
   
   @GetMapping(value = "/bookInsert")
   public String boardInsertForm(Model model) {
      System.out.println("책추가폼 이동");
      model.addAttribute("bookInsertCommand", new BookInsertCommad());
      
      return "book/bookInsertForm";
   }
   
   
   
   @PostMapping(value = "/bookInsert")
   public String boardInsert(@Validated BookInsertCommad bookInsertCommad,
                           BindingResult result, MultipartRequest multipartRequest,
                           HttpServletRequest request,
                           Model model) throws IllegalStateException, IOException {
      
      if(result.hasErrors()) {
         System.out.println("글을 모두 입력해주세요");
         return "book/bookInsertForm";
      }
      
      bookService.insertBook(bookInsertCommad, multipartRequest, request);
      
      return "redirect:/book/bookList";
   }
   
   @GetMapping(value = "/bookDetail")
   public String boardDetail(int book_seq, Model model, BookInsertCommad bookInsertCommad) {
	   System.out.println("책 상세내용");
      BookDto dto = bookService.getBook(book_seq);
      model.addAttribute("bookInsertCommand", new BookInsertCommad());
      model.addAttribute("bdto", dto);
//      int seq = bookInsertCommad.getBook_seq();
//      bookService.readCount(seq);//조회수 증가

      return "book/bookDetail";
   }
   
   @GetMapping(value = "/reserveBook")
   public String reserveBook(String bookTitle, String bookAuthor, String bookPublisher, String reserver, Model model, ReserveBookCommand reserveBookCommand) {
	   System.out.println(bookTitle + ", " + bookAuthor + "," + bookPublisher);
	   bookService.reserveBook(bookTitle, bookAuthor,bookPublisher, reserver);
	   return "redirect:/book/select";
   }
   
   @GetMapping(value = "/bookTimer")
   public String bookTimer(Model model) {
      System.out.println("책 읽기 타이머로 이동");
      return "book/bookTimer";
   }
   
   
//   @PostMapping(value = "/newsBoardUpdate")
//   public String boardUpdate(@Validated NewsUpdateBoardCommand updateBoardCommand
//                              ,BindingResult result) {
//      System.out.println("수정시작");
//      if(result.hasErrors()) {
//         System.out.println("수정내용을 모두 입력해주세요");
//         return "news/newsBoardDetail";
//      }
//      newsBoardService.updateBoard(updateBoardCommand);
//      
//      return "redirect:/news/newsBoardDetail?board_seq="+updateBoardCommand.getBoard_seq();
//      
//   }
   
//   @GetMapping(value = "/download")
//   public void download(int file_seq, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//      FileBoardDto fdto = fileService.getFileInfo(file_seq);
//      
//      fileService.fileDownload(fdto.getOrigin_filename(),fdto.getStored_filename(),request,response);
//   }
//   
   @RequestMapping(value = "/returnBook", method = {RequestMethod.GET, RequestMethod.POST})
   public String returnBook(@Validated returnCommand returnCommand,
                            BindingResult result,
                            Model model,
                            String pnum) {
       System.out.println("책 반납하기");

       // 로그 추가: 메서드 시작
       System.out.println("returnBook 메서드 시작");

       if (result.hasErrors()) {
           // 유효성 검사 에러가 있을 경우 처리
           List<BookDto> list = bookService.getAllList();
           model.addAttribute("list", list);

           // 로그 추가: 유효성 검사 실패
           System.out.println("유효성 검사 실패");

           return "user/userReserve"; // 적절한 뷰 페이지로 이동
       }

       // 유효성 검사가 통과한 경우
       boolean success = bookService.returnBook(returnCommand.getSeq());

       if (success) {
           // 반납 성공
           System.out.println("책 반납 완료");
       } else {
           // 반납 실패
           System.out.println("책 반납 실패");
       }

       // 로그 추가: 메서드 종료
       System.out.println("returnBook 메서드 종료");

       return "redirect:/user/userReserve"; // 반납 후 도서 목록 페이지로 이동
   }


}