package com.itheima.springmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.springmvc.pojo.Items;
import com.itheima.springmvc.pojo.QueryVo;
import com.itheima.springmvc.service.ItemService;

/**
 * 商品管理
 * 
 * @author lx
 *
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	// 入门程序 第一 包类 + 类包 + 方法名
	@RequestMapping(value = "/item/itemlist.action")
	public String itemList(Model model) {

		//从Mysql中查询
		List<Items> list = itemService.selectItemsList();
		System.out.println("change list method");
//		ModelAndView mav = new ModelAndView();
//		// 数据
//		mav.addObject("itemList", list);
//		mav.setViewName("itemList");
		model.addAttribute("itemList", list);
		return "itemList";
	}

	//去修改页面 入参 id
		@RequestMapping(value = "/itemEdit.action")
//		public ModelAndView toEdit(@RequestParam(value = "id",required = false,defaultValue = "1") Integer idaaq,
		public ModelAndView toEdit(Integer id,
				HttpServletRequest request,HttpServletResponse response
				,HttpSession session,Model model){
			
			//Servlet时代开发
//			String id = request.getParameter("id");
			
			//查询一个商品
//			Items items = itemService.selectItemsById(Integer.parseInt(id));
			Items items = itemService.selectItemsById(id);
			ModelAndView mav = new ModelAndView();
			//数据
			mav.addObject("item", items);
			mav.setViewName("editItem");
			return mav;
			
		}
		//提交修改页面 入参  为 Items对象  
		@RequestMapping(value = "/updateitem.action")
//		public ModelAndView updateitem(Items items){
		public String updateitem(QueryVo vo,MultipartFile pictureFile) throws Exception{


			//保存图片到 
			String name = UUID.randomUUID().toString().replaceAll("-", "");
			//jpg
			String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
			
			pictureFile.transferTo(new File("D:\\upload\\" + name + "." + ext));
			
			vo.getItems().setPic(name + "." + ext);
			//修改
			itemService.updateItemsById(vo.getItems());
			
			//ModelAndView mav = new ModelAndView();
			//mav.setViewName("success");
			//return "redirect:/itemEdit.action?id="+vo.getItems().getId();
			return "success";
		}
		//删除多个
		@RequestMapping(value="deletes.action")
		public ModelAndView deletes(QueryVo vo){
			
			
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.setViewName("success");
			return modelAndView;
		}
		//json数据交互 json和item对象的互相转换
		@RequestMapping(value = "/json.action")
		public @ResponseBody
		Items json(@RequestBody Items items){
			
			System.out.println(items);
			
			return items;
		}
		//RestFul风格的开发
		@RequestMapping(value = "/itemEdit/{id}.action")
		public ModelAndView toEdit1(@PathVariable Integer id,
				HttpServletRequest request,HttpServletResponse response
				,HttpSession session,Model model){
			
			//Servlet时代开发
//			String id = request.getParameter("id");
			
			//查询一个商品
//			Items items = itemService.selectItemsById(Integer.parseInt(id));
			Items items = itemService.selectItemsById(id);
			ModelAndView mav = new ModelAndView();
			//数据
			mav.addObject("item", items);
			mav.setViewName("editItem");
			return mav;
			
		}
		//去登陆的页面
		@RequestMapping(value = "/login.action",method = RequestMethod.GET)
		public String login(){
			return "login";
		}
		//提交界面
		@RequestMapping(value = "/login.action",method = RequestMethod.POST)
		public String login(String username
				,HttpSession httpSession){
			httpSession.setAttribute("USER_SESSION", username);
			return "redirect:/item/itemlist.action";
		}
}
