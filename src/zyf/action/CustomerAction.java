package zyf.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import zyf.dao.ICustomerDao;
import zyf.entity.Customer;
import zyf.service.ICustomerService;
import zyf.service.impl.CustomerServiceImpl;
import zyf.service.impl.Hb_CustomerServiceImpl;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	private final String SUCCESS="success";
	private final String FAILED="failed";
	private final String ADDSUCCESS="addSuccess";
	private final String ADDFAILED="addFailed";
	private final String EXIST="exist";
	private final String NOTEXIST="notexist";
	private final String LISTSUCCESS="listSuccess";
	private final String LISTFAILED="listFailed";
	private final String LISTNAMESUCCESS="listNameSuccess";
	private final String LISTNAMEFAILED="listNameFailed";
	private final String UPDATESUCCESS="updateSuccess";
	private final String SETUPDATESUCCESS="setupdateSuccess";
	private final String UPDATEFAILED="updateFailed";
	
//	CustomerServiceImpl ics = new CustomerServiceImpl();
	Hb_CustomerServiceImpl ics = new Hb_CustomerServiceImpl();
	
	Customer customer = new Customer();

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * 添加客户
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception{
		Customer cust = new Customer(customer.getCustName(), customer.getCustLevel(), customer.getCustSource(), customer.getCustLinkman(), 
				customer.getCustPhone(), customer.getCustMobile(), customer.getCustAddress(), 
				customer.getCustZip(), customer.getCustFax(), customer.getCustWebsite());
		if(customer.getCustName()!=null){
			Customer customer = ics.addCustomer(cust);
			if(customer!=null){
				return ADDSUCCESS;
			}else{
				return FAILED;
			}
		}else{
			return FAILED;
		}
		
	}
	
	/**
	 * 判断用户是否存在
	 * @return
	 * @throws Exception 
	 */
	public String isExistCustomer() throws Exception{
		Customer cust = new Customer(customer.getCustName(), customer.getCustLevel(), customer.getCustSource(), customer.getCustLinkman(), 
				customer.getCustPhone(), customer.getCustMobile(), customer.getCustAddress(), 
				customer.getCustZip(), customer.getCustFax(), customer.getCustWebsite());
		HttpServletResponse resp = ServletActionContext.getResponse();
//		resp.setContentType("text/html;charset=utf-8");
		
		
		PrintWriter out = resp.getWriter();
		if(customer.getCustName()!=null){
			Customer customer = ics.isExist(cust);
			if(customer!=null){
				out.print(EXIST);
			}else{
				out.print(NOTEXIST);
			}
		}else{
			out.print(NOTEXIST);
		}
		out.flush();
		out.close();
		return null;
		
	}
	
	/***
	 * 获取所有的客户信息
	 * @return
	 * @throws Exception 
	 */
	public String getAllCustomer() throws Exception{
		List<Customer> allCustomer = ics.getAllCustomer();
		if(allCustomer!=null){
			ServletActionContext.getRequest().setAttribute("list",allCustomer );
			return LISTSUCCESS;
		}else{
			return FAILED;
		}
	}
	
	/***
	 * 获取所有指定名称的客户信息
	 * @return
	 * @throws Exception 
	 */
	public String getAllCustomerByName() throws Exception{

		List<Customer> allCustomer = ics.getAllCustomerByName(customer.getCustName());
		if(allCustomer!=null){
			ServletActionContext.getRequest().setAttribute("list", allCustomer);
			return LISTSUCCESS;
		}else{
			return FAILED;
		}
		
	
		
		
		
	}
	/**
	 * 删除客户
	 * @return
	 * @throws Exception 
	 */
	public String deleteCustomer() throws Exception{

		Customer cust = new Customer(customer.getCustId(),customer.getCustName(), customer.getCustLevel(), customer.getCustSource(), customer.getCustLinkman(), 
				customer.getCustPhone(), customer.getCustMobile(), customer.getCustAddress(), 
				customer.getCustZip(), customer.getCustFax(), customer.getCustWebsite());
		if(customer.getCustId()!=null){
			int delete = ics.deleteCustomer(cust);
			if(delete>0){
				return getAllCustomer();
			}else{
				return FAILED;
			}
		}else{
			return FAILED;
		}
	
	}

	/**
	 * 获取客户的原有数据并展示到页面
	 * @param cust
	 * @return
	 * @throws Exception 
	 */
	public String updateCustomer() throws Exception{
		Customer cust = new Customer(customer.getCustId());
		Customer customer = ics.getCustomerById(cust);
		if(customer!=null){
			ServletActionContext.getRequest().setAttribute("customer", customer);
			return UPDATESUCCESS;
		}else{
			return FAILED;
		}
		
	}
	/**
	 * 更新客户信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception{
		Customer cust = new Customer(customer.getCustId(),customer.getCustName(), customer.getCustLevel(), customer.getCustSource(), customer.getCustLinkman(), 
				customer.getCustPhone(), customer.getCustMobile(), customer.getCustAddress(), 
				customer.getCustZip(), customer.getCustFax(), customer.getCustWebsite());
		if(customer.getCustId()!=null){
			int update = ics.updateCustomer(cust);
			if(update>0){
				return getAllCustomer();
			}else{
				return FAILED;
			}
		}else{
			return FAILED;
		}
	}
	@Override
	public Customer getModel() {
		// TODO Auto-generated method stub
		return customer;
	}

}
