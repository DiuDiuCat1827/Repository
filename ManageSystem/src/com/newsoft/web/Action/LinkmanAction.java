package com.newsoft.web.Action;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.newsoft.domain.Customer;
import com.newsoft.domain.LinkMan;
import com.newsoft.service.CustomerService;
import com.newsoft.service.LinkmanService;
import com.newsoft.service.Impl.CustomerServiceImpl;
import com.newsoft.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import freemarker.template.utility.StringUtil;

public class LinkmanAction extends ActionSupport implements ModelDriven<LinkMan> {
	/**
	 * 模型驱动 Action把传进来的数据用对象进行接收
	 */
	private static final long serialVersionUID = 4184286637684681484L;

	LinkMan linkMan = new LinkMan();

	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 传入进来的页面大小和当前页数
	private Integer pageSize;
	private Integer currentPage;
	private String lkmName;
	private LinkmanService linkmanService;

	// 查找
	public String list() {
		// 封装离线对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
		// 判断并封装函数
		if (StringUtils.isNotBlank(lkmName)) {
			detachedCriteria.add(Restrictions.like("lkm_name", "%" + lkmName + "%"));
		}
		// 调用getPage查询分页数据
		PageBean pageBean = linkmanService.getPageBean(detachedCriteria, currentPage, pageSize);
		// 封入context中
		ActionContext.getContext().put("pageBean", pageBean);
		// 根据返回值来确定转向网页
		return "list";
	}

	// 保存
	public String updateOrSave() {
		// // 调用dao层进行保存
		linkmanService.updateOrSave(linkMan);
		// list表示转到当前页面 toList 表示转到list界面
		return "toList";
	}

	// 修改
	public String edit() throws Exception {
		// 1.调用service获得Customer独享
		// System.out.println("customer.getCust_id():"+customer.getCust_id());
		LinkMan l = linkmanService.getById(linkMan.getLkm_id());
		// 2.
		ActionContext.getContext().put("linkman", l);
		return "edit";
	}

	// 删除
	public String delete() throws Exception {
		try {
			linkmanService.delete(linkMan.getLkm_id());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// 1.调用service获得Customer独享
		return "toList";
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public LinkMan getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(LinkMan linkMan) {
		this.linkMan = linkMan;
	}

	public String getLkmName() {
		return lkmName;
	}

	public void setLkmName(String lkmName) {
		this.lkmName = lkmName;
	}

	public LinkmanService getLinkmanService() {
		return linkmanService;
	}

	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}

}
