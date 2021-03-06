package mobomobo.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mobomobo.dto.BookBest;
import mobomobo.dto.BookBestImg;
import mobomobo.service.face.BookFamousLineService;
import mobomobo.util.BookBestPaging;

@Controller
@RequestMapping(value="/admin/book")
public class AdminBookFamousController {

	private static final Logger logger = LoggerFactory.getLogger(AdminBookFamousController.class);

	@Autowired
	private BookFamousLineService bookFamousLineService;
	
	
	@RequestMapping("/bookFamous")
	public void list(Model model, BookBestPaging inData) {
		logger.info("/admin/book/bookFamous");
		

		BookBestPaging paging = bookFamousLineService.getPaging(inData);
		
		logger.debug(paging.toString());
		
		List<HashMap<String,Object>> list = bookFamousLineService.getList(paging);
//		logger.debug(list.toString());
		
		model.addAttribute("list",list);
		model.addAttribute("paging",paging);
		
	}
	
	@RequestMapping(value="/famousDetail")
	public void detail(String bookBestno, Model model) {
		logger.info("/admin/book/famousDetail");
		logger.debug(bookBestno);
		
		BookBest bookBest = bookFamousLineService.getBookBestInfo(bookBestno);
//		logger.debug(bookBest.toString());
		BookBestImg img = bookFamousLineService.getBookBestImgInfo(bookBestno);
//		logger.debug(img.toString());
		
		String [] str = bookBest.getBestContext().split("/");
		
		for(int i=0;i<str.length;i++) {
			logger.debug(str[i]);
		}
		
		
		model.addAttribute("context",str);
		model.addAttribute("detail",bookBest);
		model.addAttribute("img",img);

	}
	@RequestMapping(value="/famousWrite")
	public void write() {
		logger.info("/admin/book/famousWrite [GET]");
	}
	@RequestMapping(value="/famousWrite",method = RequestMethod.POST)
	public String writeProc(BookBest bookbest,String bestContext1,String bestContext2,String bestContext3
			, @RequestParam(required = false)MultipartFile file
			, HttpSession session) {
		logger.info("/admin/book/famousWrite [POST]");
		logger.debug(bestContext1);
		logger.debug(bestContext2);
		logger.debug(bestContext3);
		logger.debug("{}",file);
		logger.debug("{}",session.getAttribute("userno"));
		
		BookBest info = bookFamousLineService.saveBookBest(bookbest,bestContext1,bestContext2,bestContext3,session);
		logger.debug(info.toString());
		
		
		BookBestImg img = bookFamousLineService.saveImg(file,bookbest);
//		logger.debug(img.toString());
		
		
		bookFamousLineService.insertBookInfo(info, img);
		
		return "redirect:/admin/book/bookFamous";
		
		
	}
	@RequestMapping(value="/delete")
	public String delete(String bookBestno) {
		
		bookFamousLineService.delete(bookBestno);
		
		
		
		
		return "redirect:/admin/book/bookFamous";
	}
	
	
	
	
}
