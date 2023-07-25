package bookmanagement.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bookmanagement.model.BookBean;
import bookmanagement.presistent.dao.BookDAO;
import bookmanagement.presistent.dto.BookRequestDTO;
import bookmanagement.presistent.dto.BookResponseDTO;

@Controller
public class BookController {
	@Autowired
	private BookDAO dao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayBook(ModelMap map) {
		ArrayList<BookResponseDTO> list = dao.selectAll();
		map.addAttribute("list", list);
		return "displaybook";
	}

	@RequestMapping(value = "/setupaddbook", method = RequestMethod.GET)
	public ModelAndView setupaddbook() {
		return new ModelAndView("addbook", "bean", new BookBean());
	}

	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public String addbook(@ModelAttribute("bean") @Validated BookBean bean, BindingResult bs, ModelMap map) {
		if (bs.hasErrors()) {
			return "addbook";
		}
		BookRequestDTO dto = new BookRequestDTO();
		dto.setBookCode(bean.getBookCode());
		dto.setBookTitle(bean.getBookTitle());
		dto.setBookAuthor(bean.getBookAuthor());
		dto.setBookPrice(Double.valueOf(bean.getBookPrice()));
		int rs = dao.insertData(dto);
		if (rs == 0) {
			map.addAttribute("error", "Insert Fail...");
			return "addbook";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/setupupdatebook/{bookCode}", method = RequestMethod.GET)
	public ModelAndView setupupdatebook(@PathVariable String bookCode) {
		BookRequestDTO dto = new BookRequestDTO();
		dto.setBookCode(bookCode);
		return new ModelAndView("updatebook", "bean", dao.selectOne(dto));
	}

	@RequestMapping(value = "/updatebook", method = RequestMethod.POST)
	public String updatebook(@ModelAttribute("bean") @Validated BookBean bean, BindingResult bs, ModelMap map) {
		if (bs.hasErrors()) {
			return "updatebook";
		}
		BookRequestDTO dto = new BookRequestDTO();
		dto.setBookCode(bean.getBookCode());
		dto.setBookTitle(bean.getBookTitle());
		dto.setBookAuthor(bean.getBookAuthor());
		dto.setBookPrice(Double.valueOf(bean.getBookPrice()));
		int rs = dao.updateData(dto);
		if (rs == 0) {
			map.addAttribute("error", "Update Fail...");
			return "updatebook";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/deletebook/{bookCode}", method = RequestMethod.GET)
	public String deletebook(@PathVariable String bookCode, ModelMap map) {
		BookRequestDTO dto = new BookRequestDTO();
		dto.setBookCode(bookCode);
		int rs = dao.deleteData(dto);
		if (rs == 0) {
			map.addAttribute("msg", "Delete Fail...");
		}
		return "redirect:/";
	}
}
