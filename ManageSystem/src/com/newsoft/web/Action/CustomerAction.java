package com.newsoft.web.Action;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.newsoft.dao.CustomerDao;
import com.newsoft.domain.Customer;
import com.newsoft.service.CustomerService;
import com.newsoft.service.Impl.CustomerServiceImpl;
import com.newsoft.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import freemarker.template.utility.StringUtil;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	/**
	 * 模型驱动 Action把传进来的数据用对象进行接收
	 */
	private static final long serialVersionUID = 4184286637684681484L;

	Customer customer = new Customer();

	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// 传入文件
	private File photo;
	private String photoFilename;
	private String photoContentType;
	// 传入进来的页面大小和当前页数
	private Integer pageSize;
	private Integer currentPage;
	private String custName;
	private CustomerService customerService;

	// 查找
	public String list() {
		// 封装离线对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// 判断并封装函数
		if (StringUtils.isNotBlank(custName)) {
			detachedCriteria.add(Restrictions.like("cust_name", "%" + custName + "%"));
		}
		// 调用getPage查询分页数据
		PageBean pageBean = customerService.getPageBean(detachedCriteria, currentPage, pageSize);
		// 封入context中
		ActionContext.getContext().put("pageBean", pageBean);
		// 根据返回值来确定转向网页
		return "list";
	}

	// 保存
	public String updateOrSave() {
		// 将上传文件保存到指定木目录
		if (photo != null) {
			photo.renameTo(new File("D:/photo/Myphoto2.jpg"));
		}
		customerService.updateOrSave(customer);
		return "toList";
	}

	// 修改
	public String edit() throws Exception {
		// 1.调用service获得Customer独享
		Customer c = customerService.getById(customer.getCust_id());
		// 2.
		ActionContext.getContext().put("customer", c);
		return "edit";
	}

	// 获得产业数量
	public String industryCount() throws Exception {
		// 1.查询产业名字和数量
		List<Object[]> list = customerService.getIndustryCount();
		// 2.放入request中
		ActionContext.getContext().put("list", list);
		return "industryCount";
	}

	// 获得来源数量
	public String resourceCount() throws Exception {
		// 1.查询产业名字和数量
		List<Object[]> list = customerService.getResourceCount();
		// 2.放入request中
		ActionContext.getContext().put("list", list);
		return "resourceCount";
	}

	// 删除
	public String delete() throws Exception {
		try {
			customerService.delete(customer.getCust_id());
		} catch (Exception e) {
			// TODO: handle exception
		}

		// 1.调用service获得Customer独享
		return "toList";
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public String getPhotoFilename() {
		return photoFilename;
	}

	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

}
