package com.newsoft.web.Action;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.newsoft.domain.Customer;
import com.newsoft.domain.SaleVisit;
import com.newsoft.domain.User;
import com.newsoft.service.CustomerService;
import com.newsoft.service.SaleVisitService;
import com.newsoft.service.Impl.CustomerServiceImpl;
import com.newsoft.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
	private SaleVisit saleVisit = new SaleVisit();
	private Integer currentPage;
	private Integer pageSize;
	private SaleVisitService saleVisitService;
	private CustomerService customerService;

	// 添加客户拜访记录
	public String add() throws Exception {
		try {
			// 0 取出登陆用户,放入SaleVisit实体.表达关系
			User user = (User) ActionContext.getContext().getSession().get("user");
			saleVisit.setUser(user);
			// 通过cust_id查询customer
			Customer customer = customerService.getById(saleVisit.getCustomer().getCust_id());
			// 1 调用Service保存客户拜访记录
			saleVisit.setCustomer(customer);
			// 判断是否存在
			SaleVisit find = saleVisitService.getById(saleVisit.getVisit_id());
			if (find == null) {
				saleVisitService.save(saleVisit);
			} else {
				saleVisitService.update(saleVisit);
			}
			// 2 重定向到拜访记录列表Action
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "toList";
	}

	public String list() throws Exception {

		// 封装离线查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
		// 判断并封装参数
		if (saleVisit.getCustomer() != null && saleVisit.getCustomer().getCust_id() != null) {
			detachedCriteria.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
		}

		// 1 调用Service查询分页数据(PageBean)
		PageBean pageBean = saleVisitService.getPageBean(detachedCriteria, currentPage, pageSize);
		System.out.println("pageBean:" + pageBean);
		// 2 将PageBean放入request域,转发到列表页面显示
		ActionContext.getContext().put("pageBean", pageBean);

		return "list";
	}

	// // 去往编辑页面回显
	public String edit() throws Exception {
		// 1 调用Service根据id查询客户拜访对象
		SaleVisit s = saleVisitService.getById(saleVisit.getVisit_id());
		// 2 将对象放入request域
		ActionContext.getContext().put("saleVisit", s);
		// TODO Auto-generated catch block
		// 3 转发到add.jsp
		return "edit";
	}

	// 删除
	public String delete() throws Exception {
		try {
			saleVisitService.delete(saleVisit.getVisit_id());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// 1.调用service获得Customer独享
		return "toList";
	}

	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public SaleVisit getSaleVisit() {
		return saleVisit;
	}

	public void setSaleVisit(SaleVisit saleVisit) {
		this.saleVisit = saleVisit;
	}

	public SaleVisitService getSaleVisitService() {
		return saleVisitService;
	}

	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
