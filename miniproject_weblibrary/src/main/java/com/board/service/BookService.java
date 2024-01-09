package com.board.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartRequest;

import com.board.command.BookInsertCommad;
import com.board.command.ReserveBookCommand;
import com.board.dtos.BookDto;
import com.board.dtos.FileBoardDto;
import com.board.mapper.BookMapper;
import com.board.mapper.FileMapper;

import jakarta.servlet.http.HttpServletRequest;



@Service
public class BookService {

   @Autowired
   private BookMapper bookMapper;
   @Autowired
   private FileMapper fileMapper;
   @Autowired
   private FileService fileService;
   
//   public List<NewsBoardDto> getAllList(String pnum){
//      Map<String,String>map=new HashMap<>();
//      map.put("pnum", pnum);
//      return newsBoardMapper.getAllList(map);
//   }
   
 //글목록 조회
 	public List<BookDto> getAllList(){
 		return bookMapper.getAllList();
 	}
 	
	public List<BookDto> getSelectList(String title){
		return bookMapper.getSelectList(title);
	}
   
	public BookDto getBook(int book_seq) {
        return bookMapper.getBook(book_seq);
     }
	// 예약한 책 반납하기
	public boolean returnBook(int seq) {
	   return bookMapper.returnBook(seq);
	}

   //글 추가, 파일 업로드 및 파일 정보 추가
      public void insertBook(BookInsertCommad bookInsertCommad, MultipartRequest multipartRequest, HttpServletRequest request) throws IllegalStateException, IOException {
         
         // command -> dto 데이터 옮겨담기
         BookDto bookDto = new BookDto();
         bookDto.setBook_seq(bookInsertCommad.getBook_seq());
         bookDto.setTitle(bookInsertCommad.getTitle());
         bookDto.setWriter(bookInsertCommad.getWriter());
         bookDto.setPublisher(bookInsertCommad.getPublisher());
         bookDto.setContent(bookInsertCommad.getContent());
         
         //새글을 추가할 때 파라미터로 전달된 boardDto 객체에 자동으로 증가된 board_seq값이 저장
         bookMapper.insertBook(bookDto); // 새글 추가
         //첨부된 파일이 있는 경우
         if(!multipartRequest.getFiles("filename").get(0).isEmpty()) {
            // 파일 저장경로 설정 : 절대경로, 상대경로
            String filepath = request.getSession().getServletContext().getRealPath("upload");
            System.out.println("파일저장경로:"+filepath);
            
            // 파일 업로드 작업은 FileService 쪽에서 업로드하고 업로드된 객체 반환
            List<FileBoardDto> uploadFileList = fileService.uploadFiles(filepath, multipartRequest);
            
            System.out.println(bookDto.getBook_seq());
            // 파일 정보를 DB에 추가
            // 글 추가할때 board_seq 증가된 값 --> file 정보를 추가할 때 사용
            // Testboard : board_seq PK         board_seq FK
            for(FileBoardDto fDto : uploadFileList) {
               fileMapper.insertFileBoard(new FileBoardDto(0, bookDto.getBook_seq(), fDto.getOrigin_filename(), fDto.getStored_filename()));
            }
         }
         
      }
      

      // 수정하기
      public boolean reserveBook(String bookTitle, String bookAuthor, String bookPublisher, String reserver) {
               //command:UI --> DTO:DB
               Map<String, String> map = new HashMap<>();
               
               map.put("bookTitle", bookTitle);
               map.put("bookAuthor", bookAuthor);
               map.put("bookPublisher", bookPublisher);
               map.put("reserver", reserver);
               
               return bookMapper.reserveBook(map);
      }
//      
//      public boolean mulDel(String[] seqs) {
//         return newsBoardMapper.mulDel(seqs);
//      }
//      
//      public boolean readCount(int seq) {
//         
//         return newsBoardMapper.readCount(seq);
//      }
//      public boolean insertReply(InsertReplyCommand insertCommand) throws Exception {
//          
//          NewsBoardDto dto=new NewsBoardDto();
//          dto.setBoard_seq(insertCommand.getBoard_seq());
//          dto.setId(insertCommand.getId());
//          dto.setContent(insertCommand.getContent());
//          
//          int count=newsBoardReplyMapper.insertReplyBoard(dto);
//         
//          return count>0?true:false;
//       }
//   
//    public List<NewsBoardDto> showReply(int seq) throws Exception{    
//        return newsBoardReplyMapper.showReplyBoard(seq);
//    }
//
//   public int getPCount() {
//      return newsBoardMapper.getPCount();
//   }
}












